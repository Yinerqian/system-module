package com.celi.system.cii.engine.entity.enums;

import com.celi.cii.base.entity.BaseEnum;
import lombok.Getter;

/**
 * @Author: Ce-li
 * @Date: 2022/7/19 15:33
 * 特征值类型
 */
@Getter
public enum ChValueTypeEnum implements BaseEnum {

    BASIC_FEATURES_VALUE("1", "基本特征值"),
    SPACE_TIME_FEATURES_VALUE("2", "时空转换特征值");

    private String code;
    private String desc;


    ChValueTypeEnum(String code, String desc) {
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
