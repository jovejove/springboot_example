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
public @interface Pattern {

    /**
     * @return the regular expression to match
     */
    String regexp();

    /**
     * @return the error message template
     */
    String message() default "格式不正确";

}
