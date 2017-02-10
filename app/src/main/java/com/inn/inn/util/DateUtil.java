package com.inn.inn.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    private final static String TAG = "DateUtil";

    public static boolean isToday(String dateStr) {
        dateStr = dateStr.replace("年", "-").replace("月", "-").replace("日", "-");
        return getTodayStr().equals(dateStr);
    }

    public static String convertToStandardDateFormat(String dateStr) {
        return dateStr.replace("年", "-").replace("月", "-").replace("日", "-");
    }

    public static boolean isTomorrow(String dateStr) {
        return getTomorrowStr().equals(dateStr);
    }

    public static int before(String dateStr1, String dateStr2) {
        try {
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr1);
            Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr2);
            if (dateStr1.equals(dateStr2)) {
                return 0;
            }
            if (date1.before(date2)) {
                return 1;
            } else {
                return -1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("参数格式不对");
        }
    }

    /**
     * 获得今天日期的字符串
     *
     * @return
     */
    public static String getTodayStr() {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        Log.d(TAG, "getTodayStr:" + dateString);
        return dateString;
    }

    /**
     * 获得今天日期的字符串
     *
     * @return
     */
    private static String getTomorrowStr() {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        Log.d(TAG, "getTomorrowStr:" + dateString);
        return dateString;
    }

    public static Date parseDate(String string, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static int getDayOfMonth(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.MONTH);
    }

    public static String getMonthStr(long time) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(time));
        return (instance.get(Calendar.MONTH) + 1) + "月";
    }

    public static int getYear(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.YEAR);
    }

    public static Date getDateOf(int year, int month, int day) {
        Calendar instance = Calendar.getInstance();
        instance.set(year, month, day);
        return instance.getTime();
    }

}
