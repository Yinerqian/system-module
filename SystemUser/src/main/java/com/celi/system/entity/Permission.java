package com.celi.system.entity;

import com.celi.system.enums.PermissionTypeEnum;
import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;

@Table(name = "sys_permission")
@Entity
@Data
@ToString
@Log4j2
public class Permission extends BaseCreateBy{

    // 权限id
    @Id
    @Column(name = "PERMISSION_ID", updatable = false)
    private String permissionId;

    // 权限分组id
    @Column(name = "GROUP_ID")
    private String groupId;

    @Column(name = "GROUP_NAME")
    private String groupName;

    // 权限code，英文描述，全局唯一
    @Column(name = "PERMISSION_CODE")
    private String permissionCode;

    // 菜单url
    @Column(name = "MENU_URL")
    private String menuUrl;

    // 权限名称
    @Column(name = "PERMISSION_NAME")
    private String permissionName;

    // 权限类型 menu-菜单 op-操作
    @Column(name = "PERMISSION_TYPE")
    private PermissionTypeEnum permissionType;

    // 是否只有平台管理员具备的权限，如果是1，则普通用户无法查看该权限，默认0
    @Column(name = "PLATFORM_ADMIN_FLAG")
    private Integer platformAdminFlag;

    // 权限是否被选中
    @Transient
    private boolean selectFlag;

    // 菜单图标
    @Column(name = "MENU_ICON_CLASS")
    private String menuIconClass;

}
