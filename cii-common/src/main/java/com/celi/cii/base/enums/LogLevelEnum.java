package com.celi.cii.base.enums;

import com.celi.cii.base.entity.BaseEnum;
import lombok.Getter;

@Getter
public enum LogLevelEnum implements BaseEnum {
    INFO("INFO", "信息"),
    WARN("WARN", "异常"),
    ERROR("ERROR", "错误"),
    DEBUG("DEBUG", "DEBUG"),
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
