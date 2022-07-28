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
    protected String lineId;

    /**
     * 父分组id
     */
    protected String parentLineId;

    /**
     * 产线名称
     */
    protected String lineName;

    /**
     * 序号
     */
    protected Integer orderIndex;

    /**
     * 消息主题
     */
    protected String messageTopic;

    /**
     * 上游产线id
     */
    protected String preLineId;

    /**
     * 下游产线id
     */
    protected String nextLineId;

    /**
     * 评价对象id
     */
    protected String targetId;

    /**
     * 备注
     */
    protected String remark;
    
    /**
     * 图标
     */
    protected String icon;

    protected String startPointId;

    protected String endPointId;

    /**
     * 开始信号uuid join字段
     */
    protected String startPointUuid;

    /**
     * 结束信号Uuid join字段
     */
    protected String endPointUuid;

    /**
     * 产线下工序列表
     */
    protected List<ProductionLineProcedure> procedureList;

    /**
     * 产线下点位 (uuid) 列表
     */
    protected List<String> pointList;

    // 该产线同时只能生产一个物料
    protected Integer singleMaterialFlag;
}
