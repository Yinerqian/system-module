package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import com.celi.system.cii.engine.entity.enums.EvalResultStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * @Author: Ce-li
 * @Date: 2022/8/9 19:54
 * 质量基础数据 （质量工艺库）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QualityBaseData extends BaseCreateBy {

    public static final String collectionName = "QUALITY_BASE_DATA";

    /**
     * 物料id
     */
    private String materialId;

    /**
     * 物料编号
     */
    private String materialCode;

    /**
     * 钢种
     */
    private String steelGrade;

    /**
     * 规格 （用json）MaterialSpecs类
     */
    private String specs;

    /**
     * 评价对象
     */
    private String targetName;

    /**
     * 产线Id
     */
    private String lineId;

    /**
     * 产线Id集合
     */
    private String lineIds;


    /**
     * 质量评价结果
     */
    private EvalResultStatusEnum evalResult;


    /**
     * 长
     */
    private Float length;


    /**
     * 宽
     */
    private Float width;


    /**
     * 厚
     */
    private Float height;


    /**
     * 厚度
     */
    private Float thickness;

    @JsonProperty(value = "evalResultName")
    private String getEvalResultName() {
        return this.evalResult != null ? this.evalResult.getTitle() : null;
    }
}
