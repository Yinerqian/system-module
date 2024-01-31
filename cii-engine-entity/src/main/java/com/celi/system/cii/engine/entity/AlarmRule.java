package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 告警规则
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlarmRule extends BaseCreateBy {
    /**
     * 告警规则id
     */
    private String ruleId;

    /**
     * 规则分组id
     */
    private String groupId;

    /**
     * 告警规则名称
     */
    private String ruleName;

    /**
     * 规则等级id
     */
    private String ruleLevelId;

    /**
     * 规则模式 1-基础模式 2-高级模式
     */
    private Integer ruleMode;

    /**
     * 是否开启防抖 1-开启 0-不开启
     */
    private Integer antiShakeFlag;

    /**
     * 防抖json
     */
    private String antiShakeJson;

    /**
     * 基础模式json
     */
    private String baseModeJson;

    /**
     * 高级模式json
     */
    private String advanceModeJson;

    /**
     * 执行动作json
     */
    private String actionJson;

    /**
     * 备注
     */
    private String remark;

    /**
     * 脚本内容
     */
    private String ruleContent;

    /**
     * 关联点位集合
     */
    List<String> pointIds;

}