package com.byf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static String getYearBaseByYear(String age){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -Integer.valueOf(age));
        Date newDate = calendar.getTime();
        DateFormat dataFormat = new SimpleDateFormat("yyyy");
        String newDatesString = dataFormat.format(newDate);
        Integer newDateInteger = Integer.valueOf(newDatesString);
        String yearBaseType = "未知";
        if(newDateInteger >= 1940 && newDateInteger < 1950){
            yearBaseType="40后";
        } else if (newDateInteger >= 1950 && newDateInteger < 1960){
            yearBaseType="50后";
        } else if (newDateInteger >= 1960 && newDateInteger < 1970){
            yearBaseType="60后";
        } else if (newDateInteger >= 1970 && newDateInteger < 1980){
            yearBaseType="70后";
        } else if (newDateInteger >= 1980 && newDateInteger < 1990){
            yearBaseType="80后";
        } else if (newDateInteger >= 1990 && newDateInteger < 2000){
            yearBaseType="90后";
        } else if (newDateInteger >= 2000 && newDateInteger < 2010){
            yearBaseType="00后";
        } else if (newDateInteger >= 2010){
            yearBaseType="10后";
        }
        return yearBaseType;
    }

    public static int getDaysBetweenbyStartAndend(String starttime,String endTime,String dateFormatstring) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(dateFormatstring);
        Date start = dateFormat.parse(starttime);
        Date end = dateFormat.parse(endTime);
        Calendar startcalendar = Calendar.getInstance();
        Calendar endcalendar = Calendar.getInstance();
        startcalendar.setTime(start);
        endcalendar.setTime(end);
        int days = 0;
        while(startcalendar.before(endcalendar)){
            startcalendar.add(Calendar.DAY_OF_YEAR,1);
            days += 1;
        }
        return days;
    }
}
