package com.tokeninfo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * 格式化时间
 */
public class TimeUtil {

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    public static final SimpleDateFormat DATE_FORMAT_YEAR_MONTH_DAY_DATE = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
    public static final SimpleDateFormat DATE_FORMAT_MONTH = new SimpleDateFormat("MM-dd", Locale.ENGLISH);
    public static final SimpleDateFormat DATE_FORMAT_SECOND = new SimpleDateFormat("mm:ss", Locale.ENGLISH);
    public static final SimpleDateFormat DATE_FORMAT_HOUR_MIN = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    public static final SimpleDateFormat DATE_FORMAT_HOUR_MIN_SCE = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
    public static final SimpleDateFormat DATE_FORMAT_MONTH_HOUR = new SimpleDateFormat("MM-dd HH:mm", Locale.ENGLISH);
    public static final SimpleDateFormat DEFAULT_DATE_WEEK_FORMAT = new SimpleDateFormat("yyyy/MM/dd E a HH:mm", Locale.CHINA);
    public static final SimpleDateFormat ISO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);


    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static long getCurrentTimeSecond() {
        return System.currentTimeMillis() / 1000;
    }

    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        long timestamp = getCurrentTimeMillis();
        return dateFormat.format(new Date(timestamp));
    }

    public static String ISO(String string) {
        String middle = string.substring(0, 19);
        return middle.replace("T", " ");
    }
}
