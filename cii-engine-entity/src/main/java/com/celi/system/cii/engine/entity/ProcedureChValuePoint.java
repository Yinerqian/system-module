package com.celi.system.cii.engine.entity;

/**
 *  特征值关联点位
 */
public class ProcedureChValuePoint {

    /**
     * 特征值id
     */
    private String chValueId;

    /**
     * 租户ID
     */
    private Integer tenantId;

    /**
     * 点位id
     */
    private String pointId;

    /**
     * 点位uuid
     */
    private String pointUuid;

    /**
     * 点位名称
     */
    private String pointName;

    /**
     * 关键点位别名
     */
    private String aliasName;

    /**
     * 序号
     */
    private Integer orderIndex;

    /**
     * 计算脚本，默认从特征值管理中继承
     */
    private String chValueScript;

}
