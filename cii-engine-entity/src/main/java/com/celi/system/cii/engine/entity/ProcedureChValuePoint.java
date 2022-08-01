package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 特征值关联点位
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureChValuePoint extends BaseCreateBy {

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
     * 点位id
     */
    private String pointId;

    /**
     * 点位uuid
     */
    private String pointUuid;

    /**
     * 点位名称，冗余字段
     */
    private String pointName;

    /**
     * 关键点位别名
     */
    private String aliasName;

    /**
     * 序号
     */
    private Integer orderIndex;

    /**
     * 计算脚本，默认从特征值管理中继承
     */
    private String chValueScript;
}
