package com.example.error.service.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

//省略 imports
@Aspect
@Service
@Slf4j
public class AspectService {
//  @Before("execution(* com.example.error.service.aop.ElectricService.charge()) ")
//  public void checkAuthority(JoinPoint pjp) throws Throwable {
//      System.out.println("validating user authority");
//      Thread.sleep(1000);
//  }
}