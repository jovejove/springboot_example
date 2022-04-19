package com.example.redis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Administrator
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IpLimiter {
    /**
     * 单位时间限制通过请求数
     */
    long limit() default 10;

    /**
     * 单位时间，单位秒
     */
    long time() default 1;

    /**
     * 达到限流提示语
     */
    String message();

}