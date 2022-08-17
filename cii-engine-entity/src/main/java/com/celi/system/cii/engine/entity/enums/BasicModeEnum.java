package com.celi.system.cii.engine.entity.enums;

import com.celi.cii.base.entity.BaseEnum;
import lombok.Getter;

/**
 * @Author: Ce-li
 * @Date: 2022/7/19 15:33
 * 基本模式
 */
@Getter
public enum BasicModeEnum implements BaseEnum {

    AVG("AVG", "平均值"),
    MAX("MAX", "最大值"),
    MIN("MIN", "最小值"),
    MEDIAN("MEDIAN", "中位数"),
    MAXIMUM_DIFF("MAXIMUM_DIFF", "最大差值"),
    MINIMUM_DIFF("MINIMUM_DIFF", "最小差值");

    private String code;
    private String desc;


    BasicModeEnum(String code, String desc) {
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
