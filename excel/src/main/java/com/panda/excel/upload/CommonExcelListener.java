package com.panda.excel.upload;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.context.xlsx.DefaultXlsxReadContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.panda.excel.base.CommonExcelProperty;
import com.panda.excel.base.ImportExcelProperty;
import com.panda.excel.base.Pattern;
import com.panda.excel.base.RedisKeyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;

/**
 * @author Administrator
 */

public abstract class CommonExcelListener<T extends CommonExcelProperty> extends AnalysisEventListener<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonExcelListener.class);
    /**
     * excel数据
     */
    protected final List<T> excelList = new ArrayList<>();
    /**
     * excel错误数据
     */
    protected final List<T> excelErrorList = new ArrayList<>();
    /**
     * 采用默认map大小16，一般导入的时候很少大于12列
     */
    private final Map<Integer, String> columnMap = new HashMap<>();
    /**
     * 最大导入数据量 因需要excel重复校验，故不能切片导入
     */
    private static final int MAX_IMPORT_SIZE = 20000;

    int allCount = 0;
    int failedCount = 0;
    int successCount = 0;

    public List<T> getExcelErrorList() {
        return excelErrorList;
    }

    @Override
    public void invoke(T entity, AnalysisContext analysisContext) {
        if (excelList.size() > MAX_IMPORT_SIZE) {
            throw new ExcelAnalysisException(String.format("单次导入数据不能超过%d条", CommonExcelListener.MAX_IMPORT_SIZE));
        }
//        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(entity));
        doLocalDataRequiredValid(entity, (DefaultXlsxReadContext) analysisContext);
        excelList.add(entity);
    }

    public void doLocalDataRequiredValid(T entity, DefaultXlsxReadContext analysisContext) {
        // excel第一行
        Integer currentRowNum = analysisContext.getCurrentRowNum() + 1;
        Field[] fields = entity.getClass().getDeclaredFields();
        // 字段值校验
        try {
            for (Field field : fields) {
                ImportExcelProperty annotation = field.getAnnotation(ImportExcelProperty.class);
                Pattern patternAnnotation = field.getAnnotation(Pattern.class);
                field.setAccessible(true);
                Object fieldValue = field.get(entity);
                // 字段必填，空数据校验
                List<String> errorInfoList = entity.getErrorInfoList();
                if (Objects.nonNull(annotation) && annotation.required()) {
                    if (Objects.isNull(fieldValue)) {
                        if (CollectionUtils.isEmpty(errorInfoList)) {
                            errorInfoList = new LinkedList<>();
                        }
                        entity.setRowIndex(currentRowNum);
                        errorInfoList.add(annotation.name() + annotation.message());
                        entity.setErrorInfoList(errorInfoList);
                    }
                }
                // 字段格式校验
                if (Objects.nonNull(fieldValue) & Objects.nonNull(patternAnnotation) && Objects.nonNull(patternAnnotation.regexp())) {
                    java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(patternAnnotation.regexp());
                    Matcher matcher = pattern.matcher(fieldValue.toString());
                    if (!matcher.matches()) {
                        if (CollectionUtils.isEmpty(errorInfoList)) {
                            errorInfoList = new LinkedList<>();
                        }
                        entity.setRowIndex(currentRowNum);
                        errorInfoList.add(annotation.name() + patternAnnotation.message());
                        entity.setErrorInfoList(errorInfoList);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new ExcelAnalysisException("解析模板出错，请传入正确格式的excel模板");
        }
    }

    @Override
    public void invokeHeadMap(Map headMap, AnalysisContext context) {
        Type[] actualTypeArguments = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        LOGGER.info(JSON.toJSONString(actualTypeArguments));
        Class<T> clazz = (Class<T>) actualTypeArguments[0];
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            ImportExcelProperty annotation = field.getAnnotation(ImportExcelProperty.class);
            if (Objects.nonNull(annotation)) {
                this.columnMap.put(annotation.index(), annotation.name());
            }
        }
        if (!Objects.deepEquals(this.columnMap, headMap)) {
            throw new ExcelAnalysisException("文件模板错误");
        }
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (CollectionUtils.isEmpty(excelList)) {
            throw new ExcelAnalysisException("文件内容为空");
        }
        // 校验重复数据
        doValidLocalRepeatData();
        // 校验业务数据
        doValidBusinessData();
        // 导出错误数据
        doAnalyzeErrorExcelData();
        // 保存业务数据
        doSaveData();
        LOGGER.info("所有数据解析完成！" + this.excelList.size());
    }

    /**
     * 校验重复数据
     */
    public void doValidLocalRepeatData() {
        List<T> excelList = this.excelList;
        Map<String, Integer> repeatMap = new HashMap<>(512);
        try {
            for (T t : excelList) {
                doValidLocalDataAllBlank(t);
                String uniqueKey = getUniqueKey(t);
                repeatMap.put(uniqueKey, repeatMap.getOrDefault(uniqueKey, 0) + 1);
            }
            for (T t : excelList) {
                String uniqueKey = getUniqueKey(t);
                Integer integer = repeatMap.get(uniqueKey);
                if (integer.compareTo(1) > 0) {
                    t.setRepeat(true);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param t row data
     * @return unique key
     */
    public String getUniqueKey(T t) throws IllegalAccessException {
        String uniqueStr = null;
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = field.get(t);
            ImportExcelProperty importExcelProperty = field.getAnnotation(ImportExcelProperty.class);
            if (Objects.nonNull(importExcelProperty)) {
                if (Objects.nonNull(fieldValue) && importExcelProperty.unique()) {
                    uniqueStr += fieldValue;
                }
            }
        }
        return uniqueStr;
    }

    /**
     * 字段必填无数据，则整条数据为空数据
     * @param t row data
     */
    public void doValidLocalDataAllBlank(T t) throws IllegalAccessException {
        Field[] fields = t.getClass().getDeclaredFields();
        t.setAllBlank(true);
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = field.get(t);
            ImportExcelProperty importExcelProperty = field.getAnnotation(ImportExcelProperty.class);
            if (Objects.nonNull(importExcelProperty)) {
                if (Objects.nonNull(fieldValue) && importExcelProperty.required()) {
                    t.setAllBlank(false);
                }
            }
        }
    }

    /**
     * 校验业务数据
     */
    abstract void doValidBusinessData();

    /**
     * 导出错误数据
     */
    void doAnalyzeErrorExcelData(){
        for (T t : excelList) {
            if (t.isAllBlank()) {
                continue;
            }
            allCount++;
            if (!org.springframework.util.CollectionUtils.isEmpty(t.getErrorInfoList())) {
                failedCount++;
                excelErrorList.add(t);
            }else {
                successCount++;
            }
        }
    }

    /**
     * 存储数据库
     */
    abstract void doSaveData();

    public List<T> getExcelList() {
        return excelList;
    }


//    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
//        String path = Objects.requireNonNull(CommonExcelListener.class.getResource("/")).getPath();
//        String filePath = path + "demo" + File.separator + "demo.xlsx";
//        CommonExcelListener<UploadData> listener = new MyExcelListner();
//        EasyExcel.read(new FileInputStream(filePath), UploadData.class, listener).sheet().doRead();
//        List<UploadData> excelList = listener.getExcelList();
//        System.out.println(Arrays.toString(excelList.toArray()));
//        LOGGER.info("excelList:{}", JSON.toJSONString(excelList, SerializerFeature.WriteNullStringAsEmpty));
//    }

    public static void main(String[] args) throws FileNotFoundException {
        int i = 0;
        i++;
        System.out.println(i);
    }
}
