package com.panda.spring.rest;


/**
 * 
 * 统一返回格式
 * 
 * @author huangjialiang
 *
 */
public class RespHandler {
	
	/**
	 * 
	 * 判断当前下层的返回是否成功
	 * 
	 * @param resp
	 */
	public static boolean isSucc(RespMessage resp) {
		if(resp.getStatus() == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 
	 * 调用rest接口成功统一返回
	 * 
	 * @param data
	 * @return
	 */
	public static RespMessage sucess(Object data) {
		RespMessage resp = new RespMessage();
		resp.setCode(ErrorCodeEnum.REQUEST_SUCCESS.getCode());
		resp.setMsg(ErrorCodeEnum.REQUEST_SUCCESS.getMsg());
		resp.setStatus(0);
		resp.setData(data);
		return resp;
	}

	public static RespMessage success(Object data) {
		RespMessage resp = new RespMessage();
		resp.setCode(ErrorCodeEnum.REQUEST_SUCCESS.getCode());
		resp.setMsg(ErrorCodeEnum.REQUEST_SUCCESS.getMsg());
		resp.setData(data);
		return resp;
	}
	/**
	 * 
	 * 调用rest接口成功统一返回
	 * 
	 * @return
	 */
	public static RespMessage success() {
		return sucess(null);
	}
	
	/**
	 * 
	 * 调用rest接口成功统一返回
	 * 
	 * @param data
	 * @return
	 */
	public static RespMessage error(Object data) {
		RespMessage resp = new RespMessage();
		resp.setCode(ErrorCodeEnum.REQUEST_SUCCESS.getCode());
		resp.setMsg(ErrorCodeEnum.REQUEST_SUCCESS.getMsg());
		resp.setStatus(0);
		resp.setData(data);
		return resp;
	}
	
	/**
	 * 
	 * 调用rest接口成功统一返回
	 * 
	 * @param data
	 * @return
	 */
	public static RespMessage sucess(Object data, Object extension) {
		RespMessage resp = new RespMessage();
		resp.setCode(ErrorCodeEnum.REQUEST_SUCCESS.getCode());
		resp.setMsg(ErrorCodeEnum.REQUEST_SUCCESS.getMsg());
		resp.setExtension(extension);
		resp.setStatus(0);
		resp.setData(data);
		return resp;
	}
	
	/**
	 * 
	 * 调用rest接口成功统一返回
	 * 
	 * @param msg
	 * @return
	 */
	public static RespMessage sucessMsg(String msg) {
		RespMessage resp = new RespMessage();
		resp.setCode(ErrorCodeEnum.REQUEST_SUCCESS.getCode());
		resp.setMsg(msg);
		resp.setStatus(0);
		return resp;
	}
	
	/**
	 *
	 * 调用rest接口失败统一返回
	 *
	 * @param msg
	 * @return
	 */
	public static RespMessage failure(String msg) {
		RespMessage resp = new RespMessage();
		resp.setCode(ErrorCodeEnum.REQUEST_FAIL.getCode());
		resp.setStatus(1);
		resp.setMsg(msg);
		return resp;
	}
	
	/**
	 * 调用rest接口失败统一返回
	 * @param msg
	 * @param data
	 * @return
	 */
	public static RespMessage failure(String msg,Object data) {
		RespMessage resp = new RespMessage();
		resp.setCode(ErrorCodeEnum.REQUEST_FAIL.getCode());
		resp.setStatus(1);
		resp.setMsg(msg);
		resp.setData(data);
		return resp;
	}

	/**
	 * 调用rest接口失败统一返回
	 * @return
	 */
	public static RespMessage failureWithErrorCode(ErrorCodeEnum codeEnum) {
		RespMessage resp = new RespMessage();
		resp.setCode(codeEnum.getCode());
		resp.setMsg(codeEnum.getMsg());
		resp.setStatus(1);
		return resp;
	}

	public static RespMessage failureWithErrorData(String msg,Object data) {
		RespMessage resp = new RespMessage();
		resp.setCode(ErrorCodeEnum.REQUEST_FAIL.getCode());
		resp.setStatus(1);
		resp.setMsg(msg);
		resp.setErrorData(data);
		return resp;
	}


	/**
	 *
	 * 调用rest接口失败返回字符串数组
	 *
	 * @param msg
	 * @return
	 */
	public static RespMessage fail(String[] msg) {
		RespMessage resp = new RespMessage();
		resp.setCode(ErrorCodeEnum.REQUEST_FAIL.getCode());
		resp.setStatus(1);
		resp.setArrayMsg(msg);
		return resp;
	}

	/**
	 * 
	 * 调用rest接口参数为空返回
	 * 
	 * @param msg
	 * @return
	 */
	public static RespMessage paramBlank(String msg) {
		RespMessage resp = new RespMessage();
		resp.setCode(ErrorCodeEnum.REQUEST_PARAMS_BLANK.getCode());
		resp.setStatus(1);
		resp.setMsg(msg);
		return resp;
	}
	
	/**
	 * 
	 * 调用rest接口参数错误返回
	 * 
	 * @param msg
	 * @return
	 */
	public static RespMessage paramError(String msg) {
		RespMessage resp = new RespMessage();
		resp.setCode(ErrorCodeEnum.REQUEST_PARAMS_ERROR.getCode());
		resp.setStatus(1);
		resp.setMsg(msg);
		return resp;
	}
	
	/**
	 * 提示存在记录数据
	 * @param msg
	 * @return
	 */
	public static RespMessage dataExist(String msg) {
		RespMessage resp = new RespMessage();
		resp.setCode(ErrorCodeEnum.REQUEST_RECORD_EXIST.getCode());
		resp.setStatus(1);
		resp.setMsg(msg);
		return resp;
	}
	
	/**
	 * 
	 *手工设置返回信息
	 * 
	 * @param errorCode
	 * @return
	 */
	public static RespMessage setErrorCode(ErrorCodeEnum errorCode) {
		RespMessage resp = new RespMessage();
		resp.setCode(errorCode.getCode());
		resp.setStatus(1);
		resp.setMsg(errorCode.getMsg());
		return resp;
	}
	
	/**
	 * 
	 *手工设置返回信息
	 * 
	 * @param errorCode
	 * @param msg
	 * @return
	 */
	public static RespMessage setErrorCodeMsg(ErrorCodeEnum errorCode,String msg) {
		RespMessage resp = new RespMessage();
		resp.setCode(errorCode.getCode());
		resp.setStatus(1);
		resp.setMsg(msg);
		return resp;
	}
	
	/**
	 * dubbo传输文件过大
	 * @param tooLarge
	 * @return
	 */
	public static RespMessage dataError(ErrorCodeEnum tooLarge) {
		RespMessage resp = new RespMessage();
		resp.setCode(tooLarge.getCode());
		resp.setStatus(1);
		resp.setMsg(tooLarge.getMsg());
		return resp;
	}

	public static RespMessage failureAuthLogin(String msg) {
		RespMessage resp = new RespMessage();
		resp.setCode(ErrorCodeEnum.AUTH_LOGIN_FAIL.getCode());
		resp.setStatus(1);
		resp.setMsg(msg);
		return resp;
	}
	
}
