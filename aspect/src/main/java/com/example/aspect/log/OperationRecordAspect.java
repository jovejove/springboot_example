package com.example.aspect.log;

import com.alibaba.fastjson.JSONObject;
import com.example.aspect.annotation.OperationRecord;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author: ljj
 * @date: 2022/4/19
 * @description:
 */
@Aspect
@Component
@Slf4j
public class OperationRecordAspect {

//    @Pointcut("execution(public * com.example.aspect.controller.ExportController.middle(..))")
    @Pointcut("@annotation(com.example.aspect.annotation.OperationRecord)")
    public void pointCut() {

    }

//    @Before(value = "pointCut()")
//    public void before(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        OperationRecord annotation = method.getAnnotation(OperationRecord.class);
//        log.info("操作描述：{}",annotation.operationDesc());
//        log.info("{} . {} : 请求参数：{}",method.getDeclaringClass().getName(),method.getName(), JSONObject.toJSONString(args));
//    }

//    @AfterReturning(value ="execution(public * com.example.aspect.controller..*.*(..))",returning = "returnObject")
//    @AfterReturning(value ="@annotation(com.example.aspect.annotation.OperationRecord)",returning = "returnObject")
//    public void after(JoinPoint joinPoint, Object returnObject) {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        log.info("{}.{} : 返回数据：{}",method.getDeclaringClass().getName(),method.getName(),JSONObject.toJSONString(returnObject));
//
//    }

    @Around(value = "@annotation(com.example.aspect.annotation.OperationRecord)")
    public void after(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info("前置 {}.{} : args：{}",method.getDeclaringClass().getName(),method.getName(),JSONObject.toJSONString(joinPoint.getArgs()));
        Object result = null;
        try {
             result = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("出异常啦。。。。。。。。。。。");
        }
        log.info("后置 {}.{} : result：{}",method.getDeclaringClass().getName(),method.getName(),JSONObject.toJSONString(result));
    }
}
