package com.panda.excel.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Administrator
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ImportExcelProperty {

    /**
     * 列是否必填
     */
    boolean required() default false;

    /**
     * 唯一标识
     */
    boolean unique() default false;

    /**
     * index不可重复
     */
    int index() default -1;

    /**
     * 列名称
     */
    String name() default "未定义名称";

    /**
     * 列值校验提示
     */
    String message() default "不能为空";

}
