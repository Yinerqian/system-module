package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.Data;

@Data
public class ProcedureKeyPoint extends BaseCreateBy {

    // 关键id
    private String keyId;

    // 点位id
    private String pointId;

    // 点位uuid
    private String pointUuid;

    // 点位名称
    private String pointName;

    // 关键点位别名
    private String aliasName;

    // 序号
    private Integer orderIndex;

    // 工序id
    private String procedureId;

    // 产线id
    private String lineId;

    // 租户ID
    private Integer tenantId;
}
