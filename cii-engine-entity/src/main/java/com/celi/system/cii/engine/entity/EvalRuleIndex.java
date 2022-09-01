package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import com.celi.system.cii.engine.entity.enums.EvalIndexModeEnum;
import com.celi.system.cii.engine.entity.enums.EvalResultStatusEnum;
import com.celi.system.cii.engine.entity.utils.EvalResultUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
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
    private String chValueJson;

    private List<EvalChValueConfig> ruleIndexChValueConfigList;

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

    private Map<String, Object> res;

    /*
    单项指标的分数
     */
    private Float evalIndexScore;

    private EvalResultStatusEnum evalIndexResult;

    // 计算结果存储
    private Map<String, Object> res;

    @JsonProperty(value = "modeName")
    public String getModeName() {
        return this.indexMode != null ? this.indexMode.getTitle() : null;
    }

    public Float getEvalIndexScore() {
        // 获取引擎计算的res
        if (res != null) {
            Object score = res.get("score");
            if (score != null) {
                try {
                    return Float.parseFloat(score.toString());
                } catch (Exception e) {
                    log.error("解析res score失败=== {}", e.getCause());
                }
            }
        }

        return evalIndexScore;
    }

    public String getChValueName() {
        if (res != null) {
            Object result = res.get("chValueName");
            if (result != null) {
                try {
                    return result.toString();
                } catch (Exception e) {
                    log.error("解析res getChValueName失败=== {}", e.getCause());
                }
            }
        }

        return null;
    }

    public List getChValueResult() {
        if (res != null) {
            Object result = res.get("chValueResult");
            if (result != null) {
                try {
                    return (List) result;
                } catch (Exception e) {
                    log.error("解析res getChValueResult 失败=== {}", e.getCause());
                }
            }
        }

        return null;
    }


    @JsonProperty(value = "resultName")
    public String resultName() {
        return evalIndexResult == null ? null : evalIndexResult.getTitle();
    }

}
