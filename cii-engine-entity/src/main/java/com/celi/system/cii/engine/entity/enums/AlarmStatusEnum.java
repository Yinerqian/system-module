package com.celi.system.cii.engine.entity.enums;

import com.celi.cii.base.entity.BaseEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Ce-li
 * @Date: 2022/7/29 16:14
 */
@Getter
@AllArgsConstructor
public enum AlarmStatusEnum implements BaseEnum {

    UNREAD("1", "未读"),
    READ("2", "已读"),
    CONFIRM("3", "已确认");

    private String code;
    private String desc;

    @JsonValue
    public String getCode() {
        return code;
    }

    public String getTitle() {
        return desc;
    }

}
