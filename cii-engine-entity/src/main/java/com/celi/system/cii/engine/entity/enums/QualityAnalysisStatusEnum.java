package com.celi.system.cii.engine.entity.enums;

import com.celi.cii.base.entity.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Ce-li
 * @Date: 2022/9/27 17:41
 */
@Getter
@AllArgsConstructor
public enum QualityAnalysisStatusEnum implements BaseEnum {

    INITIALIZE("INITIALIZE", "初始化"),
    ANALYSE("ANALYSE", "分析中"),
    ACCOMPLISH("ACCOMPLISH", "完成"),
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
