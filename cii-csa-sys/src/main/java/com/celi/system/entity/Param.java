package com.celi.system.entity;

import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: xining
 * @Date: 2024/06/17 15:51
 * @Description 参数
 */
@Table(name = "sys_params")
@Entity
@Data
@ToString
@Log4j2
public class Param extends BaseCreateBy{

    // 参数Id
    @Id
    @Column(name = "CONFIG_ID", updatable = false)
    private String configId;

    // 参数名称
    @Column(name = "CONFIG_NAME")
    private String configName;

    // 参数编码
    @Column(name = "CONFIG_CODE")
    private String configCode;

    // 参数值
    @Column(name = "CONFIG_VALUE")
    private String configValue;

    // 备注
    @Column(name = "REMARK")
    private String remark;
}
