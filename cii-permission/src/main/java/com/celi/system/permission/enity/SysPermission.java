package com.celi.system.permission.enity;

import com.celi.system.permission.enums.PermissionTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SysPermission extends BaseCreateBy {

    // 权限id
    private Integer permissionId;

    // 父权限id，默认为0，暂时不用
    private Integer parentPermissionId = 0;

    // 应用id，含内置应用
    private Integer appId;

    private String appName;

    // 权限分组id
    private Integer groupId;

    private String groupName;

    private String groupIcon;

    private Integer groupSort;

    // 权限code，英文描述，全局唯一
    private String permissionCode;

    // 菜单url
    private String menuUrl;

    // 权限名称
    private String permissionName;

    // 权限类型 menu-菜单 op-操作
    private PermissionTypeEnum permissionType;

    // 是否只有平台管理员具备的权限，如果是1，则普通用户无法查看该权限，默认0
    private Integer platformAdminFlag;

    // 是否管理员, true-管理员, false-租户管理员
    private Boolean isAdmin = false;

    // 权限是否被选中
    private boolean selectFlag;

    // 菜单图标
    private String menuIconClass;

    // 排序
    private Integer sort;

    public Integer getSort() {
        if(sort == null){
            return 0;
        }
        return sort;
    }

    @JsonProperty(value = "permissionTypeName")
    public String getPermissionTypeName() {
        return this.permissionType != null ? this.permissionType.getDesc() : null;
    }
}
