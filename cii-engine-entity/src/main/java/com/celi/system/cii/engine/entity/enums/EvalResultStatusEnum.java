package com.celi.system.cii.engine.entity.enums;

import com.celi.cii.base.entity.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EvalResultStatusEnum implements BaseEnum {
    QUALIFIED("QUALIFIED", "合格"),
    UN_QUALIFIED("UN_QUALIFIED", "不合格"),
    OTHER("OTHER", "其他"),
    ;

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
