package com.celi.system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiangshengjun
 * @date 2021/12/7
 */
@Configuration
public class SaTokenConfig {

    /**
     * 接口前缀
     */
    @Value("${url.prefix}")
    private String prefix;
    /**
     * 白名单
     */
    @Value("${url.white-list}")
    private String[] whiteList;

    @Bean
    @ConditionalOnMissingBean(value = { AbstractSaTokenConfiguration.class })
    public AbstractSaTokenConfiguration saTokenConfiguration() {
        return new SaTokenConfigure(prefix, whiteList);
    }
}
