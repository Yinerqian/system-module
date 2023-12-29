package com.celi.auth.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: cii-auth-spring-boot-starter
 * @title: PermissionEntity
 * @author: lihq
 * @date: 2023/11/21 15:08
 * @version: v1.0
 * @description: 传输实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PermissionEntity {

    /**
     * 请求url(class mapping + 方法 mapping)
     */
    private String requestUrl;

    /**
     * 服务名称 (外部提供)
     */
    private String serviceName;

    /**
     * 角色集合
     */
    private String[] roleList;

    /**
     * 权限集合
     */
    private String[] permissionList;

}
