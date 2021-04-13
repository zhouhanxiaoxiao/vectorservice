package com.cibr.vectorservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    public static Date dateAdd(Date date, int conf,int num){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(conf,num);
        return calendar.getTime();
    }

    public static Date str2date(String dateStr,String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(dateStr);
        return date;
    }

    public static String date2str(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date getFirstDayOfMonth(Date now) throws ParseException {
        now = TimeUtil.str2date(TimeUtil.date2str(now,"yyyy-MM-01"),"yyyy-MM-dd");
        return now;
    }

    public static Date getLastDayOfMonth(Date now) throws ParseException {
        Date firstDayOfMonth = TimeUtil.getFirstDayOfMonth(now);
        Date date = dateAdd(firstDayOfMonth, Calendar.MONTH, 1);
        return dateAdd(date,Calendar.DATE,-1);
    }

}
