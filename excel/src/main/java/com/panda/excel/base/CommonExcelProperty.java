package com.panda.excel.base;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

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
//    protected Map<Integer, String> errorInfoMap;

    /**
     * 错误信息
     */
    private List<String> errorInfoList;

    /**
     * 合并错误信息
     */
    private String  errorInfoOneLine;

    /**
     * excel行 从1开始
     */
    private Integer rowIndex;

    /**
     * 所有字段都为空
     */
    private boolean isAllBlank;

    /**
     * 数据重复
     */
    private boolean isRepeat;

//    /**
//     * excel所有行
//     */
//    private Integer allCount;
//    /**
//     * 没有通过校验的数据
//     */
//    private Integer failedCount;
//    /**
//     * 通过校验的数据
//     */
//    private Integer successCount;
//    /**
//     * 错误excel地址
//     */
//    private String errorFileUrl;

//    public Map<Integer, String> getErrorInfoMap() {
//        return errorInfoMap;
//    }
//
//    public void setErrorInfoMap(Map<Integer, String> errorInfoMap) {
//        this.errorInfoMap = errorInfoMap;
//    }

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

    public boolean isAllBlank() {
        return isAllBlank;
    }

    public void setAllBlank(boolean allBlank) {
        isAllBlank = allBlank;
    }

    public boolean isRepeat() {
        return isRepeat;
    }

    public void setRepeat(boolean repeat) {
        isRepeat = repeat;
    }

    public String getErrorInfoOneLine() {
        if (CollectionUtils.isNotEmpty(errorInfoList)) {
            return String.format("第【%s】行",rowIndex) + String.join(";", errorInfoList);
        }
        return errorInfoOneLine;
    }

    public void setErrorInfoOneLine(String errorInfoOneLine) {
        this.errorInfoOneLine = errorInfoOneLine;
    }


}
