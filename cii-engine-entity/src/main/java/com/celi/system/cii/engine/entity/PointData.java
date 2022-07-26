package com.celi.system.cii.engine.entity;

import lombok.Data;

/**
 * 点位数据实体类
 */

@Data
public class PointData {


    /**
     * 点位数据类型，PLC/业务系统 点位数据
     */
    public String type;

    /**
     * uuid
     */
    public String uuid;

    /**
     * 时间戳
     */
    public String timestamp;

    /**
     * 点位的值
     */
    public Double value;

}
