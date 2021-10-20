package com.panda.spring.config;

import com.panda.spring.rest.RespHandler;
import com.panda.spring.rest.RespMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.List;

/**
 * @author Administrator
 * 全局异常处理
 * 参考 ResponseEntityExceptionHandler
 */
@RestControllerAdvice
public class GlobalResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalResponseEntityExceptionHandler.class);

    /**
     * 异常调用方法
     * @param ex
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RespMessage handleException(Exception ex) {
        String message = ex.getMessage();

        if (ex instanceof MethodArgumentNotValidException) {
            LOGGER.error(message);
            return error(((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors());
        }
        //校验异常处理
        if (ex instanceof BindException) {
            LOGGER.error(message);
            return error((BindException) ex);
        }
        if (ex instanceof MissingServletRequestPartException) {
            LOGGER.error(message, ex);
            return RespHandler.failure("必须选择上传文件");
        }
        if (ex instanceof MaxUploadSizeExceededException) {
            return RespHandler.failure("文件大小不能超过10M");
        }
        if (ex instanceof MultipartException) {
            LOGGER.error(message, ex);
            return RespHandler.failure("文件上传失败");
        }
        //不采集校验异常
        LOGGER.error(message, ex);
        //CAT采集异常日志到Logview
//		Cat.getProducer().logError(ex);

        return exception(ex);
    }

    /**
     * 调用rest接口异常处理
     * @param ex
     */
    public RespMessage exception(Exception ex) {
        RespMessage respMessage = null;

        String message = getExceptionMessage(ex.getMessage());

        if (ex instanceof HttpMessageNotReadableException) {
            //请求参数异常
            respMessage = RespHandler.paramError("请求参数错误");
            respMessage.setData(message);
        } else {
            respMessage = RespHandler.failure(message);
        }

        return respMessage;
    }


    /**
     * 获取异常信息(只截取300个字符返回)
     *
     * @param message
     */
    private String getExceptionMessage(String message) {
        String errorMsg = "服务器开小差了,请稍后重试";
        if (message == null) {
            return errorMsg;
        }
        int length = message.length();
        if (length > 300) {
            message = errorMsg;
        }
        return message;
    }

    /**
     * 处理校验异常
     * @param exception
     */
    private RespMessage error(BindException exception) {
        List<ObjectError> errors = exception.getAllErrors();
        return error(errors);
    }

    /**
     * 处理校验异常
     *
     * @param errors
     */
    private RespMessage error(List<ObjectError> errors) {

        StringBuilder sb = new StringBuilder();
        for (ObjectError e : errors) {
            sb.append(e.getDefaultMessage());
            sb.append(";");
        }
        return RespHandler.failure(sb.toString());
    }
}
