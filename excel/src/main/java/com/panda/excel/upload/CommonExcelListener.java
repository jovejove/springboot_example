package com.panda.excel.upload;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.context.xlsx.DefaultXlsxReadContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.panda.excel.base.CommonExcelProperty;
import com.panda.excel.base.ImportExcelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Administrator
 */
public class CommonExcelListener<T extends CommonExcelProperty> extends AnalysisEventListener<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonExcelListener.class);
    /**
     * excel格式正确数据
     */
    private final List<T> excelList = new ArrayList<>();

    /**
     * 采用默认map大小16，一般导入的时候很少大于12列
     */
    private final Map<Integer, String> columnMap = new HashMap<>();
    /**
     * 采用默认map大小16，一般导入的时候很少大于12列
     */
    private Map<Object, Object> headMap = new HashMap<>();

    /**
     * 最大导入数据量
     */
    private static final int MAX_IMPORT_SIZE = 20000;

    private static final String NOT_EMPTY_STRING = "不能为空";

    @Override
    public void invoke(T entity, AnalysisContext analysisContext) {
        if (excelList.size() > MAX_IMPORT_SIZE) {
            throw new ExcelAnalysisException("单次导入数据不能超过2万条");
        }
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(entity));
        DefaultXlsxReadContext context = (DefaultXlsxReadContext) analysisContext;
        // excel第一行
        Integer currentRowNum = context.getCurrentRowNum() + 1;
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            ImportExcelProperty annotation = field.getAnnotation(ImportExcelProperty.class);
            if (Objects.nonNull(annotation)) {
                columnMap.put(annotation.index(), annotation.name());
            }
            field.setAccessible(true);
            Object fieldValue;
            try {
                fieldValue = field.get(entity);
            } catch (IllegalAccessException e) {
                throw new ExcelAnalysisException("解析模板出错，请传入正确格式的excel模板");
            }
            // 字段必填，空数据校验
            if (annotation.required() && Objects.isNull(fieldValue)) {
                List<String> errorInfoList = entity.getErrorInfo();
                if (CollectionUtils.isEmpty(errorInfoList)) {
                    errorInfoList = new LinkedList<>();
                }
                entity.setRowIndex(currentRowNum);
                errorInfoList.add(annotation.name() + NOT_EMPTY_STRING);
                entity.setErrorInfo(errorInfoList);
            }
        }
        excelList.add(entity);
    }

    @Override
    public void invokeHeadMap(Map headMap, AnalysisContext context) {
        this.headMap = headMap;
        LOGGER.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (!Objects.equals(this.headMap, this.columnMap)) {
            throw new ExcelAnalysisException("文件模板错误");
        }
        if (CollectionUtils.isEmpty(excelList)) {
            throw new ExcelAnalysisException("文件内容为空");
        }
//        saveData();
        LOGGER.info("所有数据解析完成！");
    }


    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", excelList.size());
        LOGGER.info("存储数据库成功！");
    }

    public List<T> getExcelList() {
        return excelList;
    }


    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        String path = Objects.requireNonNull(CommonExcelListener.class.getResource("/")).getPath();
        String filePath = path + "demo" + File.separator + "demo.xlsx";
        CommonExcelListener<UploadData> listener = new CommonExcelListener<>();
        EasyExcel.read(new FileInputStream(filePath), UploadData.class, listener).sheet().doRead();
        List<UploadData> excelList = listener.getExcelList();
        System.out.println(Arrays.toString(excelList.toArray()));
        LOGGER.info("excelList:{}", JSON.toJSONString(excelList, SerializerFeature.WriteNullStringAsEmpty));
    }
}
