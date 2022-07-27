package com.celi.system.cii.engine.entity.enums;

import com.celi.cii.base.entity.BaseEnum;
import lombok.AllArgsConstructor;

/**
 *
 */

@AllArgsConstructor
public enum ProcedureTypeEnum implements BaseEnum {
    PROCEDURE_TYPE_BASE("basic", "基础工序"),
    PROCEDURE_TYPE_VIRTUAL("virtual", "虚拟工序");

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
