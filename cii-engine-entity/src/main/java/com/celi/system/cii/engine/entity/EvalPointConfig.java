package com.celi.system.cii.engine.entity;

import lombok.Data;

@Data
public class EvalPointConfig {

    // 父对象id，可能是特征值id
    private String parentId;

    private String pointId;

    private String pointUuid;

    private String pointName;

    private String alias;
}
