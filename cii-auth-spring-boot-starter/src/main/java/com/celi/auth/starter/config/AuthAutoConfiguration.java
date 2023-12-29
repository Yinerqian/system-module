package com.celi.auth.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @project: system-parent
 * @title: AutoConfiguration
 * @author: lihq
 * @date: 2023/12/29 14:51
 * @version: v1.0
 * @description: 自动配置
 */

@Configuration
@ComponentScan(basePackages = {"com.celi.auth.starter"})
public class AuthAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "cii.auth", value = "active", havingValue = "true")
    public AutoRegisterPermission autoRegisterPermission() {
        return new AutoRegisterPermission();
    }

}
