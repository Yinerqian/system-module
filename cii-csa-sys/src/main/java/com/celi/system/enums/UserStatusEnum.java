package com.celi.system.enums;

import com.celi.system.dto.BaseEnum;
import lombok.Getter;

@Getter
public enum UserStatusEnum implements BaseEnum {

    ENABLE("0", "启用"),
    DISABLED("1", "禁用")
    ;

    private String code;
    private String desc;

    UserStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

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
