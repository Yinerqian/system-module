package com.celi.system.entity;

import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sys_permission_group")
@Data
@ToString
@Log4j2
public class PermissionGroup extends BaseCreateBy {

    // 分组id
    @Id
    @Column(name = "GROUP_ID", updatable = false)
    private Integer groupId;


    // 分组名称
    @Column(name = "GROUP_NAME")
    private String groupName;

    // 父分组 id
    @Column(name = "PARENT_GROUP_ID")
    private String parentGroupId = "0";

    // 排序
    @Column(name = "SORT")
    private Integer sort;

    @Column(name = "GROUP_ICON")
    private String groupIcon;

    //分组排序
    @Column(name = "GROUP_SORT")
    private Integer groupSort;

    @Transient
    private List<Permission> permissionList;

}
