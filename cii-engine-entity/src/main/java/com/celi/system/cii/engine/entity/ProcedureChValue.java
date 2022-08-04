package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import com.celi.system.cii.engine.entity.enums.ChValueTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工序特征值
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureChValue extends BaseCreateBy {

    /**
     * 工序id
     */
    private String procedureId;

    /**
     * 特征值id
     */
    private String chValueId;


    /**
     * 产线ID
     */
    private String lineId;

    /**
     * 序号
     */
    private Integer orderIndex;

    /**
     * 特征值名称
     */
    private String chValueName;

    /**
     * 特征值类型
     */
    private ChValueTypeEnum chValueType;

    @JsonProperty(value = "chValueTypeName")
    public String getChValueTypeName() {
        return this.chValueType != null ? this.chValueType.getTitle() : null ;
    }


}
