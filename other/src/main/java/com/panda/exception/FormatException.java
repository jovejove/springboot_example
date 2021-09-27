package com.panda.exception;

import lombok.Getter;

/**
 * @author htpeng
 * @date 2019/7/5 0005 15:27
 */
@Getter
public class FormatException extends RuntimeException {

    private int code;
    private String message;

    public FormatException() {
        super();
    }

    public FormatException(IException e, Object... args) {
        this();
        this.code = e.getCode();
        this.message = e.getMessage();
        if (null != args && null != message) {
            this.message = String.format(this.message, args);
        }
    }

    /**
     * 提供直接返回错误信息
     *
     * @param msg
     */
    public FormatException(String msg, Object... args) {
        this();
        this.code = 0;
        this.message = msg;
        if (null != args) {
            this.message = String.format(msg, args);
        }
    }
}
