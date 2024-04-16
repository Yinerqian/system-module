package com.celi.opc.client.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author changAoWen
 * @Date 2024/4/15
 * @Description opc配置参数
 */
@Data
@Component
@Configuration
@PropertySource("classpath:opcua.properties")
public class ServerProperties {

    @Value("${opcua.server.endpoint.url}")
    private String endpointUrl;

    @Value("${opcua.server.idp.username}")
    private String idpUsername;

    @Value("${opcua.server.idp.password}")
    private String idpPassword;

    @Value("${opcua.server.name.space.index}")
    private Integer nameSpaceIndex;

}