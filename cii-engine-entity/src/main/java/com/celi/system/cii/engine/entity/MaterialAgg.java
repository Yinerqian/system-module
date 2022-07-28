package com.celi.system.cii.engine.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 将同一个物料号在不同产线中的跟踪结果聚合到一起
 */
@Data
public class MaterialAgg {

    private Integer tenantId;

    private String materialCode;

    private List<Material> materialList;

    private Date minTime;

    private Date maxTime;
}
