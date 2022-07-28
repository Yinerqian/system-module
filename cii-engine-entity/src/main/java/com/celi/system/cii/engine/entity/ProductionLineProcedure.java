package com.celi.system.cii.engine.entity;

import cii.da.message.codec.model.PointData;
import com.celi.cii.base.entity.BaseCreateBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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
    protected String procedureId;

    /**
     * 工序名称
     */
    protected String procedureName;

    /**
     * 关联的分组id
     */
    protected String lineId;

    /**
     * 开始信号uuid
     */
    protected String startPointUuid;

    /**
     * 结束信号Uuid
     */
    protected String endPointUuid;

    /**
     * 序号
     */
    protected Integer orderIndex;

    /**
     * 备注
     */
    protected String remark;

    /**
     * 图标
     */
    protected String icon;


    /**
     * 工序下点位 (uuid) 列表
     */
    protected List<PointData> pointList;

    protected Date startTime;

    protected Date endTime;
}
