package com.celi.cii.base.enums;

import com.celi.cii.base.entity.BaseEnum;
import lombok.Getter;

/**
 *  测点枚举值
 */
@Getter
public enum DataTypeEnum implements BaseEnum {

    INTEGER("int", "整型"),
    DECIMAL("decimal", "小数"),
    BOOLEAN("boolean", "布尔"),
    STRING("string", "字符串"),
    TIMESTAMP("timestamp", "时间戳"),
    ;

    private String code;
    private String desc;

    DataTypeEnum(String code, String desc) {
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
