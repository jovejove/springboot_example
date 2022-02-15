package com.example;

/**
 * @author Administrator
 */

public enum ElasticJobType {
    /**
     * HTTP
     */
    HTTP("HTTP","HTTP"),
    /**
     * 脚本
     */
    SCRIPT("SCRIPT","脚本");

    private String id;

    private String desc;

    ElasticJobType(String id, String desc) {
        this.id = id;
        this.desc = desc;
    }

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
