package com.panda.excel.upload;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 模板的读取类
 *
 * @author Administrator
 */
public class UploadDataListenerB extends CommonExcelListener<UploadData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonExcelListener.class);

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        super.doAfterAllAnalysed(analysisContext);
        List<UploadData> excelList = super.getExcelList();
        LOGGER.info("excelList{}:", JSON.toJSONString(excelList));

    }

    public static void exportDta() throws FileNotFoundException, UnsupportedEncodingException {
        List<UploadData> dataList = new LinkedList<>();
        UploadData uploadData;
        for (int i = 0; i < 3; i++) {
            uploadData = new UploadData();
            uploadData.setString("string" + i);
            uploadData.setDate(new Date());
            uploadData.setDoubleData(new BigDecimal(i));
            dataList.add(uploadData);
        }
        ExcelWriterBuilder builder = EasyExcel.write();
        List<List<String>> head = new LinkedList<>();
        head.add(Collections.singletonList("string"));
        head.add(Collections.singletonList("date"));
        head.add(Collections.singletonList("bigDecimal"));
        String path = "C:\\Users\\Administrator\\Downloads";
//        String fileName = URLEncoder.encode("文件名.xlsx", StandardCharsets.UTF_8.toString());
        String fileName = "文件名.xlsx";
        String filePath = path + File.separator + fileName;

        File file = new File(filePath);
        builder.head(head).autoCloseStream(true).file(new FileOutputStream(file)).sheet().doWrite(dataList);
//        builder.he
    }


    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        String path = Objects.requireNonNull(CommonExcelListener.class.getResource("/")).getPath();

        String filePath = path + "demo" + File.separator + "demo.xlsx";
        UploadDataListenerB listener = new UploadDataListenerB();
        EasyExcel.read(new FileInputStream(filePath), UploadData.class, listener).sheet().doRead();
//        List<UploadData> excelList = listener.getExcelList();
//        System.out.println(Arrays.toString(excelList.toArray()));
//        LOGGER.info("excelList:{}", JSON.toJSONString(excelList, SerializerFeature.WriteNullStringAsEmpty));

        exportDta();
    }
}
