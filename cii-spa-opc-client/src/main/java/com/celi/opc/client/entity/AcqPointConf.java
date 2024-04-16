package com.celi.opc.client.entity;

import cn.hutool.core.util.StrUtil;
import com.celi.opc.client.enums.DataTypeEnum;
import lombok.Data;

/**
 * @Author changAoWen
 * @Date 2024/4/15
 * @Description 描述
 */
@Data
public class AcqPointConf {

    private String pointId;

    private String pointName;

    private String channel;

    private String device;

    private String db;

    private Object value;

    private DataTypeEnum dataType;

    private Double publishingInterval;

    private String identifier;

    public String getIdentifier() {
        return this.identifier == null ? StrUtil.format("{}.{}.{}", channel, device, db) : this.identifier;
    }
}
