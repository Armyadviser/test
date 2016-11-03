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
        DateFormat formatter = FORMATTER.get(format);
		if (formatter == null) {
			formatter = new SimpleDateFormat(format);
			FORMATTER.put(format, formatter);
		}
        return formatter;
    }

    public static synchronized String getTimestamp(String format) {
		return getFormatter(format).format(new Date());
    }

}
