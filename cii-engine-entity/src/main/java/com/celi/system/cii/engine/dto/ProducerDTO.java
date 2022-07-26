package com.celi.system.cii.engine.dto;

import com.celi.system.cii.engine.entity.PointData;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 工序对象 DTO
 */
@Data
public class ProducerDTO {
    /**
     * 工序 Id
     */
    private String producerId;

    /**
     * 工序次序
     */
    private String index;

    /**
     * 工序开始时间
     */
    private Date startTime;

    /**
     * 工序结束时间
     */
    private Date endTime;


    /**
     * 测点数据列表
     */
    private List<PointData> pointDataList;
}
