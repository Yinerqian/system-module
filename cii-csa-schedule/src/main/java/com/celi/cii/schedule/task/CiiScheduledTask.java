package com.celi.cii.schedule.task;

import java.util.concurrent.ScheduledFuture;

/**
 * @author jiangshengjun
 * @date 2023/10/17
 */
public class CiiScheduledTask {

    public volatile ScheduledFuture<?> future;

    /**
     * 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
