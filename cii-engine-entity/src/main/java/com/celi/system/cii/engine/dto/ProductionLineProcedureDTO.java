package com.celi.system.cii.engine.dto;

import com.celi.system.cii.engine.entity.ProductionLineProcedure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 产线工序 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionLineProcedureDTO extends ProductionLineProcedure {

    /**
     * 工序下点位 (uuid) 列表
     */
    private List<String> pointList;
}
