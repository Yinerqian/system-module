package com.celi.cii.base.constant;

import com.celi.cii.base.entity.BaseEnum;
import lombok.Getter;

@Getter
public enum CiiSysPermissionEnum implements BaseEnum {

    M_TENANT("M_TENANT", 1,"租户管理"),
    OP_TENANT_ADD("OP_TENANT_ADD", 1, "新增租户"),
    OP_TENANT_EDIT("OP_TENANT_EDIT", 1, "编辑租户"),
    OP_TENANT_VIEW("OP_TENANT_VIEW", 1, "查看租户"),
    OP_TENANT_DEL("OP_TENANT_DEL", 1, "删除租户"),
    M_USER("M_USER", 0, "用户管理"),
    OP_USER_ADD("OP_USER_ADD", 0, "新增用户"),
    OP_USER_EDIT("OP_USER_EDIT", 0, "编辑用户"),
    OP_USER_VIEW("OP_USER_VIEW", 0, "查看用户"),
    OP_USER_DEL("OP_USER_DEL", 0, "删除用户"),
    M_APP("M_APP", 1, "应用管理"),
    OP_APP_ADD("OP_APP_ADD", 1, "新增应用"),
    OP_APP_EDIT("OP_APP_EDIT", 1, "编辑应用"),
    OP_APP_VIEW("OP_APP_VIEW", 1, "查看应用"),
    OP_APP_DEL("OP_APP_DEL", 1, "删除应用"),
    M_PERMISSION("M_PERMISSION", 1, "权限管理"),
    OP_PERMISSION_ADD("OP_PERMISSION_ADD", 1, "新增权限"),
    OP_PERMISSION_EDIT("OP_PERMISSION_EDIT", 1, "编辑权限"),
    OP_PERMISSION_VIEW("OP_PERMISSION_VIEW", 1, "查看权限"),
    OP_PERMISSION_DEL("OP_PERMISSION_DEL", 1, "删除权限"),
    M_ROLE("M_ROLE", 0, "角色管理"),
    OP_ROLE_ADD("OP_ROLE_ADD", 0, "新增角色"),
    OP_ROLE_EDIT("OP_ROLE_EDIT", 0, "编辑角色"),
    OP_ROLE_VIEW("OP_ROLE_VIEW", 0, "查看角色"),
    OP_ROLE_DEL("OP_ROLE_DEL", 0, "删除角色"),
    M_DEPT("M_DEPT", 0, "机构管理"),
    OP_DEPT_ADD("OP_DEPT_ADD", 0, "新增机构"),
    OP_DEPT_EDIT("OP_DEPT_EDIT", 0, "编辑机构"),
    OP_DEPT_VIEW("OP_DEPT_VIEW", 0, "查看机构"),
    OP_DEPT_DEL("OP_DEPT_DEL", 0, "删除机构"),
    M_LOG("M_LOG", 0, "日志管理"),
    OP_LOG_VIEW("OP_LOG_VIEW", 0, "查看日志"),
    M_PARA("M_PARA", 0, "参数管理"),
    OP_PARA_ADD("OP_PARA_ADD", 0, "新增参数"),
    OP_PARA_EDIT("OP_PARA_EDIT", 0, "编辑参数"),
    OP_PARA_VIEW("OP_PARA_VIEW", 0, "查看参数"),
    OP_PARA_DEL("OP_PARA_DEL", 0, "删除参数"),
    ;

    private String code;
    private Integer platformAdminFlag;
    private String desc;

    CiiSysPermissionEnum(String code, Integer platformAdminFlag, String desc) {
        this.code = code;
        this.platformAdminFlag = platformAdminFlag;
        this.desc = desc;
    }

    @Override
    public String getTitle() {
        return desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPlatformAdminFlag() {
        return platformAdminFlag;
    }

    public void setPlatformAdminFlag(Integer platformAdminFlag) {
        this.platformAdminFlag = platformAdminFlag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
