package com.panda.excel.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public abstract class CommonExcelProperty implements Serializable {

    /**
     * 错误信息 key：行号  String：错误信息
     */
    private Map<Integer, String> errorInfoMap;

    /**
     * 错误信息
     */
    private List<String> errorInfoList;

    /**
     * excel行 从1开始
     */
    private Integer rowIndex;

    /**
     * 所有字段都为空
     */
    private Boolean isAllBlank;

    public Map<Integer, String> getErrorInfoMap() {
        return errorInfoMap;
    }

    public void setErrorInfoMap(Map<Integer, String> errorInfoMap) {
        this.errorInfoMap = errorInfoMap;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public List<String> getErrorInfoList() {
        return errorInfoList;
    }

    public void setErrorInfoList(List<String> errorInfoList) {
        this.errorInfoList = errorInfoList;
    }

    public Boolean getAllBlank() {
        return isAllBlank;
    }

    public void setAllBlank(Boolean allBlank) {
        isAllBlank = allBlank;
    }
}
