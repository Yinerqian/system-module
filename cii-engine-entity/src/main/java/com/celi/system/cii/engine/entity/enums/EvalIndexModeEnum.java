package com.celi.system.cii.engine.entity.enums;

import com.celi.cii.base.entity.BaseEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EvalIndexModeEnum implements BaseEnum {

    MODE_BASE("base", "基础模式"),
    MODE_ADVANCE("advance", "高级模式");

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
