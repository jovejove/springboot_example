package com.example.mybatis.interceptor;

import java.lang.annotation.*;

/**
 * @author junjieLuo
 * <p>在mapper的方法上使用<p/>
 * <p>类QueryOrderInterceptor拦截请求<p/>
 * <p>注意：请使用者先自行评估是否符合业务需求。如果不符合，那么请勿使用，同时请勿修改代码。</p>
 */
@Inherited
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
