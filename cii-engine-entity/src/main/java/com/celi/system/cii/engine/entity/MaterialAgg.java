package com.celi.system.cii.engine.entity;

import com.celi.system.cii.engine.entity.enums.EvalResultStatusEnum;
import lombok.Data;

import java.util.ArrayList;
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

    // 总体评价结果,由其关联的materialList中的评价结果共同确定
    private EvalResultStatusEnum evalResult;

    // 将同一物料不同产线的工序放到一个list中
    private List<ProductionLineProcedure> allProcedureList;

    public void flatAllProcedures() {
        if (allProcedureList == null) {
            allProcedureList = new ArrayList<>();
        }

        for (Material material : materialList) {
            if (material.getProcedureList() != null) {
                allProcedureList.addAll(material.getProcedureList());
            }
        }
    }
}
