package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import com.celi.system.cii.engine.entity.enums.EvalResultStatusEnum;
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
     * 产线Id
     */
    private String lineId;

    /**
     * 物料编号
     */
    private String materialCode;


    /**
     * 钢种名称
     */
    private String steelName;

    /**
     * 质量评价结果
     */
    private EvalResultStatusEnum evalResult;

    /**
     * 钢种
     */
    private String steelGrade;

    /**
     * 规格 （用json）MaterialSpecs类
     */
    private String specs;


    /**
     * 长
     */
    private BigDecimal length;


    /**
     * 宽
     */
    private BigDecimal width;


    /**
     * 厚
     */
    private BigDecimal height;


    /**
     * 重量
     */
    private BigDecimal weight;
}
