package com.celi.system.cii.engine.dto;

import com.celi.system.cii.engine.entity.ProductionLine;
import com.celi.system.cii.engine.entity.ProductionLineProcedure;
import lombok.Data;

import java.util.List;

/**
 * 产线 DTO
 */
@Data
public class ProductionLineDTO extends ProductionLine {

    /**
     * 产线下工序列表
     */
    private List<ProductionLineProcedure> procedureList;

    /**
     * 产线下点位 (uuid) 列表
     */
    private List<String> pointList;

}
