package com.celi.system.cii.engine.entity;

import com.celi.system.base.BaseCreateBy;
import com.celi.system.cii.engine.dto.ProducerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 物料信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Material extends BaseCreateBy {

    /**
     * 物料 Id
     */
    private String materialId;

    /**
     * 物料号
     */
    private String materialCode;

    /**
     * 物料生产开始时间
     */
    private Date startTime;

    /**
     * 物料生产结束时间
     */
    private Date endTime;

    /**
     * 产线信息
     */
    private ProductionLine productionLine;

    /**
     * 工序列表
     */
    private List<ProducerDTO> producerList;

    /**
     * 评价对象
     */
    private List<String> evaluateProps;

}
