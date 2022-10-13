package com.celi.system.permission.enums;

import lombok.Getter;

@Getter
public enum PermissionTypeEnum implements BaseEnum {

    MENU("MENU", "菜单"),
    OP("OP", "操作"),
    ;

    private String code;
    private String desc;

    PermissionTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getTitle() {
        return desc;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
