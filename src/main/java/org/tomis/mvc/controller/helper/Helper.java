package org.tomis.mvc.controller.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Project: tomis-mvc
 * @since 05.01.2010
 * @author Dan Persa
 */
public class Helper {

    public final static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    public final static DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");

    public static synchronized Date getNowDate() {
        return getMidnightDate(new Date());
    }

    public static synchronized Date getTomorrowDate() {
        Date nowDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(nowDate);
        c.add(Calendar.DAY_OF_MONTH, 1);
        return getMidnightDate(c.getTime());
    }

    public static synchronized Date getMidnightDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        return c.getTime();
    }
}
