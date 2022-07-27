package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import com.celi.system.cii.engine.entity.enums.EvalIndexModeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvalIndex extends BaseCreateBy {

    /**
     * 指标Id
     */
    private String indexId;

    /**
     * 维度Id
     */
    private String dimensionId;

    /**
     * 维度名称
     */
    private String dimensionName;

    /**
     * 对象Id
     */
    private String targetId;

    /**
     * 对象名称
     */
    private String targetName;

    /**
     * 指标名称
     */
    private String indexName;

    /**
     * 指标编码
     */
    private String indexCode;

    /**
     * 优先级
     */
    private Integer orderIndex;

    /**
     * 指标模式 base-基础模式 advance-高级模式
     */
    private EvalIndexModeEnum indexMode;

    @JsonProperty(value = "modeName")
    public String getModeName() {
        return this.indexMode != null ? this.indexMode.getTitle() : null;
    }

    /**
     * 普通模式Json数据
     */
    private String baseJson;

    /**
     * 高级模式Json数据
     */
    private String advanceJson;

    /**
     * 备注
     */
    private String remark;
}
