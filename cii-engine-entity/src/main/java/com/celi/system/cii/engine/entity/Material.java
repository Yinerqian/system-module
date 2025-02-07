package com.celi.system.cii.engine.entity;

import cn.hutool.core.collection.CollUtil;
import com.celi.cii.base.entity.BaseCreateBy;
import com.celi.system.cii.engine.entity.enums.EvalResultStatusEnum;
import com.celi.system.cii.engine.entity.enums.MaterialProductionStatusEnum;
import com.celi.system.cii.engine.entity.enums.QualityAnalysisStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * 跟踪计划 Id（如果存在批次跟踪情况）
     */
    private String planId;

    private String nextPlanId;

    /*
     * 原始计划 Id
     * */
    private String originPlanId;

    /**
     * 钢种
     */
    private String steelGrade;

    /**
     * 规格 （用json）MaterialSpecs类
     */
    private String specs;

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

    private String targetName;

    /**
     * 产线id
     */
    private String lineId;

    /**
     * 产线Id集合
     */
    private String lineIds;

    /**
     * 评价规则实体
     */
    private List<EvalRule> ruleList;

    /**
     * 特征值列表
     */
    private List<QualityChValue> chValueList;

    /**
     * 质量评价结果
     */
    private EvalResultStatusEnum evalResult;

    /**
     * 分析状态
     */
    private QualityAnalysisStatusEnum analysisStatus;

    /**
     * 质量评价分数
     */
    private Float evalScore;


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

    // 分组求和
    private Integer count;

    // 当前工序id
    private String currentProcedureId;

    // 当前工序次序
    private Integer currentProcedureIndex;

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

    // 特征值计算结果列表（由引擎计算而来）
    private List<QualityChValue> chValueResult = new ArrayList<>();

    @JsonProperty(value = "productionStatusName")
    public String getProductionStatusName() {
        return productionStatus == null ? null : productionStatus.getTitle();
    }

    @JsonProperty(value = "evalResultName")
    public String evalResultName() {
        return evalResult == null ? null : evalResult.getTitle();
    }

    @JsonProperty(value = "analysisStatusName")
    public String analysisStatusName() {
        return analysisStatus == null ? null : analysisStatus.getTitle();
    }

    // 预存的一些属性 比如命中率计算需要，宽度容忍范围之类
    private Map<String, Object> props;

    // 存储质量分析结果，影响因子和缺陷分析结果
    private AnalyseResult analyseResult;

    // 备注信息
    private String remark;

    // 物料所处位置（结合工序可精确物料所处产线的具体位置）
    private String zone;

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
