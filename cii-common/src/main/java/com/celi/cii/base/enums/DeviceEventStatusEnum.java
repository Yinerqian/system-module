package com.celi.cii.base.enums;

import com.celi.cii.base.entity.BaseEnum;
import lombok.Getter;

/**
 *  设备事件状态枚举值 normal 正常 alarm 告警
 */
@Getter
public enum DeviceEventStatusEnum implements BaseEnum {

    NORMAL("normal", "正常"),
    FAULT("alarm", "告警")
    ;

    private String code;
    private String desc;

    DeviceEventStatusEnum(String code, String desc) {
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
