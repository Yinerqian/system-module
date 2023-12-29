package com.celi.auth.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "cii.auth")
public class CiiAuthProperties {

    /**
     * 访问权限添加接口的key
     */
    private String masterKey;
    private String permissionUrl;
}
