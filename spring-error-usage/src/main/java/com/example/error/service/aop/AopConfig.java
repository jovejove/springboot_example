package com.example.error.service.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Aspect
@Service
@Slf4j
public class AopConfig {
    @Around("execution(* com.example.error.service.aop.ElectricService.pay()) ")
    public void recordPayPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("aop! Pay method time cost（ms）: " + (end - start));
    }

    @Before("execution(* com.example.error.service.aop.AdminUserService.login(..))")
    public void logAdminLogin(JoinPoint pjp) throws Throwable {
        System.out.println("aop! admin login ...");
    }


    @Before("execution(* com.example.error.service.aop.ElectricService.charge())")
    public void logBeforeMethod(JoinPoint pjp) throws Throwable {
        System.out.println("step into ->" + pjp.getSignature());
    }

    @Before("execution(* com.example.error.service.aop.ElectricService.charge()) ")
    public void checkAuthority(JoinPoint pjp) throws Throwable {
//        throw new RuntimeException("authority check failed");

        System.out.println("validating user authority");
        Thread.sleep(1000);
    }

    @Around("execution(* com.example.error.service.aop.ElectricService.doCharge(..)) ")
    public void recordPerformance(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("charge method time cost: " + (end - start));
    }




}