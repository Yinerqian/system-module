package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import com.celi.system.cii.engine.entity.enums.EvalIndexModeEnum;
import com.celi.system.cii.engine.entity.enums.EvalResultStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvalRuleIndex extends BaseCreateBy {

    /**
     * 指标ID
     */
    private String indexId;

    /**
     * 规则ID
     */
    private String ruleId;

    /**
     * 序号(优先级)
     */
    private Integer orderIndex;

    /**
     * 启用状态1-启用 0-未启用
     */
    private Integer enableFlag;

    /**
     * 基础模式json数据
     */
    private String baseJson;

    /**
     * 高级模式json数据
     */
    private String advanceJson;

    /**
     * 关联的点位json数组，含pointId、name、uuid、别名
     */
    private String pointJson;

    private List<EvalPointConfig> ruleIndexPointConfigList;

    /**
     * 关联的特征值json，含chValueId、name、uuid、别名
     */
    private String chValueJson;    private List<EvalChValueConfig> ruleIndexChValueConfigList;



    // 开始点位id
    private String startPointId;

    private String startPointUuid;

    private String startPointName;

    // 结束点位id
    private String endPointId;

    private String endPointUuid;

    private String endPointName;

    // 实际的开始时间
    private Date startDate;

    // 实际的结束时间
    private Date endDate;

    private String lineId;

    /**
     * 指标数据相关
     */
    private String indexName;

    private String pointName;

    private EvalIndexModeEnum indexMode;

    private String indexCode;

    /*
    单项指标的分数
     */
    private Float evalIndexScore;


    private EvalResultStatusEnum evalIndexResult;

    @JsonProperty(value = "modeName")
    public String getModeName() {
        return this.indexMode != null ? this.indexMode.getTitle() : null;
    }

}
