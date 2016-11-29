package com.ge.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Storm_Falcon on 2015/10/10.
 * Some method do help to convert date.
 */
public class DateHelper {

    private final static Map<String, DateFormat> FORMATTER = new ConcurrentHashMap<>();

    private static DateFormat getFormatter(String format) {
		return FORMATTER.computeIfAbsent(format, k -> new SimpleDateFormat(format));
    }

    public static synchronized String getTimestamp(String format) {
		return getTimestamp(format, System.currentTimeMillis());
    }

    public static synchronized String getTimestamp(String format, long timestamp) {
        return getFormatter(format).format(new Date(timestamp));
    }

}
