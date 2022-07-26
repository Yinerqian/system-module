package com.celi.system.cii.engine.entity;

import com.celi.system.base.BaseCreateBy;
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

}
