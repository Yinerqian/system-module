package com.celi.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;

/**
 * @project: cii
 * @description: 用户角色关系类
 */
@Table(name = "jimu_sys_role_permission")
@Entity
@IdClass(RolePermissionPrimaryKey.class)
@Data
@ToString
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission {

    /**
     * 角色id
     */
    @Id
    @Column(name = "ROLE_ID", nullable = false)
    private String roleId;

    @Id
    @Column(name = "PERMISSION_ID", nullable = false)
    private String permissionId;


}
