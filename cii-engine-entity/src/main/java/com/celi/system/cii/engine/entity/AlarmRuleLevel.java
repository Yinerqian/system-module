package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.Data;

/**
 * 告警规则等级
 */
@Data
public class AlarmRuleLevel extends BaseCreateBy {

    // 规则等级id
    private String levelId;

    // 规则等级名称
    private String levelName;

    // 规则等级编码
    private String levelCode;

    // 规则等级颜色，形如#A1B2C3
    private String levelColor;

    // 备注
    private String remark;
}
