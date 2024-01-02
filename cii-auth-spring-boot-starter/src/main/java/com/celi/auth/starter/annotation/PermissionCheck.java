package com.celi.auth.starter.annotation;

import java.lang.annotation.*;

/**
 * @title: PermissionCheck
 * @project: cii-auth-spring-boot-starter
 * @author: lihq
 * @date: 2023/11/21 9:27
 * @version: v1.0
 * @description: 判断是否有相应的权限
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionCheck {

    /**
     * 传入权限编码列表
     * @return
     */
    String[] operate() default "";

    /**
     * 传入角色列表
     * @return
     */
    String[] role() default "";

}
