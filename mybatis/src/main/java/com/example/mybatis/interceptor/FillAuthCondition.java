package com.example.mybatis.interceptor;

import java.lang.annotation.*;

/**
 * @author junjieLuo
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FillAuthCondition {
    boolean intercept() default  true;
    String filterField() default  "org_id";
    String tableName() default "";
}
