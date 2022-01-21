package com.panda.excel.base;

/**
 * @author Administrator
 */

public enum RedisKeyEnum {
    /**
     *
     */
    EXCEL_ERROR_LIST_DEFAULT("EXCEL_ERROR_LIST_DEFAULT", "excel错误数据列表"),
    /**
     *
     */
    EXCEL_ERROR_LIST_TEST("EXCEL_ERROR_LIST_TEST", "excel错误数据列表");

    RedisKeyEnum(String id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    private String id;
    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
