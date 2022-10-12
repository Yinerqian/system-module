package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.Data;

@Data
public class EvalPointConfig extends BaseCreateBy {

    // 父对象id，可能是特征值id
    private String parentId;

    private String pointId;

    private String pointUuid;

    private String pointName;

    private String alias;
}
