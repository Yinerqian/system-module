package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 产线
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvalTarget extends BaseCreateBy {

    /**
     * 对象ID
     */
    private String targetId;

    /**
     * 序号
     */
    private Integer orderIndex;

    /**
     * 对象名称
     */
    private String targetName;

    /**
     * 对象编码
     */
    private String targetCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 图标
     */
    private String icon;

    /**
     * 长度数据标记，1-包含 0-不包含
     */
    private Integer lengthDataFlag;
}
