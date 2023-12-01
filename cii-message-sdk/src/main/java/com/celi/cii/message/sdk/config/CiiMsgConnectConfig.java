package com.celi.cii.message.sdk.config;

import lombok.Data;
import lombok.NonNull;

/**
 * 连接平台的配置类
 */
@Data
public class CiiMsgConnectConfig {

    private String ciiGateway; // cii网关地址
    private String masterKey; // masterKey - 可以告知服务属于哪个租户
    private String version; // header的version 默认为celi
    public static final String DEFAULT_VERSION = "celi"; // 默认的version
    public static final String SEND_MSG_URL = "cii-message/msg/send";  // 发送消息地址
    public static final String SEND_TO_SERUS_MSG_URL = "cii-message/msg/sendToUsers";  // 发送消息地址
    public static final String FETCH_RES_URL = "cii-message/msg/fetchSendRes"; // 获取发送结果地址
    public static final String THIRD_APP_LOGIN_URL = "cii-message/deviceToken/login";  // 第三方登录地址
    public static final String THIRD_APP_LOGINOUT_URL = "cii-message/deviceToken/logout";  // 第三方登出地址

    public CiiMsgConnectConfig(@NonNull String ciiGateway, @NonNull String masterKey) {
        this(ciiGateway, masterKey, DEFAULT_VERSION);
    }

    public CiiMsgConnectConfig(@NonNull String ciiGateway, @NonNull String masterKey, @NonNull String version) {
        this.ciiGateway = ciiGateway;
        this.masterKey = masterKey;
        this.version = version;
    }

    public CiiMsgConnectConfig version(String version) {
        this.version = version;
        return this;
    }

    public CiiMsgConnectConfig ciiGateway(String ciiGateway) {
        this.ciiGateway = ciiGateway;
        return this;
    }

    public CiiMsgConnectConfig masterKey(String masterKey) {
        this.masterKey = masterKey;
        return this;
    }

}
