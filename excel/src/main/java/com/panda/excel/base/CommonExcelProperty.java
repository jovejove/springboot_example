package com.panda.excel.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public abstract class CommonExcelProperty implements Serializable {

    private Map<Integer, String> errorInfoMap;

    private List<String> errorInfo;

    private Integer rowIndex;

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

    public List<String> getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(List<String> errorInfo) {
        this.errorInfo = errorInfo;
    }
}
