package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import com.celi.system.cii.engine.entity.enums.SteelPropDataTypeEnum;
import com.celi.system.cii.engine.entity.enums.SteelPropTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Author: Ce-li
 * @Date: 2022/10/28 9:13
 * 钢种属性
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SteelGradeProp extends BaseCreateBy {

    /**
     * 属性Id
     */
    private String propId;

    /**
     * 钢种id，可以为空，为空时表示通用钢种id
     */
    private String steelId;

    /**
     * 属性名称
     */
    private String propName;

    /**
     * 属性Code
     */
    private String propCode;

    /**
     * 属性单位
     */
    private String propUnit;

    /**
     * 属性类别  specical-专有属性 common-通用属性
     */
    private SteelPropTypeEnum propType;

    /**
     * 数据类型
     */
    private SteelPropDataTypeEnum dataType;

    /**
     * 属性脚本
     */
    private String script;

    /**
     * 备注
     */
    private String remark;

    @JsonProperty(value = "propTypeName")
    public String propTypeName() {
        return this.propType != null ? this.propType.getTitle() : null;
    }

    @JsonProperty(value = "propDataTypeName")
    public String propDataTypeName() {
        return this.dataType != null ? this.dataType.getTitle() : null;
    }

}
