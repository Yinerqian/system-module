package com.celi.system.cii.engine.entity.enums;

import com.celi.cii.base.entity.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Ce-li
 * @Date: 2022/8/4 14:24
 */
@Getter
@AllArgsConstructor
public enum EnableFlagEnum implements BaseEnum {

    ENABLE("1", "启用"),
    DISABLE("2", "禁用");

    private String code;
    private String title;

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }
}
