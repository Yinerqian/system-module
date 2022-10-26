package com.celi.system.cii.engine.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnalyseResult implements Serializable {
    // 缺陷分析结果
    List<String> defectValue;

    List<InfluenceValue> valueList;

    List<FishBoneData> fishBoneData;
}

