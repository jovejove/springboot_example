package com.panda.excel.upload;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.panda.excel.base.BaseService;
import com.panda.excel.base.RedisKeyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.LinkedList;
import java.util.List;

/**
 * 模板的读取类
 *
 * @author Administrator
 */
public class UploadDataListener extends CommonExcelListener<UploadData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadDataListener.class);

    private final BaseService<UploadData> baseService;

    private final RedisTemplate<String, Object> redisTemplate;

    public UploadDataListener(BaseService<UploadData> baseService, RedisTemplate<String, Object> redisTemplate) {
        this.baseService = baseService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void doValidBusinessData() {


        LOGGER.info("doBusinessDataValid:{}", JSON.toJSONString(excelList, SerializerFeature.PrettyFormat));
    }

    @Override
    public void doSaveData() {

        List<UploadData> excelErrorList = this.getExcelErrorList();
        List<UploadDataErrorModule> list = new LinkedList<>();
        for (UploadData uploadData : excelErrorList) {
            UploadDataErrorModule data = new UploadDataErrorModule();
            BeanUtils.copyProperties(uploadData,data);
            list.add(data);
        }
        redisTemplate.opsForValue().set(RedisKeyEnum.EXCEL_ERROR_LIST_DEFAULT.getId(), list);

        List<UploadData> excelList = this.getExcelList();
        LOGGER.info("{}条数据，开始存储数据库！", excelList.size());
//        baseService.saveOrUpdateBatch(excelList, excelList.size());
        LOGGER.info("存储数据库成功！");
    }
}
