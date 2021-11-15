package com.bat.laoyin.service.common.utils;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/22 16:44
 */
public class CronUtils {
    /**
     * 因为有年 仅保证适用xxljob
     * 
     * @param date
     * @return
     */
    public static String dateToCronStr(Date date) {
        LocalDateTime dateTime = TimeUtils.dateToLocalDateTime(date);
        int year = dateTime.getYear();
        int monthOfYear = dateTime.getMonthValue();
        int dayOfMonth = dateTime.getDayOfMonth();
        int hourOfDay = dateTime.getHour();
        int minuteOfHour = dateTime.getMinute();
        int secondOfMinute = dateTime.getSecond();
        return MessageFormat.format("{0} {1} {2} {3} {4} ? {5}", secondOfMinute, minuteOfHour, hourOfDay, dayOfMonth,
            monthOfYear, String.valueOf(year));
    }

    public static void main(String[] args) {
        System.out.println(dateToCronStr(new Date()));
    }
}
