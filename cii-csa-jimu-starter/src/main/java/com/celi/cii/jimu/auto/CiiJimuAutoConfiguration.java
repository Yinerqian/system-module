package com.celi.cii.jimu.auto;

import com.celi.cii.jimu.convert.MyApiDataConvertAdapter;
import com.celi.cii.jimu.func.JmExpressCustomImpl;
import com.celi.cii.jimu.token.JimuReportTokenService;
import org.jeecg.modules.jmreport.config.init.JimuReportConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiangshengjun
 * @date 2023/9/21
 */

@Configuration
@AutoConfigureAfter({ JimuReportConfiguration.class })
@ComponentScan(basePackages = {"org.jeecg.modules.jmreport", "com.celi.cii.common"})
public class CiiJimuAutoConfiguration {

    /**
     * 积木数据分页接口转换
     * @return
     */
    @Bean
    public MyApiDataConvertAdapter myApiDataConvertAdapter() {
        return new MyApiDataConvertAdapter();
    }

    /**
     * 积木token模拟
     * @return
     */
    @Bean
    public JimuReportTokenService jimuReportTokenService() {
        return new JimuReportTokenService();
    }

    /**
     * 积木表达式自定义
     * 如果需要自定义，自定义JmExpressCustomImpl类，即可
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(value = JmExpressCustomImpl.class)
    public JmExpressCustomImpl jmExpressCustomImpl() {
        return new JmExpressCustomImpl();
    }

}
