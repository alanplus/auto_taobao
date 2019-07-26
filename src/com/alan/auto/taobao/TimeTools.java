package com.alan.auto.taobao;

import java.util.Calendar;

public class TimeTools {

    public static final long TIME_ONE_SECOND = 1000;
    public static final long TIME_ONE_MINUTE = TIME_ONE_SECOND * 60;
    public static final long TIME_ONE_HOUR = TIME_ONE_MINUTE * 60;
    public static final long TIME_ONE_DAY = TIME_ONE_HOUR * 24;
    public static final long TIME_ONE_MONTH = TIME_ONE_DAY * 30;
    public static final long TIME_ONE_YEAR = TIME_ONE_DAY * 365;


    public static long getTimeMillion(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTimeInMillis();
    }

}
