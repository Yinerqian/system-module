package cii.da.message.codec.model;

import lombok.Data;

@Data
public class PointBase {

    // 2022-07-22 物料code
    protected String materialCode;

    // 前道产线ID
    protected String preLineCode;

    // 当前产线ID
    protected String currentLineCode;

}
