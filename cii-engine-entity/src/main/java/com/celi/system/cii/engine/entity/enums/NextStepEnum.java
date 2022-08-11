package com.celi.system.cii.engine.entity.enums;

import com.celi.cii.base.entity.BaseEnum;

/**
 * @author Ce-li
 * @date 2022-08-08 19:49
 * @Description
 */
public enum NextStepEnum implements BaseEnum {
    CONTINUE("1", "继续分析"),
    STOP("0", "停止分析");

    private String code;
    private String title;
    NextStepEnum(String code, String title) {
        this.code = code;
        this.title = title;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
