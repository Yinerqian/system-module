package com.celi.cii.schedule.runner;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.celi.cii.common.utils.SpringContextUtils;
import com.celi.cii.schedule.entity.ParamsConfig;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiangshengjun
 * @date 2023/10/10
 */

@Slf4j
@EqualsAndHashCode
public class ScheduleRunner implements Runnable {

    @Resource
    private SpringContextUtils springContextUtils;

    private String beanName;

    private String methodName = "execute";

    private String params;

    public ScheduleRunner(String beanName, String methodName, String params) {
        this.beanName = beanName;
        if (StrUtil.isNotBlank(methodName)) {
            this.methodName = methodName;
        }
        this.params = params;
    }

    @Override
    public void run() {
        log.info("=================开始执行定时任务: {}-{}-{}", beanName, methodName, params);
        long startTime = System.currentTimeMillis();
        try {
            Object target = springContextUtils.getBean(beanName);

            Method method = null;
            Object[] paramsValueArray = null;
            if (StrUtil.isNotBlank(this.params)) {
                List<ParamsConfig> configList = JSON.parseArray(this.params, ParamsConfig.class);
                configList = configList.stream().sorted(Comparator.comparing(ParamsConfig::getIndex))
                        .collect(Collectors.toList());
                Class[] classArray = new Class[configList.size()];
                paramsValueArray = new Object[configList.size()];
                for (int i = 0; i < configList.size(); i++) {
                    ParamsConfig item = configList.get(i);
                    String type = item.getType();
                    if (StrUtil.isBlank(type)) {
                        log.error("定时任务：{}-{}执行失败，参数类型不能为空", beanName, method);
                        return;
                    }
                    classArray[i] = Class.forName(configList.get(i).getType());
                    paramsValueArray[i] = item.getValue();
                }
                method = target.getClass().getDeclaredMethod(methodName, classArray);
            } else {
                method = target.getClass().getDeclaredMethod(methodName);
            }

            ReflectionUtils.makeAccessible(method);
            if (!StrUtil.isEmpty(params)) {
                method.invoke(target, paramsValueArray);
            } else {
                method.invoke(target);
            }
            log.info("=================定时任务执行完成: {}-{},总耗时：{}", beanName, methodName, System.currentTimeMillis() - startTime);
        } catch (Exception ex) {
            log.error(String.format("定时任务执行异常 - bean：%s，方法：%s，参数：%s ", beanName, methodName, params), ex);
        }
    }
}
