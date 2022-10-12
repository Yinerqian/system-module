package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductionLine extends BaseCreateBy {

    /**
     * 产线id
     */
    private String lineId;

    private String lineCode;

    /**
     * 父分组id
     */
    private String parentLineId;

    /**
     * 产线名称
     */
    private String lineName;

    /**
     * 序号
     */
    private Integer orderIndex;

    /**
     * 消息主题
     */
    private String messageTopic;

    /**
     * 上游产线id
     */
    private String preLineId;

    /**
     * 下游产线id
     */
    private String nextLineId;

    /**
     * 评价对象id
     */
    private String targetId;

    /**
     * 备注
     */
    private String remark;
    
    /**
     * 图标
     */
    private String icon;

    private String startPointId;

    private String endPointId;

    private String startPointName;

    private String endPointName;

    /**
     * 开始信号uuid join字段
     */
    private String startPointUuid;

    /**
     * 结束信号Uuid join字段
     */
    private String endPointUuid;

    /**
     * 产线下工序列表
     */
    private List<ProductionLineProcedure> procedureList;

    /**
     * 产线下点位 (uuid) 列表
     */
    private List<String> pointList;

    // 该产线同时只能生产一个物料
    private Integer singleMaterialFlag;

    // 产线对应的特征值列表
    private List<QualityChValue> chValueList;

    /**
     * 产线开始/结束，工序/结束，评价规则开始/结束，特征值开始/结束
     */
    private List<String> triggerPointList;

}
