package com.celi.system.enums;

import com.celi.system.dto.BaseEnum;
import lombok.Getter;

import java.util.Objects;

/**
 * 系统参数 参数类型
 */
@Getter
public enum SysParaEnum implements BaseEnum {

    NUMBER("Number", "数字"),
    BOOLEAN("Boolean", "布尔"),
    STRING("String", "字符串")
    ;

    private String code;
    private String desc;

    SysParaEnum(String code, String desc) {
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

    //获取枚举实例
    public static SysParaEnum fromValue(String value) {
        for (SysParaEnum statusEnum : SysParaEnum.values()) {
            if (Objects.equals(value, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        throw new IllegalArgumentException();
    }
}
