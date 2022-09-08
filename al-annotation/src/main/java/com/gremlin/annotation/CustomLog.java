package com.gremlin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: CustomLog
 * @author: gremlin
 * @version: 1.0.0
 * @description: 自定义注解的使用
 * @date: 2022/8/14 23:03
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomLog {

    /**
     * 接口解释，用于做什么
     */
    String notes() default "";

    /**
     * 接口请求地址
     */
    String reqUrl() default "";
}
