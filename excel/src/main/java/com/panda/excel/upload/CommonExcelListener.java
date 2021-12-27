package com.panda.excel.upload;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.panda.excel.base.BaseService;
import com.panda.excel.base.BaseValidateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Administrator
 */
public class CommonExcelListener<T> extends AnalysisEventListener<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonExcelListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<Object> list = new ArrayList<Object>();

    private BaseService baseService;

    private BaseValidateService baseValidateService;

    @Override
    public void invoke(T entity, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(entity));
        list.add(entity);
        Class<?> clazz = entity.getClass();

//        ParameterizedType type = (ParameterizedType)clazz.getGenericSuperclass();
        LOGGER.info(" entity annotations:{}", JSON.toJSONString(clazz));
        LOGGER.info(" entity annotations:{}", JSON.toJSONString(clazz.getDeclaredFields()));
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            LOGGER.info("annotations:{}", JSON.toJSONString(annotations));
//            ValidExcelProperty annotation = field.getAnnotation(ValidExcelProperty.class);
//            boolean nullable = annotation.nullable();
//            LOGGER.info("nullable:{}", JSON.toJSONString(nullable));
        }
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void invokeHeadMap(Map headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public static void main(String[] args) throws FileNotFoundException {

        String path = Objects.requireNonNull(CommonExcelListener.class.getResource("/")).getPath();
        System.out.println(path);

        String filePath = path+"demo"+File.separator+"demo.xlsx";
        EasyExcel.read(new FileInputStream(filePath), UploadData.class, new CommonExcelListener<UploadData>()).sheet().doRead();
    }
}
