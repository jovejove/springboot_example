package com.example.aspect.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: ljj
 * @date: 2022/4/20
 * @description:
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OperationRecordDataInfo {

    String field() default "";
//    String studentId() default "";
//    String studentName() default "";
//    String studentPhone() default "";
//    String studentCardNo() default "";
}
