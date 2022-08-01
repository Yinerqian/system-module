package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
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
     * 租户ID
     */
    private Integer tenantId;

    /**
     * 产线ID
     */
    private String lineId;

    /**
     * 序号
     */
    private Integer orderIndex;


}
