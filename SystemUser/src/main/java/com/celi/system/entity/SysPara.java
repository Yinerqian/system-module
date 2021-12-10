package com.celi.system.entity;

import com.celi.system.enums.SysParaEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @Author: Changaowen
 * @Date: 2021/12/9 19:42
 */
@Table(name = "sys_para")
@Entity
@Data
public class SysPara extends BaseCreateBy{

    /**
     * 参数id
     */
    @Id
    @Column(name = "PARA_ID", columnDefinition = "int(11)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paraId;

    /**
     * 参数名称
     */
    @Column(name = "PARA_NAME", columnDefinition = "varchar(100)")
    private String paraName;

    /**
     * 参数编码
     */
    @Column(name = "PARA_CODE", columnDefinition = "varchar(100)")
    private String paraCode;

    /**
     * 参数值
     */
    @Column(name = "PARA_VALUE", columnDefinition = "varchar(100)")
    private String paraValue;

    /**
     * 参数类型，Number | Boolean | String
     */
    @Column(name = "PARA_TYPE", columnDefinition = "varchar(20)")
    private SysParaEnum paraType;

    /**
     * 默认值
     */
    @Column(name = "DEFAULT_VALUE", columnDefinition = "varchar(100)")
    private String defaultValue;

    /**
     * 备注
     */
    @Column(name = "REMARK", columnDefinition = "varchar(500)")
    private String remark;

    @JsonProperty(value = "paraTypeName")
    public String getParaTypeName() {
        return this.paraType != null ? this.paraType.getDesc() : null;
    }
}
