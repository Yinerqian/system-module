package com.celi.cii.base.auto;

import com.celi.cii.base.exception.ServiceExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiangshengjun
 * @date 2023/9/21
 */

@Configuration
public class HandlerAutoConfigure {

    /**
     * 全局异常处理
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(value = ServiceExceptionHandler.class)
    public ServiceExceptionHandler serviceExceptionHandler() {
        return new ServiceExceptionHandler();
    }
}
