package com.celi.cii.base.auto;

import com.celi.cii.base.cdo.controller.DataOpenController;
import com.celi.cii.base.config.CORSFilter;
import com.celi.cii.base.config.WebMvcConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author jiangshengjun
 * @date 2023/9/21
 */

@Configuration
@ComponentScan(basePackages = {"com.celi.cii.base", "com.celi.cii.common"})
public class BaseAutoConfigure {

    /**
     * 动态注入数据开放请求controller
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "cii", name = "open-api.prefix", matchIfMissing = false)
    public DataOpenController dataOpenController() {
        return new DataOpenController();
    }

    /**
     * 跨域请求允许
     * @return
     */
    @Bean
    public FilterRegistrationBean ciiFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new CORSFilter());
        // 跨域过滤器优先级最高
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }
}
