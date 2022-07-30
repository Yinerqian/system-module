package com.celi.system.cii.engine.entity.enums;

import com.celi.cii.base.entity.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MaterialProductionStatusEnum implements BaseEnum {
    UN_START("UN_START", "未开始"),
    IN_PRODUCTION("IN_PRODUCTION", "生产中"),
    FINISH("FINISH", "生产结束"),
    DROP("DROP", "丢弃"),
    EXCEPTION("EXCEPTION", "跟踪异常")
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
