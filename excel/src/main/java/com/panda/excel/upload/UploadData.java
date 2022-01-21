package com.panda.excel.upload;

import com.panda.excel.base.CommonExcelProperty;
import com.panda.excel.base.ImportExcelProperty;
import com.panda.excel.base.Pattern;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 基础数据类
 * @author Administrator
 */
public class UploadData extends CommonExcelProperty {


    @ImportExcelProperty(unique = true,required = true, index = 0, name = "字符串标题")
    private String string;

    @ImportExcelProperty(unique = true,required = true, index = 1, name = "日期标题")
    private Date date;

    @Pattern(regexp = "^\\d+\\.\\d?$")
    @ImportExcelProperty(required = true, index = 2, name = "数字标题")
    private BigDecimal doubleData;

    @ImportExcelProperty(index = 3, name = "姓名")
    private String test;

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

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
