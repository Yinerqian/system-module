package com.celi.system.permission.enity;

import lombok.Data;

import java.util.List;

@Data
public class SysPermissionGroup extends BaseCreateBy {

    // 分组id
    private Integer groupId;

    // 应用id
    private Integer appId;

    private String appName;

    private String groupIcon;

    // 分组名称
    private String groupName;

    // 排序
    private Integer sort;

    //分组排序
    private Integer groupSort;

    private List<SysPermission> permissionList;

}
