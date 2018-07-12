package com.dawoo.coretool.util.date;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTool {

    public static final String FMT_DATE = "yyyy-MM-dd";
    public static final String FMT_DATE_YM = "yyyy-MM";
    public static final String FMT_DATE_ZN = "yyyy年MM月";
    public static final String FMT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FMT_DATE_2 = "yyyyMMdd";
    public static final String FMT_DATE_TIME_2 = "yyyyMMddHHmmss";
    public static final String FMT_DATE_TIME_3 = "MMddHHmmss";
    public static final String FMT_TIME = "HHmmss";
    public static final String FMT_TIME_2 = "mm:ss";
    public static final String FMT_DATE_MD = "MM-dd";
    public static final String FMT_TIME_3 = "HH:mm:ss";
    public static final String FMT_TIME_4 = "HH:mm";

    /**
     * 获取今天到钱几天的日期
     */
    public static String getDateLimit(int limit) {
        Calendar calendar1 = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf1 = new SimpleDateFormat(FMT_DATE);
        calendar1.add(Calendar.DATE, -limit);
        return sdf1.format(calendar1.getTime());

    }

    public static String getAnyDateLimit(Date date, int limit) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf1 = new SimpleDateFormat(FMT_DATE);
        calendar1.add(Calendar.DATE, -limit);
        return sdf1.format(calendar1.getTime());
    }

    /**
     * 获取当天时间
     */
    public static String getNowDay() {
        Calendar calendar1 = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        return sdf1.format(calendar1.getTime());
    }

    /**
     * 把日期字符串格式化成日期类型
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date convert2Date(String dateStr, String format) {
        SimpleDateFormat simple = new SimpleDateFormat(format);
        try {
            simple.setLenient(false);
            return simple.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 把日期类型格式化成字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String convert2String(Date date, String format) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            return formater.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String convert2String(long pMillisUntilFinished) {
        long hourTime = 3600 * 1000;
        long minTime = 60 * 1000;
        StringBuffer buffer = new StringBuffer();
        long hour = pMillisUntilFinished / hourTime;
        long min = (pMillisUntilFinished % hourTime) / minTime;
        long sec = ((pMillisUntilFinished % hourTime) % minTime) / 1000;
        if (hour < 10) {
            buffer.append("0" + hour + ":");
        } else {
            buffer.append(hour + ":");
        }

        if (min < 10) {
            buffer.append("0" + min + ":");
        } else {
            buffer.append(min + ":");
        }
        if (sec < 10) {
            buffer.append("0" + sec);
        } else {
            buffer.append(sec);
        }
        return buffer.toString();
    }

    /**
     * 毫秒带有时区的转换
     *
     * @param before
     * @param timeZone
     * @return
     */
    public static long convertTimeInMillisWithTimeZone(long before, String timeZone) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(before);
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        return calendar.getTimeInMillis();
    }

    /**
     * 时间字符带有时区转换
     *
     * @param dateStr
     * @param format
     * @param timeZone
     * @return
     */
    public static String convertStringWithTimeZone(String dateStr, String format, String timeZone) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            Date date = formater.parse(dateStr);
            formater.setTimeZone(TimeZone.getTimeZone(timeZone));
            return formater.format(date);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * <pre>
     * 根据指定的日期字符串获取星期几
     * </pre>
     *
     * @param strDate 指定的日期字符串(yyyy-MM-dd 或 yyyy/MM/dd)
     * @return week
     * 星期几(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY)
     */
    public static int getWeekByDateStr(String strDate) {
        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(5, 7));
        int day = Integer.parseInt(strDate.substring(8, 10));

        Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);

        String week = "";
        int weekIndex = c.get(Calendar.DAY_OF_WEEK);

//        switch (weekIndex) {
//            case 1:
//                week = "SUNDAY";
//                break;
//            case 2:
//                week = "MONDAY";
//                break;
//            case 3:
//                week = "TUESDAY";
//                break;
//            case 4:
//                week = "WEDNESDAY";
//                break;
//            case 5:
//                week = "THURSDAY";
//                break;
//            case 6:
//                week = "FRIDAY";
//                break;
//            case 7:
//                week = "SATURDAY";
//                break;
//        }
        return weekIndex;
    }

    /**
     * 转sql的time格式
     *
     * @param date
     * @return
     */
    public static java.sql.Timestamp convertSqlTime(Date date) {
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        return timestamp;
    }

    /**
     * 转sql的日期格式
     *
     * @param date
     * @return
     */
    public static java.sql.Date convertSqlDate(Date date) {
        java.sql.Date Datetamp = new java.sql.Date(date.getTime());
        return Datetamp;
    }


    /**
     * 获取当前日期
     *
     * @param format
     * @return
     */
    public static String getCurrentDate(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 將时间戳转日期
     *
     * @param format
     * @return
     */
    public static String getTimeFromLong(String format, long time) {
        return new SimpleDateFormat(format).format(time);
    }

    /**
     * 將日期转时间戳
     *
     * @param format
     * @return
     */
    public static long getLongFromTime(String format, String date) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        try {
            return sf.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Long.parseLong(null);
    }


    /**
     * 获取时间戳
     *
     * @return
     */
    public static long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取月份的天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取日期的年
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }


    /**
     * 获取日期的月
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }


    /**
     * 获取日期的日
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }


    /**
     * 获取日期的时
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR);
    }

    /**
     * 获取日期的分种
     *
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取日期的秒
     *
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取星期几
     *
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek - 1;
    }

    /**
     * 获取哪一年共有多少周
     *
     * @param year
     * @return
     */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        return getWeekNumOfYear(c.getTime());
    }

    /**
     * 取得某天是一年中的多少周
     *
     * @param date
     * @return
     */
    public static int getWeekNumOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 取得某天所在周的第一天
     *
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        return c.getTime();
    }

    /**
     * 取得某天所在周的最后一天
     *
     * @param date
     * @return
     */
    @SuppressLint("WrongConstant")
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
        return c.getTime();
    }

    /**
     * 取得某年某周的第一天 对于交叉:2008-12-29到2009-01-04属于2008年的最后一周,2009-01-05为2009年第一周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar calFirst = Calendar.getInstance();
        calFirst.set(year, 0, 7);
        Date firstDate = getFirstDayOfWeek(calFirst.getTime());

        Calendar firstDateCal = Calendar.getInstance();
        firstDateCal.setTime(firstDate);

        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, firstDateCal.get(Calendar.DATE));

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);
        firstDate = getFirstDayOfWeek(cal.getTime());

        return firstDate;
    }

    /**
     * 取得某年某周的最后一天 对于交叉:2008-12-29到2009-01-04属于2008年的最后一周, 2009-01-04为
     * 2008年最后一周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar calLast = Calendar.getInstance();
        calLast.set(year, 0, 7);
        Date firstDate = getLastDayOfWeek(calLast.getTime());

        Calendar firstDateCal = Calendar.getInstance();
        firstDateCal.setTime(firstDate);

        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, firstDateCal.get(Calendar.DATE));

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);
        Date lastDate = getLastDayOfWeek(cal.getTime());

        return lastDate;
    }

    private static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(calendarField, amount);
            return c.getTime();
        }
    }

    /*
     * 1则代表的是对年份操作， 2是对月份操作， 3是对星期操作， 5是对日期操作， 11是对小时操作， 12是对分钟操作， 13是对秒操作，
     * 14是对毫秒操作
     */

    /**
     * 增加年
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addYears(Date date, int amount) {
        return add(date, 1, amount);
    }

    /**
     * 增加月
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMonths(Date date, int amount) {
        return add(date, 2, amount);
    }

    /**
     * 增加周
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addWeeks(Date date, int amount) {
        return add(date, 3, amount);
    }

    /**
     * 增加天
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addDays(Date date, int amount) {
        return add(date, 5, amount);
    }

    /**
     * 增加时
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addHours(Date date, int amount) {
        return add(date, 11, amount);
    }

    /**
     * 增加分
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMinutes(Date date, int amount) {
        return add(date, 12, amount);
    }

    /**
     * 增加秒
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addSeconds(Date date, int amount) {
        return add(date, 13, amount);
    }

    /**
     * 增加毫秒
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMilliseconds(Date date, int amount) {
        return add(date, 14, amount);
    }


    /**
     * time差
     *
     * @param before
     * @param after
     * @return
     */
    public static long diffTimes(Date before, Date after) {
        return after.getTime() - before.getTime();
    }

    /**
     * 秒差
     *
     * @param before
     * @param after
     * @return
     */
    public static long diffSecond(Date before, Date after) {
        return (after.getTime() - before.getTime()) / 1000;
    }

    /**
     * 分种差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffMinute(Date before, Date after) {
        return (int) (after.getTime() - before.getTime()) / 1000 / 60;
    }

    /**
     * 时差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffHour(Date before, Date after) {
        return (int) (after.getTime() - before.getTime()) / 1000 / 60 / 60;
    }

    /**
     * 天数差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffDay(Date before, Date after) {
        return Integer.parseInt(String.valueOf(((after.getTime() - before.getTime()) / 86400000)));
    }

    /**
     * 月差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffMonth(Date before, Date after) {
        int monthAll = 0;
        int yearsX = diffYear(before, after);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(before);
        c2.setTime(after);
        int monthsX = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        monthAll = yearsX * 12 + monthsX;
        int daysX = c2.get(Calendar.DATE) - c1.get(Calendar.DATE);
        if (daysX > 0) {
            monthAll = monthAll + 1;
        }
        return monthAll;
    }

    /**
     * 年差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffYear(Date before, Date after) {
        return getYear(after) - getYear(before);
    }

    /**
     * 设置23:59:59
     *
     * @param date
     * @return
     */
    public static Date setEndDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 设置00:00:00
     *
     * @param date
     * @return
     */
    public static Date setStartDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        return calendar.getTime();
    }

    public static final String TODAY = "today";
    public static final String YESTERDAY = "yesterday";
    public static final String THISWEEK = "thisWeek";
    public static final String LASTWEEK = "lastWeek";
    public static final String THISMONTH = "thisMonth";
    public static final String LASTMONTH = "lastMonth";
    public static final String DAYS_7 = "7days";
    public static final String DAYS_30 = "30days";

    /**
     * 日期快选
     *
     * @param dateValue
     * @returns {string}
     */
    public static String[] getDateOfFastChoose(String dateValue) {
        Date date = new Date();
        long nowLong = date.getTime();
        String format = FMT_DATE;
        String currentDate = getCurrentDate(format);
        String[] fastDateArray = new String[2];
        String startTime = currentDate;
        String endTime = currentDate;
        if (TODAY.equals(dateValue)) {
            startTime = currentDate;
            endTime = currentDate;

        } else if (YESTERDAY.equals(dateValue)) {
            long yesterday = nowLong - 24 * 60 * 60 * 1000;

            startTime = convert2String(new Date(yesterday), format);
            endTime = startTime;
        } else if (THISWEEK.equals(dateValue)) {
            Calendar calendar = Calendar.getInstance();
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            Date dayOfWeekFirst = getFirstDayOfWeek(date);

            startTime = convert2String(dayOfWeekFirst, format);
            endTime = currentDate;
        } else if (LASTWEEK.equals(dateValue)) {


            startTime = convert2String(DateUtils.getBeginDayOfLastWeek(), format);
            endTime = convert2String(DateUtils.getEndDayOfLastWeek(), format);
        } else if (THISMONTH.equals(dateValue)) {
            startTime = convert2String(DateUtils.getBeginDayOfMonth(), format);

            endTime = currentDate;
        } else if (LASTMONTH.equals(dateValue)) {
            startTime = convert2String(DateUtils.getBeginDayOfLastMonth(), format);
            endTime = convert2String(DateUtils.getEndDayOfLastMonth(), format);
        } else if (DAYS_7.equals(dateValue)) {

            startTime = convert2String(DateUtils.getFrontDay(date, 7), format);
            endTime = currentDate;
        } else if (DAYS_30.equals(dateValue)) {
            startTime = convert2String(DateUtils.getFrontDay(date, 30), format);
            endTime = currentDate;
        }

        fastDateArray[0] = startTime;
        fastDateArray[1] = endTime;
        return fastDateArray;
    }


    /**
     * String转Data
     */
    public static Date stringM2Date(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(FMT_DATE);
        sdf.setLenient(false);
        Date currentDate;
        try {
            currentDate = sdf.parse(date);
            return currentDate;
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {

        }
        return null;

    }

    /**
     * 下一天时间传入String
     */
    public static String getNextDay(String nowDay) {
        long addTime = 1;//以1为基数
        addTime *= 1;    //1天
        addTime *= 24;   //一天24小时
        addTime *= 60;   //1小时60分钟
        addTime *= 60;   //1分钟60秒
        addTime *= 1000; //1秒1000毫秒
        Date date = DateTool.stringM2Date(nowDay);
        assert date != null;
        Date nextData = new Date(date.getTime() + addTime);
        nowDay = DateTool.convert2String(nextData, DateTool.FMT_DATE);
        return nowDay;
    }


}