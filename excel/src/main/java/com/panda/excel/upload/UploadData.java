package com.panda.excel.upload;

import com.panda.excel.base.CommonExcelProperty;
import com.panda.excel.base.ImportExcelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 基础数据类
 * @author Administrator
 */
public class UploadData extends CommonExcelProperty {

    @ImportExcelProperty(required = true, index = 0, name = "字符串标题")
    private String string;

    @ImportExcelProperty(required = true, index = 1, name = "日期标题")
    private Date date;

    @ImportExcelProperty(required = true, index = 2, name = "数字标题")
    private BigDecimal doubleData;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getDoubleData() {
        return doubleData;
    }

    public void setDoubleData(BigDecimal doubleData) {
        this.doubleData = doubleData;
    }

    @Override
    public String toString() {
        return "UploadData{" +
                "string='" + string + '\'' +
                ", date=" + date +
                ", doubleData=" + doubleData +
                '}';
    }
}
