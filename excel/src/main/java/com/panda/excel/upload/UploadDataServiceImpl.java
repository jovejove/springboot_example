package com.panda.excel.upload;

import com.panda.excel.base.BaseServiceImpl;

import java.util.List;

/**
 * @author Administrator
 */
public class UploadDataServiceImpl extends BaseServiceImpl<UploadDataMapper,UploadData> implements UploadDataService {

    public void saveData(List<UploadData> list) {
        // 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入
        this.saveBatch(list);
    }
}
