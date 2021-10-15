package com.example.mybatis.interceptor;

import java.lang.annotation.*;

/**
 * @author junjieLuo
 * 配合类QueryOrderInterceptor使用
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FillAuthCondition {
    /**
     *  是否启用拦截
     */
    boolean intercept() default  true;

    /**
     * 需要过滤条件的表
     */
    String table() default "";

    /**
     * table中的需要过滤的字段
     */
    String column() default  "org_id";
}
