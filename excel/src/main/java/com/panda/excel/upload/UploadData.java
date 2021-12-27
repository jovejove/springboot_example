package com.panda.excel.upload;

import com.panda.excel.base.ValidExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 基础数据类
 *
 **/
@Data
public class UploadData {

    @ValidExcelProperty(unique = true,required = true)
    private String string;

    @ValidExcelProperty(unique = true,required = false)
    private Date date;

    @ValidExcelProperty(unique = false,required = false)
    private Double doubleData;

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

    public Double getDoubleData() {
        return doubleData;
    }

    public void setDoubleData(Double doubleData) {
        this.doubleData = doubleData;
    }
}
