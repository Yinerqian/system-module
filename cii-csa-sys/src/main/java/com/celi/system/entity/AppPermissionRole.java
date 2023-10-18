package com.celi.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppPermissionRole {

    /**
     *  租户id
     */
    private Integer tenantId;

    /**
     *  角色id
     */
    private Integer roleId;

    /**
     *  应用id
     */
    private Integer appId;

    /**
     *  权限id
     */
    private Integer permissionId;

}
