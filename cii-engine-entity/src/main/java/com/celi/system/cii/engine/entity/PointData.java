package com.celi.system.cii.engine.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * 点位数据实体类
 */

@Data
public class PointData {

    public PointData(JSONObject jsonObject) {
        String json = jsonObject.toJSONString();

        if (json.contains("type")) {
            this.type = jsonObject.getString("type");
        }

        if (json.contains("uuid")) {
            this.type = jsonObject.getString("uuid");
        }

        if (json.contains("timestamp")) {
            this.type = jsonObject.getString("timestamp");
        }

        if (json.contains("value")) {
            this.type = jsonObject.getString("value");
        }
    }


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
