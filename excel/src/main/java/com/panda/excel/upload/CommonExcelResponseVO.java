package com.panda.excel.upload;

import com.panda.excel.base.CommonExcelProperty;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * 通用类，导入后，返回给前端的json对象 展示每条行错误 名称相同也不合并
 * @author Administrator
 */
public class CommonExcelResponseVO<E extends CommonExcelProperty> implements Serializable {

    private Integer allCount;
    private Integer failedCount;
    private Integer successCount;
    private String errorFileUrl;
    /**
     * 展示每一条错误，名称相同不合并
     */
    private List<CommonErrorExcelEntity> errors = new LinkedList<>();

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }

    public Integer getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(Integer failedCount) {
        this.failedCount = failedCount;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public String getErrorFileUrl() {
        return errorFileUrl;
    }

    public void setErrorFileUrl(String errorFileUrl) {
        this.errorFileUrl = errorFileUrl;
    }

    public List<CommonErrorExcelEntity> getErrors() {
        return errors;
    }

    public void setErrors(List<CommonErrorExcelEntity> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "CommonExcelResponseVO{" +
                "allCount=" + allCount +
                ", failedCount=" + failedCount +
                ", successCount=" + successCount +
                ", errorFileUrl='" + errorFileUrl + '\'' +
                ", errors=" + errors +
                '}';
    }
}
