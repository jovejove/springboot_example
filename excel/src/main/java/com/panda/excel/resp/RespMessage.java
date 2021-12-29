package com.panda.excel.resp;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 类说明:接口相应消息对象
 *
 * @author huangshengbao
 * @date 2018年7月19日  新建
 **/

public class RespMessage implements Serializable {

    private static final long serialVersionUID = -6166903037581212731L;

    /**
     * 相应编码：如1001表示参数为空，0表示系统异常，1表示请求正常。
     */
    private String code;

    /**
     * 相应提示信息：如1001错误码，对应返回的msg：用户ID为空。
     */
    private String msg;

    /**
     * 以数组的形式相应提示信息：如1001错误码，对应返回的msg：用户ID为空。
     */
    private String[] arrayMsg;

    public String[] getArrayMsg() {
        return arrayMsg;
    }

    public void setArrayMsg(String[] arrayMsg) {
        this.arrayMsg = arrayMsg;
    }

    /**
     * 接口状态：0正常，1异常。
     */
    private int status = 0;

    /**
     * 接口正常情况下，应该返回的业务数据，如用户登录成功的相应信息
     */
    private Object data;

    /**
     * 扩展返回参数
     */
    private Object extension;

    private Object data2;

    private Object errorData;

    public Object getErrorData() {
        return errorData;
    }

    public void setErrorData(Object errorData) {
        this.errorData = errorData;
    }

    public void setResult(ErrorCodeEnum result) {
        this.code = result.getCode();
        this.msg = result.getMsg();
    }

    public Object getData2() {
        return data2;
    }

    public void setData2(Object data2) {
        this.data2 = data2;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getExtension() {
        return extension;
    }

    public void setExtension(Object extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "RespMessage [code=" + code + ", msg=" + msg + ", arrayMsg=" + Arrays.toString(arrayMsg) + ", status="
                + status + ", data=" + data + ", extension=" + extension + ", data2=" + data2 + "]";
    }

}


