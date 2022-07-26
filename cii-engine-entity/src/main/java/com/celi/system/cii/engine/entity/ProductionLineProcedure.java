package com.celi.system.cii.engine.entity;

import com.celi.system.base.BaseCreateBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 产线工序
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductionLineProcedure extends BaseCreateBy {

    /**
     * 工序id
     */
    private String procedureId;

    /**
     * 工序名称
     */
    private String procedureName;

    /**
     * 关联的分组id
     */
    private String lineId;

    /**
     * 开始信号id
     */
    private String startPointId;

    /**
     * 结束信号id
     */
    private String endPointId;

    /**
     * 序号
     */
    private Integer orderIndex;

    /**
     * 备注
     */
    private String remark;

    /**
     * 图标
     */
    private String icon;


    /**
     * 工序下点位 (uuid) 列表
     */
    private List<String> pointList;
}
