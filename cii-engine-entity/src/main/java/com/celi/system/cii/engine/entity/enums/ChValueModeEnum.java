package com.celi.system.cii.engine.entity.enums;

import com.celi.cii.base.entity.BaseEnum;
import lombok.Getter;

/**
 * @Author: Ce-li
 * @Date: 2022/7/19 15:33
 * 特征值模式
 */
@Getter
public enum ChValueModeEnum implements BaseEnum {

    BASIC_PATTERN("1", "基本模式"),
    SENIOR_PATTERN("2", "高级模式");

    private String code;
    private String desc;


    ChValueModeEnum(String code, String desc) {
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
