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
public enum SteelPropDataTypeEnum implements BaseEnum {

    NUM("NUM", "数字"),
    BOOLEAN("BOOLEAN", "布尔"),
    STRING("STRING", "字符"),;


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
