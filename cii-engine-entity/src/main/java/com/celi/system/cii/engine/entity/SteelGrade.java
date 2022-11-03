package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Ce-li
 * @Date: 2022/10/28 9:13
 * 钢种种管理
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SteelGrade extends BaseCreateBy {

    /**
     * 钢种种Id
     */
    private String steelId;

    /**
     * 钢种 名称
     */
    private String steelName;

    /**
     * 钢种 Code
     */
    private String steelCode;

    /**
     * 钢种简称
     */
    private String abbrName;

    /**
     * 备注
     */
    private String remark;

    private List<SteelGradeProp> propList;

}
