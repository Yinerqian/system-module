package com.celi.system.permission.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysApp extends BaseCreateBy {

    private Integer appId;

    private String appCode;

    private String appName;

    private String nickName;

    private String url;

    private String color;

    private String icon;

    private String customIcon;

    private String describe;

    private Integer innerFlag;

    private String categoryId;

    /**
     * 1 收藏了 ,2 未收藏
     */
    private Integer isFav;

    /**
     *  应用下有多个权限
     */
    private List<SysPermission> permissions;

    /**
     *  应用下有多个权限分组
     */
    private List<SysPermissionGroup> permissionGroupList;


    /**
     *  权限ids
     */
    private List<Integer> permissionIds;

    private SysAppUser sysAppUser;

    /**
     *  权限id
     */
    private Integer permissionId;

    /**
     *  分组名称
     */
    private String groupName;

    /**
     *  权限名称
     */
    private String permissionName;

    // 租户id
    private Integer tenantId;

    // 角色id
    private Integer roleId;

    private Integer selectApped;

    // APP树数据
    @JsonProperty(value = "label")
    public String getTreeLabel() {
        return this.appName;
    }

    public SysApp(Integer appId) {
        this.appId = appId;
    }

    // 应用是否被选中
    @JsonProperty(value = "selectAppFlag")
    public Boolean getSelectAppFlag() {
        return Integer.valueOf(1).equals(selectApped);
    }
}
