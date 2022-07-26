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

    @Value("${url.prefix}")
    private String prefix;

    @Bean
    @ConditionalOnMissingBean(value = { AbstractSaTokenConfiguration.class })
    public AbstractSaTokenConfiguration saTokenConfiguration() {
        return new SaTokenConfigure(prefix);
    }
}
