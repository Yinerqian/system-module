package com.celi.opc.client.entity;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author changAoWen
 * @Date 2024/4/15
 * @Description client配置类
 */
@Data
@Component
@Configuration
@PropertySource("classpath:opcua.properties")
public class ClientProperties {

    @Value("${opcua.client.app.name}")
    private String appName;

    @Value("${opcua.client.app.uri}")
    private String appUri;

    @Value("${opcua.client.cert.path}")
    private String certPath;

    @Value("${opcua.client.cert.file}")
    private String certFile;

    @Value("${opcua.client.cert.alias}")
    private String certAlias;

    @Value("${opcua.client.cert.common.name}")
    private String commonName;

    @Value("${opcua.client.cert.organization}")
    private String organization;

    @Value("${opcua.client.cert.organization.unit}")
    private String orgUnit;

    @Value("${opcua.client.cert.locality.name}")
    private String localityName;

    @Value("${opcua.client.cert.state.name}")
    private String stateName;

    @Value("${opcua.client.cert.country.code}")
    private String countryCode;

    @Value("${opcua.client.cert.dns.name}")
    private String dnsName;

    @Value("${opcua.client.cert.ip.address}")
    private String ipAddress;

    @Value("${opcua.client.cert.keystore.password}")
    private String keyPassword;

    public String getCertPath() {
        if (this.certPath == null) {
            return System.getProperty("user.home") + "\\Desktop";
        } else if (this.certPath.contains("Desktop")) {
            return System.getProperty("user.home") + "\\Desktop";
        } else {
            return this.certPath;
        }
    }

}
