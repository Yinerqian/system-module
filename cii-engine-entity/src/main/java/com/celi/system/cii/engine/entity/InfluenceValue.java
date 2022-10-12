package com.celi.system.cii.engine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfluenceValue implements Serializable {
    // 影响因子
    String influenceName;

    // 当前物料
    String analyseValue;

    // 优秀样本平均
    String bestValue;

    // 偏差
    String deviation;
}
