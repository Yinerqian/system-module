package com.celi.cii.ws.handler.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.celi.cii.ws.entity.BaseWsEntity;
import com.celi.cii.ws.entity.UserRegistry;
import com.celi.cii.ws.handler.IWebSocketHandler;
import com.celi.cii.ws.props.CiiWsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;

import java.io.IOException;

import static com.celi.cii.ws.common.WsConstant.USER_ID_KEY;
import static com.celi.cii.ws.entity.BaseWsEntity.HEART_BEAT;
import static com.celi.cii.ws.entity.BaseWsEntity.USER_REGISTER;

/**
 * @author jiangshengjun
 * @date 2023/9/21
 */
@Slf4j
public class DefaultWebSocketHandler implements IWebSocketHandler {

    private CiiWsProperties ciiWsProperties;

    public DefaultWebSocketHandler(CiiWsProperties ciiWsProperties) {
        this.ciiWsProperties = ciiWsProperties;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.error("{} 连接成功", session.getId());
        SESSION_MAP.put(session.getId(), session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String content = message.getPayload().toString();
        BaseWsEntity wsEntity = JSON.parseObject(content, BaseWsEntity.class);
        if (ciiWsProperties.isEnableUserRegister()) {
            userRegistryProcess(wsEntity, session);
        }
        if (ciiWsProperties.isEnableHeartBeat()) {

        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.error("{} 连接关闭", session.getId());
        try {
            if (session.isOpen()) {
                session.close();
            }
        } catch (IOException e) {
            log.error("关闭失败", e);
        } finally {
            SESSION_MAP.remove(session.getId());
            if (ciiWsProperties.isEnableUserRegister()) {
                removeUserAndSessionRef(session);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.error("{} 连接关闭", session.getId());
        try {
            if (session.isOpen()) {
                session.close();
            }
        } catch (IOException e) {
            log.error("关闭失败", e);
        } finally {
            SESSION_MAP.remove(session.getId());
            if (ciiWsProperties.isEnableUserRegister()) {
                removeUserAndSessionRef(session);
            }
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private void userRegistryProcess(BaseWsEntity wsEntity, WebSocketSession session) {
        if (StrUtil.equals(wsEntity.getMsgType(), USER_REGISTER)) {
            String userId = ((JSONObject) wsEntity.getMessage()).getString(USER_ID_KEY);
            if (StrUtil.isNotBlank(userId)) {
                USER_SESSION_BIND.put(userId, session);
                // 通知用户绑定成功
                sendMessageByUserId(userId, "用户信息绑定成功");
            }
        }
    }

    private void heartBeat(BaseWsEntity wsEntity, WebSocketSession session) {
        if (StrUtil.equals(wsEntity.getMsgType(), HEART_BEAT)) {
            if (ciiWsProperties.isEnableUserRegister()) {
                // 开启用户注册session

            }
        }
    }
}
