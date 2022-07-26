/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.celi.cii.base.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author zte
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM",
            "E MMM dd hh:mm:ss z yyyy", "yyyy/MM/dd HH:mm:ss.SSS"
    };

    public static Date now() {
        return new Date();
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 今天是否本月第一天
     * @return
     */
    public static boolean firstDayOfMonth() {
        String dd = getDay();
        if ("01".equals(dd)) {
            return true;
        }
        return false;
    }

    /**
     * 今天是否本年第一天
     * @return
     */
    public static boolean firstDayOfYear() {
        String mm = getMonth();
        String dd = getDay();
        if ("01".equals(mm) && "01".equals(dd)) {
            return true;
        }
        return false;
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parseDatelocal(Object str, Locale locale) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), locale, parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    public static long getDiffSeconds(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000);
    }

    public static long getDiffMilliSeconds(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return afterTime - beforeTime;
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
//		System.out.println(DateFormatUtils.format(getFirstOfThisMonth(),"yyyy-MM-dd 00:00:00"));
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
        Date date = DateUtils.parseDate("2020/02/09 19:24:49.000000000");
        System.out.println(DateUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 获取本周的周一，如果当天是周一，获取上周周一
     *
     * @return
     */
    public static Date getMondayOfThisWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            c.add(Calendar.DAY_OF_MONTH, -7);
        } else {
            c.set(Calendar.DAY_OF_WEEK, 2);
        }
        return c.getTime();
    }

    /**
     * 获取本周的周日，如果当天是周日，获取上周周日
     *
     * @return
     */
    public static Date getSundayOfLastWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            c.add(Calendar.DAY_OF_MONTH, -7);
        } else {
            c.set(Calendar.DAY_OF_WEEK, 1);
        }
        return c.getTime();
    }

    /**
     * 获取本月1号。如果当天是本月1号。获取上月1号
     *
     * @param date
     * @return
     */
    public static Date getFirstOfThisMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (c.get(Calendar.DAY_OF_MONTH) == 1) {
            c.add(Calendar.MONTH, -1);
        } else {
            c.set(Calendar.DAY_OF_MONTH, 1);
        }
        return c.getTime();
    }

    /**
     * 获取本月1号。如果当天是本月1号
     *
     * @param
     * @return
     */
    public static Date getFirstOfThisMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 获取上月最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastOfLastMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (c.get(Calendar.DAY_OF_MONTH) != 1) {
            c.set(Calendar.DAY_OF_MONTH, 1);
        }
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    public static Date getFirstOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取本月最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastOfThisMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
        return c.getTime();
    }

    /**
     * 统计每周和月的最后一天。星期日和月底最后一天
     *
     * @param date   统计日期
     * @param period 周期
     * @param num    返回多少段
     * @return
     */
    public static Date[] getDateEndPeriod(Date date, int period, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        date = calendar.getTime();
        Date[] datePeriod = new Date[num];
        //period 1:日  2:周  3:月
        if (period == 1) {
            for (int i = num; i > 0; i--) {
                datePeriod[num - i] = addDays(date, 0 - i);
            }
        }
        if (period == 2) {
            //获取本周的周一，如果当天是周一，获取上周的周一
            date = addDays(date, -1);
            datePeriod[num - 1] = date;

            date = getSundayOfLastWeek(date);

            //获取最近num个周的周日
            for (int i = num - 2; i >= 0; i--) {
                datePeriod[num - i - 2] = addWeeks(date, 0 - i);
            }
        }
        if (period == 3) {
            date = addDays(date, -1);
            datePeriod[num - 1] = date;
            //获取最近num个月的最后一天。本月最后一天是昨天。
            date = getLastOfLastMonth(date);
            for (int i = num - 2; i >= 0; i--) {
                //每个月的最后一天
                datePeriod[num - i - 2] = getLastOfThisMonth(addMonths(date, 0 - i));
            }
        }
        return datePeriod;
    }

    /**
     * 统计周和月的第一天。星期一和一号
     *
     * @param date   统计日期
     * @param period 周期
     * @param num    返回多少段
     * @return
     */
    public static Date[] getDateStartPeriod(Date date, int period, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        date = calendar.getTime();
        Date[] datePeriod = new Date[num];
        //period 1:日  2:周  3:月
        if (period == 1) {
            for (int i = num; i > 0; i--) {
                datePeriod[num - i] = addDays(date, 0 - i);
            }
        }
        if (period == 2) {
            //获取本周的周一，如果当天是周一，获取上周的周一
            date = getMondayOfThisWeek(date);

            //获取最近num个周的周一
            for (int i = num - 1; i >= 0; i--) {
                datePeriod[num - i - 1] = addWeeks(date, 0 - i);
            }
        }
        if (period == 3) {
            date = getFirstOfThisMonth(date);
            for (int i = num - 1; i >= 0; i--) {
                datePeriod[num - i - 1] = addMonths(date, 0 - i);
            }
        }
        return datePeriod;
    }

    /**
     * 获取昨天凌晨0点整
     *
     * @return
     */
    public static Date getYesterday() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        date = calendar.getTime();
        date = DateUtils.addDays(date, -1);
        return date;
    }

    public static int getSeconds(String date) {
        int seconds = 0;
        try {
            Date d = DateUtils.parseDate(date, "HH:mm:ss");
            seconds = d.getHours() * 3600 + d.getMinutes() * 60 + d.getSeconds();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return seconds;
    }

    /**
     * 获取当年的第一天
     *
     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取去年最后一天
     *
     * @return
     */
    public static Date getLastYearlast() {
        return new Date(getCurrYearFirst().getTime() - 24 * 60 * 60 * 1000);
    }


    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    /**
     * 将字符串的日期转换成longString
     *
     * @param date 字符串的日期
     * @return
     */
    public static String dateStringToLong(String date) {
        if (StringUtils.isNoneBlank(date)) {
            Date d = null;
            try {
                d = DateUtils.parseDate(date, "yyyy-MM-dd HH:mm");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (d != null) {
                long time = d.getTime();
                return String.valueOf(time);
            }
        }
        return "";
    }

    /**
     * 将字符串的日期转换成longStringYMD
     *
     * @param date 字符串的日期
     * @return
     */
    public static String dateStringToLongYmd(String date) {
        if (StringUtils.isNoneBlank(date)) {
            Date d = null;
            try {
                d = DateUtils.parseDate(date, "yyyy-MM-dd");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (d != null) {
                long time = d.getTime();
                return String.valueOf(time);
            }
        }
        return "";
    }

    /**
     * 将long类型的时间转换成字符串(yyyy-MM-dd HH:mm)
     *
     * @param l
     * @return
     */
    public static String longToDateString(String l) {
        if (StringUtils.isNoneBlank(l)) {
            Long time = Long.valueOf(l);
            if (time > 0) {
                Date date = new Date(time);
                String d = DateUtils.formatDate(date, "yyyy-MM-dd HH:mm");
                return d;
            }
        }
        return "";
    }

    /**
     * 将long类型的时间转换成字符串(yyyy-MM-dd)
     *
     * @param l
     * @return
     */
    public static String longToDateStringYmd(String l) {
        if (StringUtils.isNoneBlank(l)) {
            Long time = Long.valueOf(l);
            if (time > 0) {
                Date date = new Date(time);
                String d = DateUtils.formatDate(date, "yyyy-MM-dd");
                return d;
            }
        }
        return "";
    }

    /**
     * 时间是否是当月
     *
     * @param l
     * @return
     */
    public static boolean isCurrMonth(String l) {
        if (StringUtils.isNoneBlank(l)) {
            Long time = Long.valueOf(l);
            if (time > 0) {
                Date date = new Date(time);
                return isCurrMonth(date);
            }
        }
        return false;
    }

    public static boolean isCurrMonth(Date date) {
        Date now = new Date();
        String format = "yyyyMM";
        if (DateUtils.formatDate(date, format).equals(DateUtils.formatDate(now, format))) {
            return true;
        }
        return false;
    }


    /**
     * @param preCallbackTimeStart :
     * @return : java.util.Date
     * @author sundg
     * @date 2019/5/15 9:27
     * @description 将格里利之时间转换为Date
     */
    public static Date getPreCallbackTime(String preCallbackTimeStart) {
        String TimeStart = preCallbackTimeStart.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date parse = null;
        try {
            String callbackTimeStart = format.parse(TimeStart).toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
            TimeZone tz = TimeZone.getTimeZone("GMT+8");
            sdf.setTimeZone(tz);
            parse = sdf.parse(callbackTimeStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }


    public static String getHumanTimeBySeconds(Long dt) {
        if (dt < 60) {
            return dt + "秒";
        }

        if (dt < 3600) {
            return Math.round(dt / 60) + "分钟";
        }

        if (dt < 24 * 3600) {
            int hour = Math.round(dt / 3600);
            int minute = Math.round((dt - (hour * 3600)) / 60);
            return hour + "小时" + (minute == 0 ? "" : minute + "分钟");
        }

        int compare = 24 * 3600;
        int days = Math.round(dt / compare);
        int hour = Math.round((dt - (days * compare)) / 3600);

        return days + "天" + (hour == 0 ? "" : hour + "小时");
    }

    /**
     * 获取该月的最近十天
     *
     * @return
     */
    public static List<Integer> getDaysThisMonth() {
        List<Integer> dayNumList = new ArrayList<>();
        String day = getDay();
        int dayNum = Integer.valueOf(day);
        if (dayNum >= 10) {
            for (int i = 9; i >= 0; i--) {
                dayNumList.add(dayNum - i);
            }
        } else {
            for (int i = 1; i < 11; i++) {
                dayNumList.add(i);
            }
        }
        return dayNumList;
    }

    /**
     * 格式化日期  由05 => 5日
     *
     * @param str
     * @return
     */
    public static String formatDate(String str) {

        if (str.startsWith("0")) {
            str = str.replace("0", "");
        }
        str += "日";
        return str;
    }

    /**
     * 获取下个月几号
     *
     * @param day
     * @return
     */
    public static Date getDayOfNextMonth(Date in, Integer day) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(in);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            calendar.add(Calendar.MONTH, 1);
            return calendar.getTime();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取上个月几号
     *
     * @param day
     * @return
     */
    public static Date getDayOfLastMonth(Date in, Integer day) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(in);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            calendar.add(Calendar.MONTH, -1);
            return calendar.getTime();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取本月几号
     *
     * @param day
     * @return
     */
    public static Date getDayOfCurrentMonth(Date in, Integer day) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(in);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            calendar.add(Calendar.MONTH, 0);
            return calendar.getTime();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //获取昨天的结束时间
    public static Date getDayEndOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd(now()));
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    public static Date getDayStart(Date date) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Date getDayEnd(Date date) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 0);

            return calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Date getTodayStartDate() {
        return getDayStart(now());
    }

    public static Date getTodayEndDate() {
        return getDayEnd(now());
    }

    public static Date getMonthStartDate() {
        Date date = getFirstOfThisMonth();
        return getDayStart(date);
    }

    public static Date getMonthEndDate() {
        return getDayEnd(getLastOfThisMonth(now()));
    }

    public static Date getLastMonthStartDate() {
        Date date = getFirstOfLastMonth();
        return getDayStart(date);
    }

    public static Date getLastMonthEndDate() {
        Date date = getLastOfLastMonth(now());
        return getDayEnd(date);
    }

    public static Date getYearStartDate() {
        Date now = now();
        return getDayStart(getYearFirst(now.getYear() + 1900));
    }

    public static Date getYearEndDate() {
        Date now = now();
        return getDayEnd(getYearLast(now.getYear() + 1900));
    }

    public static Date getLastYearStartDate() {
        Date now = now();
        return getDayStart(getYearFirst(now.getYear() + 1900 - 1));
    }

    public static Date getLastYearEndDate() {
        Date now = now();
        return getDayEnd(getYearLast(now.getYear() + 1900 - 1));
    }

    /**
     * @return
     */
    public static Date getDaysAgoEndDate(int agoDays) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -agoDays);
        return getDayEnd(now.getTime());
    }

    /**
     * 获取去年本月第一天开始时间
     * @return
     */
    public static Date getLastYearThisMonthStartDate() {
        int year = getNowYear();
        int month = getNowMonth();
        return getDayStart(getStartMonthDate(year-1, month));
    }

    /**
     * 获取当前日期前几天的日期
     * @param days
     * @return
     */
    public static String getBeforeCurrentTime(int days) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, -days);
        Date time = now.getTime();
        return formatDateTime(time);
    }

    /**
     * 获取去年本月最后一天开始时间
     * @return
     */
    public static Date getLastYearThisMonthEndDate() {
        int year = getNowYear();
        int month = getNowMonth();
        return getDayEnd(getEndMonthDate(year-1, month));
    }

    //获取今年是哪一年
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    //获取本月是哪一月
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    //获取某年某月的第一天日期
    public static Date getStartMonthDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getTime();
    }

    //获取某年某月的最后一天日期
    public static Date getEndMonthDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    public static Date getLastYearThisDayEndDate() {
        Date now = now();
        int year = now.getYear() + 1900 - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_MONTH, now.getDate());
        calendar.set(Calendar.MONTH, now.getMonth());
        return getDayEnd(calendar.getTime());
    }

    public static int[] getLastFiveYearsArray() {
        int thisYear = getNowYear();
        int[] result = {thisYear-4, thisYear-3, thisYear-2, thisYear-1, thisYear};
        return result;
    }


    /**
     * 获取最近12个月
     */
    public static String[] getLast12YearMonths() {

        String[] last12Months = new String[12];

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1); //要先+1,才能把本月的算进去
        // 加此行,否则3月重复
        cal.set(Calendar.DATE, 1);

        for (int i = 0; i < 12; i++) {
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1); //逐次往前推1个月
            int year = cal.get(Calendar.YEAR);
            int m = cal.get(Calendar.MONTH) + 1;
            String month = m < 10 ? "0" + m : "" + m;
            last12Months[11 - i] = year + "-" + month;
        }
        return last12Months;
    }

    /**
     * 获取最近12个月(月)
     */
    public static int[] getLast12Months() {

        int[] last12Months = new int[12];

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1); //要先+1,才能把本月的算进去
        // 加此行,否则3月重复
        cal.set(Calendar.DATE, 1);

        for (int i = 0; i < 12; i++) {
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1); //逐次往前推1个月
            int m = cal.get(Calendar.MONTH) + 1;
            last12Months[11 - i] = m;
        }
        return last12Months;
    }

    /**
     * 获取最近五年
     * @return
     */
    public static String[] getLast5Years() {
        String[] last5Years = new String[5];
        int thisYear = getNowYear();
        for (int i = 1; i <= 5; i++ ) {
            last5Years[i-1] = String.valueOf(thisYear - 5 + i);
        }
        return last5Years;
    }

    /**
     * 获取最近15天
     * @return
     */
    public static String[] getLast15Days() {
        String[] last15Days = new String[15];
        String thisDay = DateUtils.getDate();
        for (int i = 0; i < 15; i++) {
            last15Days[i] = getSpecifiedDayAfter(thisDay, i -14);
        }
        return last15Days;
    }

    /**
     * 获取最近dayCount天日期的集合
     * @param dayCount
     * @return
     */
    public static List<String> getDateList(Integer dayCount) {

        List<String> dateList = new ArrayList<>();

        //获取当前日期
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1 - dayCount);
        Date firstDate = calendar.getTime();
        for (int i = 0; i < dayCount; i++) {
            Date curDate = DateUtils.addDays(firstDate, i);
            dateList.add(DateUtils.formatDate(curDate, "yyyy-MM-dd"));
        }
        return dateList;
    }

    /**
     * 获得指定日期的后n天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay, int n) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + n);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    /**
     * 判断时间是否超过限制
     * @param startDate
     * @param minutes
     * @return
     */
    public static boolean checkPastTimeOverLimit(Date startDate, int minutes) {
        long start = startDate.getTime();
        long end = System.currentTimeMillis();
        long rangeTime = minutes*60*1000;
        return end - start > rangeTime;
    }

}
