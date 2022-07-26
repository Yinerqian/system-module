package com.celi.system.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Changaowen
 * @Date: 2021/12/6 16:17
 */
@Data
public class RolePermissionPrimaryKey implements Serializable {

    public Integer roleId;

    public Integer permissionId;
}
