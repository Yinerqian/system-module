package com.celi.cii.base.enums;

import com.celi.cii.base.entity.BaseEnum;
import lombok.Getter;

@Getter
public enum LogOpTypeEnum  implements BaseEnum {
    LOGIN("login", "登录"),
    LOGOUT("logout", "登出"),
    INSERT("insert", "插入"),
    UPDATE("update", "修改"),
    DELETE("delete", "删除"),
    QUERY("query", "查询"),
    ;

    LogOpTypeEnum(String code, String title) {
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
