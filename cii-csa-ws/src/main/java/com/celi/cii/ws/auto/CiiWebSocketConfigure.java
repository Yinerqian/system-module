package com.celi.cii.ws.auto;

import com.celi.cii.ws.handler.impl.DefaultWebSocketHandler;
import com.celi.cii.ws.handler.IWebSocketHandler;
import com.celi.cii.ws.props.CiiWsProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfiguration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author jiangshengjun
 * @date 2023/9/21
 * 只有server.type 为prod 并且配置了 cii.ws.url该配置才会生效
 */

@Configuration
@AutoConfigureAfter(value = SchedulingConfiguration.class)
@EnableWebSocket
@EnableScheduling
@ConditionalOnExpression("#{!'${cii.ws.url}'.equals(null) && !'${cii.ws.url}'.trim().equals('') && '${server.type}'.equals('prod')}")
public class CiiWebSocketConfigure implements WebSocketConfigurer {


    @Bean
    @ConditionalOnMissingBean
    public CiiWsProperties ciiWsProperties() {
        return new CiiWsProperties();
    }

    /**
     * 默认websocketHandler
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(value = IWebSocketHandler.class)
    public IWebSocketHandler webSocketHandler() {
        return new DefaultWebSocketHandler(ciiWsProperties());
    }

    @ConditionalOnClass(value = SchedulingConfiguration.class)
    @Bean
    public TaskScheduler taskScheduler() {
        // 设置核心线程数
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(availableProcessors);
        taskScheduler.initialize();
        return taskScheduler;
    }

    @Bean
    @ConditionalOnMissingBean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), ciiWsProperties().getUrl())
                .setAllowedOrigins("*");
    }

}
