package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Ce-li
 * @Date: 2022/7/19 11:31
 * @Desc: 质量分析规则实体类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QualityAnalyseRule extends BaseCreateBy {

    /**
     * 特征值id
     */
    private String ruleId;

    /**
     * 评价对象Id
     */
    private String targetId;

    /**
     * 评价对象name
     */
    private String targetName;

    /**
     * 质量分析规则名称
     */
    private String ruleName;

    /**
     * 质量分析规则编码
     */
    private String ruleCode;

    /**
     * 优先级 1-最低 数字越大越高
     */
    private Integer priority;

    /**
     * 质量分析规则
     */
    private String ruleConfigJson;

    /**
     * 是否启用 1 启用 0 不启用
     */
    private Integer enableFlag;

}
