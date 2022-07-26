package com.celi.system.entity;

import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.util.List;

@Table(name = "sys_role")
@Entity
@Data
@ToString
@Log4j2
public class Role extends BaseCreateBy{

    /**
     * 角色id
     */
    @Id
    @Column(name = "ROLE_ID", updatable = false)
    private Integer roleId;

    /**
     * 角色名称
     */
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * 角色编码
     */
    @Column(name = "ROLE_CODE")
    private String roleCode;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;


    /**
     *  前端添加角色的时候 可以新增用户
     */
    @Transient
    private List<User> userList;

    @Transient
    private List<Permission> permissionList;

}
