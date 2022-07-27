package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import com.celi.cii.base.enums.DataTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 评价维度
 */
@Data
public class EvalDimension extends BaseCreateBy {

    /**
     * 维度Id
     */
    private String dimensionId;

    /**
     * 维度名称
     */
    private String dimensionName;

    /**
     * 维度编码
     */
    private String dimensionCode;

    /**
     * 对象Id
     */
    private String targetId;

    /**
     * 序号
     */
    private Integer orderIndex;

    /**
     * 数字number，字符串string
     */
    private DataTypeEnum dataType;

    /**
     * 图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

    /**
     * 评价对象名称
     */
    private String targetName;

    @JsonProperty(value = "dataTypeName")
    public String getDataTypeName() {
        return this.dataType != null ? this.dataType.getTitle() : null;
    }
}
