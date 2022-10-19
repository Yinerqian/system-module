package com.celi.system.permission.enums;

import lombok.Getter;

@Getter
public enum SysUserStatusEnum implements BaseEnum {

    ENABLE("0", "启用"),
    DISABLED("1", "禁用")
    ;

    private String code;
    private String desc;

    SysUserStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
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
