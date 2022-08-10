package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * 长
     */
    private String length;


    /**
     * 宽
     */
    private String width;


    /**
     * 厚
     */
    private String height;


    /**
     * 重量
     */
    private String weight;
}
