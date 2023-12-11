package com.celi.cii.message.client.starter.config;

import com.celi.cii.message.sdk.config.CiiMsgConnectConfig;
import com.celi.cii.message.sdk.server.CiiMessageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AutoMessageClientConfiguration {

    @Value("${cii.message.ciiGateway}")
    public String ciiGateway;
    @Value("${cii.message.masterKey}")
    public String masterKey;
    @Value("${cii.message.version:celi}")
    public String version;

    @Bean
    public CiiMessageClient ciiMessageClient() {
        log.info("开始初始化CiiMessageClient...");
        log.info("采用网关: {}, 密钥: {}, version: {}", ciiGateway, masterKey, version);
        CiiMsgConnectConfig config = new CiiMsgConnectConfig(ciiGateway, masterKey, version);
        CiiMessageClient client = new CiiMessageClient(config);
        log.info("创建CiiMessageClient实例完成.");
        return client;
    }

}
