package com.panda.demo.enums;

/**
 * date: 2018年12月4日 下午2:31:11 <br/>
 *
 * @author guanjian
 */
public enum ResponseStatusEnum {
    // 原枚举属性
    //    ERROR(0, "error"), SUCCESS(1, "success"), PARM_CHECK_ERROR(2, "参数校验错误！"), SESSION_TIMEOUT(3, "session已过期！"), ACCESS_DENIED(4, "无权访问！");

    // 修改符合多语言的value，跟properties中key对应一致
    ERROR(0, "api.response.code.fail"),
    SUCCESS(1, "api.response.code.success"),
    PARM_CHECK_ERROR(2, "api.response.code.paramCheckError"),
    SESSION_TIMEOUT(3, "api.response.code.sessionTimeout"),
    ACCESS_DENIED(4, "api.response.code.accessDenied"),
    UNKNOWN_ERROR(5, "api.response.code.unknownError");

    private String value;

    private Integer code;

    private ResponseStatusEnum(Integer code, String value) {
        this.value = value;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
