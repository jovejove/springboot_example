package com.example.mybatis.interceptor;

import java.lang.annotation.*;

/**
 * @author Administrator
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InterceptAnnotation {
    boolean intercept() default  true;
    String filterField() default  "org_id";
    String tableName() default "";
}
