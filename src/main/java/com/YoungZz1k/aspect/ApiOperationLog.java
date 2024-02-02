package com.YoungZz1k.aspect;

import java.lang.annotation.*;

/**
 * 自定义记录日志注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ApiOperationLog {

    // 功能描述
    String description() default "";
}
