package com.celi.cii.schedule.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author jiangshengjun
 * @date 2023/10/10
 */

@Configuration
@EnableScheduling
@ComponentScan(basePackages = {"com.celi.cii.schedule", "com.celi.cii.common"})
@EntityScan("com.celi.cii.schedule.entity")
@EnableJpaRepositories("com.celi.cii.schedule.dao")
public class ScheduleAutoConfigure {

    @Bean
    @ConditionalOnMissingBean(TaskScheduler.class)
    public TaskScheduler taskScheduler() {
        // 设置核心线程数
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(availableProcessors);
        taskScheduler.initialize();
        return taskScheduler;
    }
}
