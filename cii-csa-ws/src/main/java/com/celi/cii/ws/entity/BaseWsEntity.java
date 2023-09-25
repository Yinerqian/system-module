package com.celi.cii.ws.entity;

import lombok.Data;

/**
 * @author jiangshengjun
 * @date 2023/9/22
 * websocket消息实体
 */

@Data
public class BaseWsEntity<T> {

    /**
     * 类型 1 和 2是保留 类型
     * 1 用户注册
     * 2 心跳保持
     */
    public static final String USER_REGISTER = "1";
    public static final String HEART_BEAT = "2";

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 消息内容
     */
    private T message;
}
