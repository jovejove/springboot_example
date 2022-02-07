package com.panda.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.panda.excel.base.BaseService;
import com.panda.excel.base.RedisKeyEnum;
import com.panda.excel.upload.UploadData;
import com.panda.excel.upload.UploadDataErrorModule;
import com.panda.excel.upload.UploadDataListener;
import com.panda.util.EasyExcelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {


    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private BaseService baseService;

    @PostMapping("/import")
    public Object importData(@RequestParam(value = "file") MultipartFile param) throws IOException {

        UploadDataListener uploadDataListener = new UploadDataListener(baseService, redisTemplate);
        EasyExcel.read(param.getInputStream(), UploadData.class, uploadDataListener).sheet().doRead();
        return uploadDataListener;
    }

    @PostMapping("/error")
    public void exportErrorData(HttpServletResponse response) throws Exception {
        List<UploadDataErrorModule> excelErrorList = (List<UploadDataErrorModule>) redisTemplate.opsForValue().get(RedisKeyEnum.EXCEL_ERROR_LIST_DEFAULT.getId());
        EasyExcelUtil.writeExcel(response,excelErrorList,"test","testSheet",UploadDataErrorModule.class);
    }
}
