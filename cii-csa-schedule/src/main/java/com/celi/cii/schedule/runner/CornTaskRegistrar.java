package com.celi.cii.schedule.runner;

import com.celi.cii.schedule.entity.ScheduleConfig;
import com.celi.cii.schedule.task.CiiScheduledTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangshengjun
 * @date 2023/10/17
 * 定时任务注册
 */

@Component
@Slf4j
public class CornTaskRegistrar implements DisposableBean {

    private final Map<Integer, CiiScheduledTask> scheduledTaskMap = new ConcurrentHashMap<>();

    private TaskScheduler taskScheduler;

    public CornTaskRegistrar(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    public void addCronTask(ScheduleConfig scheduleConfig) {
        ScheduleRunner scheduleRunner = new ScheduleRunner(scheduleConfig.getBeanName(), scheduleConfig.getMethodName(),
                scheduleConfig.getMethodParams());
        CronTask cronTask = new CronTask(scheduleRunner, scheduleConfig.getCornExpression());
        if (this.scheduledTaskMap.containsKey(scheduleConfig.getJobId())) {
            removeCronTask(scheduleConfig.getJobId());
        }
        log.debug("任务Id: {} 注册成功，beanName:{} methodName: {}", scheduleConfig.getJobId(),
                scheduleConfig.getBeanName(), scheduleConfig.getMethodName());
        this.scheduledTaskMap.put(scheduleConfig.getJobId(), scheduleCronTask(cronTask));
    }

    public void removeCronTask(Integer jobId) {
        CiiScheduledTask ciiScheduledTask = this.scheduledTaskMap.remove(jobId);
        if (ciiScheduledTask != null) {
            ciiScheduledTask.cancel();
            log.debug("任务Id: {} 删除成功", jobId);
        }
    }

    public CiiScheduledTask scheduleCronTask(CronTask cronTask) {
        CiiScheduledTask ciiScheduledTask = new CiiScheduledTask();
        ciiScheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
        return ciiScheduledTask;
    }


    @Override
    public void destroy() {
        for (CiiScheduledTask task : this.scheduledTaskMap.values()) {
            task.cancel();
        }
        this.scheduledTaskMap.clear();
    }
}
