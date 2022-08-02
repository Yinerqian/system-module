package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.Data;

/**
 * 质量报告模板
 */
@Data
public class EvalReportTemplate extends BaseCreateBy {
    /**
     *  Id
     */
    private String templateId;


    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 产线Id
     */
    private String lineId;

    /**
     * 产线name
     */
    private String lineName;

    /**
     * 评价对象
     */
    private String targetId;

    /**
     * 文件路径
     */
    private String templateOssPath;

    /**
     * 备注
     */
    private String remark;


}
