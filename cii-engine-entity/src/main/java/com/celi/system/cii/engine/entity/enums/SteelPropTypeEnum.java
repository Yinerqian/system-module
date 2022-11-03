package com.celi.system.cii.engine.entity.enums;

import com.celi.cii.base.entity.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Ce-li
 * @Date: 2022/10/28 16:30
 */
@Getter
@AllArgsConstructor
public enum SteelPropTypeEnum implements BaseEnum {

    SPECICAL("SPECICAL", "专有属性"),
    COMMON("COMMON", "基础属性");

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
