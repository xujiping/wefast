package com.common.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xujiping
 * @date 2018/6/27 11:44
 */
public class DateTimeUtil {

    public static Date convertToDate(String dateTime) {
        Long str2Time = null;
        Date tempDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 将字符串日期格式化并专成时间
        try {
            str2Time = format.parse(dateTime).getTime() / 1000;
            tempDate.setTime(str2Time * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tempDate;
    }

    /**
     * 日期时间生产源
     *
     * @param format
     * @return
     */
    public static String dateTime(String format) {
        Date date = null;
        SimpleDateFormat sdf = null;
        try {
            date = new Date();
            sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        } finally {
            date = null;
            sdf = null;
        }
    }

    /**
     * 获取年,如：2013
     *
     * @return
     */
    public static String getYear() {
        return dateTime("yyyy");
    }

    /**
     * 获取年月
     *
     * @return
     */
    public static String getYearAndMonth() {
        return dateTime("yyyy-MM");
    }

    /**
     * 获取月,如：12
     *
     * @return
     */
    public static String getMonth() {
        return dateTime("MM");
    }

    /**
     * 获取日,如：22
     *
     * @return
     */
    public static String getDay() {
        return dateTime("dd");
    }

    /**
     * 获取几点,如：12:12
     *
     * @return
     */
    public static String getHourMin() {
        return dateTime("HH:mm");
    }

    /**
     * 获取时间,如：12:12:12
     *
     * @return
     */
    public static String getTime() {
        return dateTime("HH:mm:ss");
    }

    /**
     * 获取日期,如：2013-09-09
     *
     * @return
     */
    public static String getDate() {
        return dateTime("yyyy-MM-dd");
    }

    /**
     * 获取日期时间,如：2013-09-09 12:12:12
     *
     * @return
     */
    public static String getDateTime() {
        return dateTime("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取年月日,如：20130101235959
     *
     * @return
     */
    public static String getCompactDateTime() {
        return dateTime("yyyyMMddHHmmss");
    }

    /**
     * 获取年月日,如：20130101235959
     *
     * @return
     */
    public static String getCompactDate() {
        return dateTime("yyyyMMdd");
    }

    /**
     * 获取日期时间,如：2013-09-09 12:12:12
     *
     * @param date
     * @return
     */
    public static String getDateTime(Date date) {
        SimpleDateFormat sdf = null;
        try {
            if (null != date) {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return sdf.format(date);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        } finally {
            sdf = null;
        }
    }

    /**
     * 获取日期时间,如：2013-09-09
     *
     * @param date
     * @return
     */
    public static String getDate(Date date) {
        SimpleDateFormat sdf = null;
        try {
            if (null != date) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.format(date);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        } finally {
            sdf = null;
        }
    }

    /**
     * 获取偏移日期
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getDateOffset(Date date, Integer days) {
        Long str2Time = null;
        Long str2TimeTemp = null;
        Date tempDate = new Date();
        // 将字符串日期格式化并专成时间
        str2Time = date.getTime() / 1000;
        // 加上指定天数，从当天算起
        str2TimeTemp = str2Time + 60 * 60 * 24 * days;
        // 将秒转成系统时钟频率，1000次大约是一秒，然后获得日期
        tempDate.setTime(str2TimeTemp * 1000);
        return tempDate;
    }

    /**
     * 判断是否是昨天
     *
     * @param date
     * @return
     */
    public static boolean checkYesterday(Date date) {
        Date newDate = getDateOffset(date, 1);
        String newDateStr = getDate(newDate);
        String todayStr = getDate();
        if (newDateStr.equals(todayStr)) {
            return true;
        }
        return false;
    }

    /**
     * 比较两时间大小
     *
     * @param date1 java.sql.Date
     * @param date2 java.sql.Date
     * @return 大于1 小于2 等于0
     */
    public static int compare(java.sql.Date date1, java.sql.Date date2) {
        java.sql.Date d1 = java.sql.Date.valueOf(date1.toString());
        java.sql.Date d2 = java.sql.Date.valueOf(date2.toString());
        if (d1.after(d2)) {
            return 1;
        }
        if (d1.before(d2)) {
            return 2;
        }
        return 0;
    }

    /**
     * java8 字符串转日期
     * @param str yyyy-MM-dd HH:mm:ss
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static Date strToDate(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取今日几点几分几秒得时间对象
     * @param hour 几点
     * @param minute 几分
     * @param second 几秒
     * @return
     */
    public static Date today(int hour, int minute, int second){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        Date zero = calendar.getTime();
        return zero;
    }

    /**
     * 获取多长时间之前
     * @param date
     * @return
     */
    public static String leftTime(Date date) {
        String result;
        long d = date.getTime();
        long c = System.currentTimeMillis();
        long time = Math.round((c - d)/1000);
        int minute = 60;
        int quarter = 15 * minute;
        int hour = 60 * minute;
        int day = 24 * hour;
        long month = 30 * day;
        long year = 365 * day;
        if (time >= year) {
            result = (int)Math.floor(time / year) + "年前";
        } else if (time >= month) {
            result = (int)Math.floor(time / month) + "月前";
        } else if (time >= day) {
            result = (int)Math.floor(time / day) + "天前";
        } else if (time >= hour) {
            result = (int)Math.floor(time / hour) + "小时前";
        } else if (time >= quarter) {
            result = "15分钟前";
        } else if (time >= minute) {
            result = "1分钟前";
        } else {
            result = getDateTime(date);
        }
        return result;
    }

    public static String minuteToStr(long second){
        StringBuffer sb = new StringBuffer();
        long hour = second / 3600;
        if (hour < 10){
            if (hour > 0){
                sb.append("0");
                sb.append(hour + ":");
            }
        }else {
            sb.append(hour + ":");
        }
        long munite = (second - hour * 3600) / 60;
        if (munite < 10){
            sb.append("0");
        }
        sb.append(munite + ":");
        long second2 = second - hour * 3600 - munite * 60;
        if (second2 < 10){
            sb.append("0");
        }
        sb.append(second2);
        return sb.toString();
    }

    public static String toMinuteStr(long second){
        StringBuffer sb = new StringBuffer();
        long munite = second / 60;
        if (munite < 10){
            sb.append("0");
        }
        sb.append(munite + ":");
        long second2 = second - munite * 60;
        if (second2 < 10){
            sb.append("0");
        }
        sb.append(second2);
        return sb.toString();
    }

    /**
     * 获取日期的年月，如：201807
     * @param date
     * @return
     */
    public static String getYearMonth(Date date){
        SimpleDateFormat sdf = null;
        try {
            if (null != date) {
                sdf = new SimpleDateFormat("yyyyMM");
                return sdf.format(date);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        } finally {
            sdf = null;
        }
    }

    public static Date getTodayStart(){
        String date = getDate();
        String todayStr = date + " 00:00:00";
        return strToDate(todayStr);
    }

    /**
     * 今日剩下的毫秒数
     *
     * @return
     */
    public static long todayTimeLeft() {
        Date todayStart = today(0, 0, 0);
        Date dateOffset = getDateOffset(todayStart, 1);
        return dateOffset.getTime() - System.currentTimeMillis();
    }
}
