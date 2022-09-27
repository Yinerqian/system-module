package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.Data;

@Data
public class EvalChValueConfig extends BaseCreateBy {

    // 父对象id
    private String parentId;

    private String chValueId;

    private String chValueName;

    private String alias;

}
