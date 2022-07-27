package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvalRule extends BaseCreateBy {

    /**
     * 规则Id
     */
    private String ruleId;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 规则编码
     */
    private String ruleCode;

    /**
     * 关联规则
     */
    private String relationRuleIds;

    /**
     * 评价对象
     */
    private String targetId;

    /**
     * 对象名称
     */
    private String targetName;

    /**
     * 评价维度
     */
    private String dimensionId;

    /**
     * 维度名称
     */
    private String dimensionName;

    /**
     * 产线
     */
    private String lineId;

    /**
     * 序号
     */
    private Integer orderIndex;

    /**
     * 匹配条件
     */
    private String matchConditionJson;

    /**
     * 备注
     */
    private String remark;

    /**
     * 产线Id集合
     */
    private String lineIds;

    List<EvalRuleIndex> evalRuleIndexList;
}
