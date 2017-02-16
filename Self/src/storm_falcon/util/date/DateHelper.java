package storm_falcon.util.date;

import storm_falcon.util.StringDataErrorException;
import storm_falcon.util.string.StringHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Storm_Falcon on 2015/10/10.
 * Some method do help to convert date.
 */
public class DateHelper {

    private static final Map<String, String> mMonthMap;

    public final static int TYPE_SIMPLE = 1;

    static {
        mMonthMap = new HashMap<>();
        mMonthMap.put("Sep", "09");
        mMonthMap.put("Oct", "10");
    }

    /**
     * @param nTimeMillis
     * @param sFormat
     * @return
     */
    public static String getTimeStrFromLongAndFormat(long nTimeMillis, String sFormat) {
    	Calendar c = Calendar.getInstance();
    	c.setTimeInMillis(nTimeMillis);
    	Date data = c.getTime();
    	DateFormat df = new SimpleDateFormat(sFormat);
    	return df.format(data);
    }

    /**
     * 锟斤拷date.toString()锟斤拷式锟斤拷锟街凤拷锟斤拷锟斤拷锟斤拷指锟斤拷锟斤拷式转锟斤拷
     * @param source date.toString()锟斤拷式
     * @param type 锟斤拷锟斤拷锟斤拷式
     * @return 时锟斤拷锟街凤拷锟斤拷
     * @throws StringDataErrorException
     */
    public static String parseDateToString(String source, int type) throws StringDataErrorException {

        //锟斤拷取锟铰凤拷锟斤拷息
        String sMonth = StringHelper.getContent(source, " ", " ", 1);
        sMonth = mMonthMap.get(sMonth);
        if (sMonth == null) {
            throw new StringDataErrorException("source month error:" + source);
        }

        //锟斤拷取锟斤拷锟斤拷锟斤拷息
        String sDay = StringHelper.getContent(source, " ", " ", 2);
        if (sDay.length() < 1 || sDay.length() > 2) {
            throw new StringDataErrorException("source day error:" + source);
        }

        //锟斤拷取时锟斤拷锟斤拷息
        String sTime = StringHelper.getContent(source, " ", " ", 3);
        if (sTime.length() != 8) {
            throw new StringDataErrorException("source time error,source:" + source + ",time:" + sTime);
        }
        sTime = sTime.replaceAll(":", "");

        //锟斤拷取锟斤拷锟斤拷锟较�
        String sYear = source.substring(source.lastIndexOf(' ') + 1, source.length());
        if (sYear.length() != 4) {
            throw new StringDataErrorException("source year error:" + source);
        }

        return resultFormat(sYear, sMonth, sDay, sTime, type);
    }

    /**
     * 锟斤拷System.currentTimeMillis()锟斤拷式锟侥筹拷锟斤拷锟轿伙拷锟街凤拷锟斤拷转锟斤拷锟斤拷指锟斤拷锟斤拷式
     * @param time
     * @return
     */
    public static String parseCurrentTimeMillis(String time, int type) {
        return parseCurrentTimeMillis(Long.parseLong(time), type);
    }

    public static String parseCurrentTimeMillis(long time, int type) {
        Date date = new Date();
        date.setTime(time);
        return resultFormat(date, type);
    }

    public static String resultFormat(Date date, int type) {
        DateFormat df;
        String res = "";
        switch(type) {
            case TYPE_SIMPLE:
                df = new SimpleDateFormat("yyyyMMddHHmmss");
                res = df.format(date);
                break;
            default:
        }
        return res;
    }

    /**
     * 锟斤拷式锟斤拷时锟斤拷
     * @param year
     * @param month
     * @param day
     * @param time
     * @param type
     * @return
     */
    public static String resultFormat(String year, String month, String day, String time, int type) {
        StringBuilder sb = new StringBuilder();
        switch(type) {
            case TYPE_SIMPLE:
                sb.append(year).append(month).append(day).append(time);
                break;
            default:
        }
        return sb.toString();
    }
}
