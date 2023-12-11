package com.celi.system.message.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author celi
 * @date 2023-07-18 17:27
 * @desc 请求发送的参数
 */
@Data
public class MsgSendRequestDTO {

    /**
     * 每次发送请求生成的请求ID
     */
    private String requestId;

    /**
     * 第三方客户端请求时 需要携带appId 该appId由本系统生成 然后告知第三方APP
     */
    private String appId;

    /**
     * 渠道需要的参数
     */
    private Map<String, Object> channelParams = new HashMap<>();

    /**
     * 消息模板编码
     */
    private String templateCode;

    /**
     * 消息模板需要的参数
     */
    private Map<String, Object> templateParams = new HashMap<>();

    /**
     * 业务场景编码
     */
    private String topicCode;

}
