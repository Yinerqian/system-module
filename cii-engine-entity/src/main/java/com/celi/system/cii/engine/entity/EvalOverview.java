package com.celi.system.cii.engine.entity;

import lombok.Data;

import java.math.BigDecimal;

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

    // 评价结果总数
    private Integer evalCount;

    // 不合格总数
    private Integer unqualifiedCount;

    // 不合格率
    private BigDecimal rejectionRate;
}
