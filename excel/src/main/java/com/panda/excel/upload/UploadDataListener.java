package com.panda.excel.upload;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.panda.excel.base.BaseService;
import com.panda.excel.base.RedisKeyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
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
@Service
public class UploadDataListener extends CommonExcelListener<UploadData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadDataListener.class);

    @Resource
    private BaseService<UploadData> baseService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public UploadDataListener() {

    }

//    @Override
//    public void doValidLocalRepeatData() {
//        LOGGER.info("super doLocalRepeatDataValid");
//        super.doValidLocalRepeatData();
//        LOGGER.info("doLocalRepeatDataValid");
//    }

    @Override
    public void doValidBusinessData() {
        List<UploadData> excelList = this.getExcelList();
        LOGGER.info("doBusinessDataValid:{}", JSON.toJSONString(excelList, SerializerFeature.PrettyFormat));
    }


    @Override
    public void doSaveData() {
        List<UploadData> excelErrorList = this.getExcelErrorList();
        redisTemplate.opsForValue().set(RedisKeyEnum.EXCEL_ERROR_LIST_DEFAULT.getId(), excelErrorList);
        List<UploadData> excelList = this.getExcelList();
        LOGGER.info("{}条数据，开始存储数据库！", excelList.size());
        baseService.saveOrUpdateBatch(excelList, excelList.size());
        LOGGER.info("存储数据库成功！");
    }

    public static void exportData() throws FileNotFoundException {
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


    public static void main(String[] args) throws FileNotFoundException {
        String path = Objects.requireNonNull(CommonExcelListener.class.getResource("/")).getPath();

        String filePath = path + "demo" + File.separator + "demo.xlsx";
        UploadDataListener listener = new UploadDataListener();
        EasyExcel.read(new FileInputStream(filePath), UploadData.class, listener).sheet().doRead();

//        List<UploadData> excelList = listener.getExcelList();
//        System.out.println(Arrays.toString(excelList.toArray()));
//        LOGGER.info("excelList:{}", JSON.toJSONString(excelList, SerializerFeature.WriteNullStringAsEmpty));

        exportData();
    }
}
