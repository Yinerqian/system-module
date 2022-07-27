package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import com.celi.cii.base.enums.DataTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvalTargetProp extends BaseCreateBy {

    /**
     * 属性ID
     */
    private String propId;

    /**
     * 对象ID
     */
    private String targetId;

    /**
     * 序号
     */
    private Integer orderIndex;

    /**
     * 属性单位
     */
    private String propUnit;

    /**
     * 对象名称
     */
    private String propName;

    /**
     * 对象编码
     */
    private String propCode;

    /**
     * 类型
     * 数字number，字符串string
     */
    private DataTypeEnum dataType;

    @JsonProperty(value = "DataSetTypeName")
    public String getDataSetTypeName() {
        return this.dataType == null ? "" : this.dataType.getTitle();
    }

    /**
     * 图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;


}
