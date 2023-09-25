package com.celi.cii.ws.handler;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangshengjun
 * @date 2023/9/21
 * websocket 接口，系统默认实现支持DefaultWebSocketHandler 如果需要自定义 实现该接口
 */
public interface IWebSocketHandler extends WebSocketHandler {

    Logger log = LoggerFactory.getLogger(IWebSocketHandler.class);

    /**
     * key
     *  - 用户注册 userId
     *  - 用户不注册 sessionId
     * value
     *  session
     */
    Map<String, WebSocketSession> SESSION_MAP = new ConcurrentHashMap<>();

    /**
     * 用户id和session绑定
     */
    Map<String, WebSocketSession> USER_SESSION_BIND = new HashMap<>();

    /**
     * 广播消息
     */
    public default void broadcastMessage(String message) {
        if (!CollectionUtils.isEmpty(SESSION_MAP)) {
            for (WebSocketSession s : SESSION_MAP.values()) {
                if (s != null && s.isOpen()) {
                    try {
                        s.sendMessage(new TextMessage(message));
                    } catch (IOException e) {
                        log.error("消息发送失败");
                    }
                }
            }
        }
    }

    /**
     * 指定消息推送
     */
    public default void sendMessageByUserId(String userId, String message) {
        if (USER_SESSION_BIND.containsKey(userId)) {
            WebSocketSession session = USER_SESSION_BIND.get(userId);
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(message));
                } catch (IOException e) {
                    log.error("消息发送失败");
                }
            }
        }
    }

    /**
     * 删除用户ID和session的关联关系
     */
    public default void removeUserAndSessionRef(WebSocketSession session) {
        Iterator<Map.Entry<String, WebSocketSession>> iterator = USER_SESSION_BIND.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, WebSocketSession> s = iterator.next();
            String sessionId = s.getValue().getId();
            if (StrUtil.equals(sessionId, session.getId())) {
                iterator.remove();
                return;
            }
        }
    }

}
