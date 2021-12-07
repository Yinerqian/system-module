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
@Table(name = "jimu_sys_user_role")
@Entity
@IdClass(UserRolePrimaryKey.class)
@Data
@ToString
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    @Id
    @Column(name = "USER_ID", nullable = false)
    private String userId;

    /**
     * 角色id
     */
    @Id
    @Column(name = "ROLE_ID", nullable = false)
    private String roleId;


}
