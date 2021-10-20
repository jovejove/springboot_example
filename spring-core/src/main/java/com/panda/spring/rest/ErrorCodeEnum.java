package com.panda.spring.rest;

/**
* 类说明:系统错误码枚举 
*
* @author huangshengbao
* @date 2018年7月19日  新建
**/

public enum ErrorCodeEnum {
	
	//系统异常
    SYS_ERROR("SYS001", "系统异常"),
    
	//网络链接超时
    CONNECTION_TIMEOUT("CON001", "网络超时"),
    //网络链接异常
    CONNECTION_ERROR("CON002", "网络链接异常"),
    
    //请求成功
    REQUEST_SUCCESS("REQ001", "请求成功"),
    //请求失败
    REQUEST_FAIL("REQ002", "请求失败"),
    
    //请求参数为空
    REQUEST_PARAMS_BLANK("REQ003", "请求参数为空"),
    //请求参数错误
    REQUEST_PARAMS_ERROR("REQ004", "请求参数错误"),
    //请求参数错误
    REQUEST_RECORD_EXIST("REQ005", "记录已经存在"),
    
    REQUEST_RECORD_UNCHANGEABLE("REQ006", "不可修改非同一事业部成员或上级的等级"),

    //登入失败 输入验证码
    REQUEST_RECORD_VALIDATECODE("REQ007", "登入失败 输入验证码"),
    REQUEST_RECORD_NO_EXIST("REQ008", "该记录不存在"),

    /**
     * 用户登录相关错误码
     */
    USER_LOGIN_NEED_BIND_DEVICE("LOGIN001", "为保障你的学习体验，需要绑定当前设备才能学习使用（绑定后不可在其他同类型设备登录）"),
    USER_LOGIN_DEVICE_HAS_BINDED("LOGIN002", "账号已绑定其他设备，请用原设备登录或联系老师"),

    
    //用户未登录
    USER_OUTLINE("SEN001", "用户未登录"),
    
    USER_TOKEN_NULL("SEN002", "用户token为空"),
    
    //用户已在别的地方登录，强退
    USER_OTHERLOGIN("SEN003", "用户已在别的地方登录"),
    
    //重复注册
    USER_IS_REGISTER("SEN004","用户已注册，请直接登录"),
    
    //未知错误
    OTHER_ERROR("OTHER001", "其他错误"),
	
    //客户端未登录
	CLIENT_NOT_LOGIN("CLIENT001","客户端未登录！"),
	//极光连接异常，无法下发消息给客户端
	CLIENT_CONNECTION_ERROR("CLIENT002","连接失败，请检查客户端是否正常连接！"),
	//触发拨打电话失败
	CALL_FAIL("CALL001","呼叫失败，请稍后再试！"),
	//触发拨打电话失败
	CALL_FAIL_ANOTHER("CALL001","呼叫失败，请保持APP处于登录状态！"),
	//呼叫请求限制
	CALL_REQUEST_LIMIT("CALL002","呼叫过于频繁，请稍后再试！"),

	//排班日期无效
	DUTYTIME_INVALIDATE("DUT001", "所选排班日期无效!"),
	
	//存在冲突的数据
	DATE_COLLISION("DATA001", "数据冲突"),
	//数据过大
	TOO_LARGE("DATA002", "【文件过大，下载失败】"),
	
	//数据过大
	AUTH_FAIL("AUTH001", "签名检验失败"),
	
	//非法IP访问系统错误提示
	REQUEST_REFUSE("REQ10001", "非法请求，不允许访问！"),
    //数据过大
    AUTH_LOGIN_FAIL("REQ0011", "用户没有合法的登录权限")
	;

    private String code;
    private String msg;

    private ErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getResponseMsg(String code) {
        for (ErrorCodeEnum wrapperEnumError : ErrorCodeEnum.values()) {
            if (code.equals(wrapperEnumError.getCode())) {
                return wrapperEnumError.getMsg();
            }
        }
        return OTHER_ERROR.getMsg();
    }

}


