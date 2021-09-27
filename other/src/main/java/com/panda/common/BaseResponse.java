package com.panda.common;


import com.panda.enums.ResponseStatusEnum;
import com.panda.exception.FormatException;

import java.io.Serializable;

/**
 * @param <T>
 * @author guanjian
 */
public class BaseResponse<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String error) {
        // 支持多语言
        this.message = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseResponse() {
    }

    public BaseResponse<T> success(T t) {
        setCode(ResponseStatusEnum.SUCCESS.getCode());
        setMessage(ResponseStatusEnum.SUCCESS.getValue());
        setData(t);
        if (t != null && t instanceof String) {
            String message = t.toString();
            setData((T) t);
        }
        return this;
    }

    public BaseResponse<T> error(String msg, int code) {
        setCode(code);
        setMessage(msg);
        return this;
    }

    public BaseResponse<T> error(String msg) {
        setCode(ResponseStatusEnum.ERROR.getCode());// 错误码
        setMessage(msg);
        return this;
    }

    @Override
    public String toString() {
        return "BaseResponse [code=" + code + ", " + (message != null ? "message=" + message + ", " : "")
                + (data != null ? "data=" + data : "") + "]";
    }


    public static BaseResponse error(Exception e) {
        BaseResponse r = new BaseResponse();
        if (e instanceof FormatException) {
            FormatException fe = (FormatException) e;
            r.setCode(fe.getCode());// 错误码
            r.setMessage(fe.getMessage());
        } else {
            r.setCode(0);// 错误码
            r.setMessage("系统异常");
        }
        return r;
    }


}
