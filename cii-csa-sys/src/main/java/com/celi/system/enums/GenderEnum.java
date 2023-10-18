package com.celi.system.enums;

/**
 * @author jiangshengjun
 * @date 2021/11/15
 */
public enum GenderEnum {
    MALE(1, "男"),
    FEMALE(2, "女"),
    UN_KNOW(3, "未选择");

    private Integer code;
    private String desc;

    GenderEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
