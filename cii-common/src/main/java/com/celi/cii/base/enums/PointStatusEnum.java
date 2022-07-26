package com.celi.cii.base.enums;

import com.celi.cii.base.entity.BaseEnum;
import lombok.Getter;

@Getter
public enum PointStatusEnum implements BaseEnum {

    NORMAL("NORMAL", "正常"),
    ABNORMAL("ABNORMAL", "异常"),
    OTHER("OTHER", "其他"),
    ;

    private String code;
    private String desc;

    PointStatusEnum(String code, String desc) {
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
