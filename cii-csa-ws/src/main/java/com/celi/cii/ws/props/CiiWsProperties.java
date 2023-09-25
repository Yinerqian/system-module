package com.celi.cii.ws.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jiangshengjun
 * @date 2023/9/21
 */
@ConfigurationProperties("cii.ws")
@Data
public class CiiWsProperties {

    /**
     * ws 地址
     */
    private String url;

    /**
     * 开启心跳检测
     */
    private boolean enableHeartBeat = false;

    /**
     * 开启用户注册绑定
     */
    private boolean enableUserRegister = false;

    /**
     * 心跳时间间隔
     */
    private Long heartBeatInterval;
}
