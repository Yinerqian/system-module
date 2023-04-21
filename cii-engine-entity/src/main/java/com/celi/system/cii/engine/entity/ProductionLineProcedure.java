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
    private String procedureId;

    /**
     * 工序名称
     */
    private String procedureName;

    /**
     * 关联的分组id
     */
    private String lineId;

    private String startPointId;

    private String endPointId;

    private String startPointName;

    private String endPointName;

    /**
     * 开始信号uuid
     */
    private String startPointUuid;

    /**
     * 结束信号Uuid
     */
    private String endPointUuid;

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
    private List<PointData> pointList;

    /*
    * 额外需要记录的点位信息
    * */
    private String extraPoints;

    private List<ProcedureKeyPoint> keyPointList;

    /**
     * 工序下特征值
     */
    private List<ProcedureChValue> procedureChValueList;

    private Date startTime;

    private Date endTime;

    /**
     * 时间阈值，超过阈值自动判废，单位：秒（s）
     */
    private Integer timeThreshold;
}
