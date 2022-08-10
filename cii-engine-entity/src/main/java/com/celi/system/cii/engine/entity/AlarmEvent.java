package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import com.celi.system.cii.engine.entity.enums.AlarmStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: Ce-li
 * @Date: 2022/7/29 16:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlarmEvent extends BaseCreateBy {

    /**
     * 事件id
     */
    private String eventId;


    /**
     * 告警规则Id
     */
    private String ruleId;

    /**
     * 告警规则名称，冗余字段
     */
    private String ruleName;

    /**
     * 告警规则等级id
     */
    private String levelId;


    /**
     * 告警规则等级名称，冗余字段
     */
    private String levelName;


    /**
     * 告警规则等级颜色，冗余字段
     */
    private String levelColor;


    /**
     * 产线id
     */
    private String lineId;


    /**
     * 产线名称
     */
    private String lineName;


    /**
     * 评价对象id
     */
    private String targetId;


    /**
     * 事件id
     */
    private String targetName;


    /**
     * 物料号
     */
    private String materialCode;


    /**
     * 测点id
     */
    private String pointId;

    private String pointName;

    /**
     * 测点Uuid
     */
    private String pointUuid;


    /**
     * 告警时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date alarmDate;


    /**
     * 告警描述内容
     */
    private String alarmDesc;

    /**
     * 告警状态 1-未读 2-已读 3-已确认
     */
    private AlarmStatusEnum alarmStatus;

    /**
     * 告警次数
     */
    private Integer alarmCount;

    /**
     * 恢复时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date recoveryDate;

    @JsonProperty("alarmStatusName")
    private String getAlarmStatusName() {
        return this.alarmStatus != null ? this.alarmStatus.getTitle() : null;
    }

}
