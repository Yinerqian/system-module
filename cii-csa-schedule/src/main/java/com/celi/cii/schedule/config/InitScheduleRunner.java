package com.celi.cii.schedule.config;

import cn.hutool.core.collection.CollectionUtil;
import com.celi.cii.schedule.entity.ScheduleConfig;
import com.celi.cii.schedule.runner.CornTaskRegistrar;
import com.celi.cii.schedule.runner.ScheduleRunner;
import com.celi.cii.schedule.service.ScheduleConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jiangshengjun
 * @date 2023/10/10
 */

@Slf4j
@Component
public class InitScheduleRunner implements CommandLineRunner {

    private ScheduleConfigService scheduleConfigService;
    private CornTaskRegistrar cornTaskRegistrar;

    public InitScheduleRunner(ScheduleConfigService scheduleConfigService, CornTaskRegistrar cornTaskRegistrar) {
        this.scheduleConfigService = scheduleConfigService;
        this.cornTaskRegistrar = cornTaskRegistrar;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("======================开始注册定时任务======================");
        List<ScheduleConfig> scheduleList = scheduleConfigService.queryAllNormalSchedule();
        if (CollectionUtil.isNotEmpty(scheduleList)) {
            scheduleList.forEach(scheduleConfig -> {
                cornTaskRegistrar.addCronTask(scheduleConfig);
            });
        }
    }
}
