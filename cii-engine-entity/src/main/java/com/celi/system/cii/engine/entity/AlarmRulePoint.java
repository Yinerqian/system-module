package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author dw_min
 * @Date 2024/3/4
 * @Description 告警规则和测点中间对象
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlarmRulePoint extends BaseCreateBy {
    /**
     * id
     */
    private String alarmRulePointId;

    /**
     * 测点id
     */
    private String pointId;

    /**
     * 告警规则id
     */
    private String ruleId;

    /**
     * 排序
     */
    private Integer orderIndex;

    /**
     * 备注
     */
    private String remark;
}
