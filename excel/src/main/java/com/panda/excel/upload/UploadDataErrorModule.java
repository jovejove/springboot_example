package com.panda.excel.upload;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 基础数据类 错误信息 若使用动态excel头，请注意实体字段顺序
 *
 * @author Administrator
 */
public class UploadDataErrorModule {
    @ExcelProperty(index = 0,value = "错误信息")
    private String  errorInfoOneLine;
    @ExcelProperty(index = 3,value = "数字标题")
    private BigDecimal bigDecimal;
    @ExcelProperty(index = 4,value = "姓名")
    private String test;
    @ExcelProperty(index = 1,value = "字符串标题")
    private String string;

    @DateTimeFormat(value = "yyyy-MM-dd")
    @ExcelProperty(index = 2,value = "日期标题")
    private Date date;
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

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getErrorInfoOneLine() {
        return errorInfoOneLine;
    }

    public void setErrorInfoOneLine(String errorInfoOneLine) {
        this.errorInfoOneLine = errorInfoOneLine;
    }
}
