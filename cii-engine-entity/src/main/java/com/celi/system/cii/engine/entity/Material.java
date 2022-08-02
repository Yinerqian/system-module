package com.celi.system.cii.engine.entity;

import cn.hutool.core.collection.CollUtil;
import com.celi.cii.base.entity.BaseCreateBy;
import com.celi.system.cii.engine.entity.enums.EvalResultStatusEnum;
import com.celi.system.cii.engine.entity.enums.MaterialProductionStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
     * 手动修改前的物料号
     */
    private String beforeChangeMaterialCode;

    /**
     * 排序号
     */
    private Integer orderIndex;

    /**
     * 评价对象ID
     */
    private String targetId;

    /**
     * 质量评价结果
     */
    private EvalResultStatusEnum evalResult;

    /**
     * 质量评价分数
     */
    private Float evalPoint;


    /**
     * 物料生产开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 物料生产结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 产线信息
     */
    private ProductionLine productionLine;

    /**
     * 工序列表
     */
    private List<ProductionLineProcedure> procedureList;

    /**
     * 评价对象
     * TODO 需要修改数据类型为 评价对象实体类
     */
    private List<String> evaluateProps;

    // 当前工序id
    private String currentProcedureId;

    // 当前产线物料的生产状态
    private MaterialProductionStatusEnum productionStatus;

    // 上游物料Id
    private List<String> prevMaterialIdList;

    // 下游物料Id
    private List<String> nextMaterialIdList;

    // 上游产线Id
    private List<String> prevLineIdList;

    // 下游产线Id
    private List<String> nextLineIdList;

    @JsonProperty(value = "productionStatusName")
    public String getProductionStatusName() {
        return productionStatus == null ? null : productionStatus.getTitle();
    }

//    public String getOnePrevMaterialId() {
//        return CollUtil.isNotEmpty(prevMaterialIdList) ? prevMaterialIdList.get(0) : null;
//    }
//
//    public void setOnePreMaterialId(String preMaterialId) {
//        if (prevMaterialIdList == null) {
//            prevMaterialIdList = new ArrayList<>();
//        }
//
//    }
}
