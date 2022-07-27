package com.celi.system.cii.engine.entity;

import lombok.Data;

/**
 * 评价概览
 */
@Data
public class EvalOverview {

    // 评价对象
    private Integer targetCount;

    // 评价维度
    private Integer dimensionCount;

    // 评价指标
    private Integer indexCount;

    // 评价规则
    private Integer ruleCount;
}
