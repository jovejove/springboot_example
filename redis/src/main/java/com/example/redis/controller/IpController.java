package com.example.redis.controller;

import com.example.redis.annotation.IpLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

@RestController
public class IpController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpController.class);
    private static final String MESSAGE = "请求失败,你的IP访问太频繁";

    //这里就不获取请求的ip,而是写死一个IP
    @RequestMapping("/iplimiter")
    @IpLimiter(limit = 5, time = 10, message = MESSAGE)
    public String sendPayment(HttpServletRequest request) throws Exception {

        Class<? extends IpController> clazz = this.getClass();
        Method method = clazz.getMethod("sendPayment", HttpServletRequest.class);
        IpLimiter ipLimiter = method.getAnnotation(IpLimiter.class);

        InvocationHandler invocationHandler = Proxy.getInvocationHandler(ipLimiter);
        Field ipAddress = invocationHandler.getClass().getDeclaredField("memberValues");
        ipAddress.setAccessible(true);
        Map memberValues = (Map) ipAddress.get(invocationHandler);
        memberValues.put("value", request.getRemoteAddr());
        String remoteAddr = request.getRemoteAddr();

        String header = request.getHeader("x-forward-ip");

        return String.format("请求成功 remoteAddr:%s,x-forward-ip ",remoteAddr,header);
    }
    @RequestMapping("/iplimiter1")
    @IpLimiter(limit = 4, time = 10, message = MESSAGE)
    public String sendPayment1(HttpServletRequest request) throws Exception {
        return "请求成功";
    }
}