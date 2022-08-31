package com.celi.system.cii.engine.entity;

import lombok.Data;

/**
 * 告警概览
 */
@Data
public class AlarmOverview {

    // 告警总数
    private Integer eventCount;

    // 监控点位
    private Integer pointCount;

    // 告警规则
    private Integer ruleCount;

    // 告警等级
    private Integer levelCount;
}
