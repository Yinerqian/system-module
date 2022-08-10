package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import com.celi.system.cii.engine.entity.enums.EnableFlagEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Ce-li
 * @Date: 2022/8/3 17:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonitorPoint extends BaseCreateBy {

    /**
     * 监控点位id
     */
    private String monitorPointId;


    /**
     * 监控点位名称(默认取测点名称)
     */
    private String monitorPointName;


    /**
     * 产线分组id
     */
    private String productionLineGroupId;


    /**
     * 工序id
     */
    private String procedureId;


    /**
     * 告警规则ID
     */
    private String alarmRuleId;

    private String ruleName;

    private String levelName;

    private String levelColor;

    /**
     * 启用状态 1-启用 0-禁用
     */
    private EnableFlagEnum enableFlag;


    /**
     * 关联测点json，数组，可以多个测点，至少应包括测点id、uuid和名称
     */
    private String relationPointJson;


    /**
     * 备注
     */
    private String remark;

    @JsonProperty("enableName")
    private String getEnableName() {
        return this.enableFlag != null ? this.enableFlag.getTitle() : null;
    }

}
