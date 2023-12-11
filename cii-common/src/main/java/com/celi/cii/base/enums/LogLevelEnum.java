package com.celi.cii.base.enums;

import com.celi.cii.base.entity.BaseEnum;
import lombok.Getter;

@Getter
public enum LogLevelEnum implements BaseEnum {
    INFO("info", "信息"),
    WARN("warn", "异常"),
    ERROR("error", "错误"),
    DEBUG("debug", "DEBUG"),
    ;

    LogLevelEnum(String code, String title) {
        this.code = code;
        this.title = title;
    }

    private String code;

    private String title;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
