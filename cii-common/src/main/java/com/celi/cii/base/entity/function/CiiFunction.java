package com.celi.cii.base.entity.function;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.celi.cii.base.annotation.CiiFunctionAnnotation;

import java.util.Date;

/**
 * 常用功能封装为 Groovy 函数，通过 cii 调用
 */
public class CiiFunction {

    public static final String functionName = "cii";

    private final String timeFormat = "yyyy-MM-dd HH:mm:ss";

    private final String dateFormat = "yyyy-MM-dd";

    private final String startTime = " 00:00:00";

    private final String endTime = " 23:59:59";

    @CiiFunctionAnnotation(description = "当前时间，格式 yyyy-MM-dd HH:mm:ss")
    public String now() {
        return DateUtil.now();
    }

    @CiiFunctionAnnotation(description = "当前日期，格式 yyyy-MM-dd")
    public String today() {
        return DateUtil.today();
    }

    @CiiFunctionAnnotation(description = "偏移天数，偏移量是正数向未来偏移，负数向过去偏移，格式 yyyy-MM-dd")
    public String offsetDay(int offset) {
        return DateUtil.offsetDay(new Date(), offset).toString(dateFormat);
    }

    @CiiFunctionAnnotation(description = "偏移天数的一天开始时间，格式 yyyy-MM-dd 00:00:00")
    public String offsetDayStart(int offset) {
        return getStartTime(DateUtil.offsetDay(new Date(), offset));
    }

    @CiiFunctionAnnotation(description = "偏移天数的一天结束时间，格式 yyyy-MM-dd 23:59:59")
    public String offsetDayEnd(int offset) {
        return getEndTime(DateUtil.offsetDay(new Date(), offset));
    }

    @CiiFunctionAnnotation(description = "昨天时间，格式 yyyy-MM-dd HH:mm:ss")
    public String yesterday() {
        return DateUtil.yesterday().toString(timeFormat);
    }

    @CiiFunctionAnnotation(description = "昨天开始时间，格式 yyyy-MM-dd 00:00:00")
    public String yesterdayStart() {
        return getStartTime(DateUtil.yesterday());
    }

    @CiiFunctionAnnotation(description = "昨天结束时间，格式 yyyy-MM-dd 23:59:59")
    public String yesterdayEnd() {
        return getEndTime(DateUtil.yesterday());
    }

    @CiiFunctionAnnotation(description = "明天时间，格式 yyyy-MM-dd HH:mm:ss")
    public String tomorrow() {
        return DateUtil.tomorrow().toString(timeFormat);
    }

    @CiiFunctionAnnotation(description = "明天开始时间，格式 yyyy-MM-dd 00:00:00")
    public String tomorrowStart() {
        return getStartTime(DateUtil.tomorrow());
    }

    @CiiFunctionAnnotation(description = "明天结束时间，格式 yyyy-MM-dd 23:59:59")
    public String tomorrowEnd() {
        return getEndTime(DateUtil.tomorrow());
    }

    @CiiFunctionAnnotation(description = "上周时间，格式 yyyy-MM-dd HH:mm:ss")
    public String lastWeek() {
        return DateUtil.lastWeek().toString(timeFormat);
    }

    @CiiFunctionAnnotation(description = "上周开始时间，格式 yyyy-MM-dd 00:00:00")
    public String lastWeekStart() {
        return getStartTime(DateUtil.lastWeek());
    }

    @CiiFunctionAnnotation(description = "上周结束时间，格式 yyyy-MM-dd 23:59:59")
    public String lastWeekEnd() {
        return getEndTime(DateUtil.lastWeek());
    }

    @CiiFunctionAnnotation(description = "下周时间，格式 yyyy-MM-dd HH:mm:ss")
    public String nextWeek() {
        return DateUtil.nextWeek().toString(timeFormat);
    }

    @CiiFunctionAnnotation(description = "下周开始时间，格式 yyyy-MM-dd 00:00:00")
    public String nextWeekStart() {
        return getStartTime(DateUtil.nextWeek());
    }

    @CiiFunctionAnnotation(description = "下周结束时间，格式 yyyy-MM-dd 23:59:59")
    public String nextWeekEnd() {
        return getEndTime(DateUtil.nextWeek());
    }

    @CiiFunctionAnnotation(description = "上个月时间，格式 yyyy-MM-dd HH:mm:ss")
    public String lastMonth() {
        return DateUtil.lastMonth().toString(timeFormat);
    }

    @CiiFunctionAnnotation(description = "上个月开始时间，格式 yyyy-MM-dd 00:00:00")
    public String lastMonthStart() {
        return getStartTime(DateUtil.lastMonth());
    }

    @CiiFunctionAnnotation(description = "上个月结束时间，格式 yyyy-MM-dd 23:59:59")
    public String lastMonthEnd() {
        return getEndTime(DateUtil.lastMonth());
    }

    @CiiFunctionAnnotation(description = "下个月时间，格式 yyyy-MM-dd HH:mm:ss")
    public String nextMonth() {
        return DateUtil.nextMonth().toString(timeFormat);
    }

    @CiiFunctionAnnotation(description = "下个月开始时间，格式 yyyy-MM-dd 00:00:00")
    public String nextMonthStart() {
        return getStartTime(DateUtil.nextMonth());
    }

    @CiiFunctionAnnotation(description = "下个月结束时间，格式 yyyy-MM-dd 23:59:59")
    public String nextMonthEnd() {
        return getEndTime(DateUtil.nextMonth());
    }

    public String uuid() {
        return IdUtil.simpleUUID();
    }

    private String getStartTime(DateTime dateTime) {
        return dateTime.toString(dateFormat) + startTime;
    }

    private String getEndTime(DateTime dateTime) {
        return dateTime.toString(dateFormat) + endTime;
    }

    private Integer nowOfWeek() {
        return DateUtil.thisDayOfWeek();
    }

    private String nowOfWeekChn() {
        return DateUtil.thisDayOfWeekEnum().toChinese();
    }

}
