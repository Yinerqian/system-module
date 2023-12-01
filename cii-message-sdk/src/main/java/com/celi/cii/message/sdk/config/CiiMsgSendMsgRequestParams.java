package com.celi.cii.message.sdk.config;

import cn.hutool.core.map.MapUtil;
import com.celi.system.message.entity.MsgSendRequestDTO;
import com.celi.system.message.entity.MsgSendToUsersRequestDTO;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CiiMsgSendMsgRequestParams {

    private MsgSendToUsersRequestDTO requestDTO;

    public CiiMsgSendMsgRequestParams() {}

    public CiiMsgSendMsgRequestParams(MsgSendToUsersRequestDTO requestDTO) {
        this.requestDTO = requestDTO;
    }

    private synchronized void createRequestDto() {
        if (this.requestDTO == null) {
            this.requestDTO = new MsgSendToUsersRequestDTO();
        }
    }

    public CiiMsgSendMsgRequestParams requestId(String requestId) {
        createRequestDto();
        requestDTO.setRequestId(requestId);
        return this;
    }

    public CiiMsgSendMsgRequestParams templateCode(String templateCode) {
        createRequestDto();
        requestDTO.setTemplateCode(templateCode);
        return this;
    }

    public CiiMsgSendMsgRequestParams templateParams(Map<String, Object> params) {
        if (MapUtil.isEmpty(params)) {
            return this;
        }
        createRequestDto();
        if (requestDTO.getTemplateParams() == null) {
            requestDTO.setTemplateParams(new HashMap<String, Object>());
        }
        requestDTO.getTemplateParams().putAll(params);
        return this;
    }

    public CiiMsgSendMsgRequestParams templateParams(String key, Object val) {
        Map<String, Object> params = new HashMap<>();
        params.put(key, val);
        return templateParams(params);
    }

    public CiiMsgSendMsgRequestParams topicCode(String topicCode) {
        createRequestDto();
        requestDTO.setTopicCode(topicCode);
        return this;
    }

    public CiiMsgSendMsgRequestParams toUsers(String toUsers) {
        createRequestDto();
        requestDTO.setToUsers(toUsers);
        return this;
    }

    public CiiMsgSendMsgRequestParams channelParams(Map<String, Object> params) {
        if (MapUtil.isEmpty(params)) {
            return this;
        }
        createRequestDto();
        if (requestDTO.getChannelParams() == null) {
            requestDTO.setChannelParams(new HashMap<String, Object>());
        }
        requestDTO.getChannelParams().putAll(params);
        return this;
    }

    public CiiMsgSendMsgRequestParams channelParams(String k, Object v) {
        Map<String, Object> params = new HashMap<>();
        params.put(k, v);
        return channelParams(params);
    }

    public CiiMsgSendMsgRequestParams appId(String appId) {
        createRequestDto();
        requestDTO.setAppId(appId);
        return this;
    }

}
