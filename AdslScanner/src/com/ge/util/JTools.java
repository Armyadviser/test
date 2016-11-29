package com.ge.util;

import java.io.*;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>Title: Java工具类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class JTools {
	
	public static String cvsId = "@(#)$Id: JTools.java,v 1.1 2009/08/17 11:05:47 zhuming Exp $";

	public static String strToMD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String dateToTimeStampStr(Date value, String strTimeFormat) {
		if (value == null) {
			return null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(strTimeFormat);//("yyyyMMddHHmmss.SSS");
			return sdf.format(value);
		}
	} 

    //以字符串的形式得到当前时间信息
    //strFormat:格式说明
    //例如：yyyyMMddHHmmss
    public static String getSysTimeStr(String strFormat) {
        Date now = new Date();
        DateFormat formatter = new SimpleDateFormat(strFormat, Locale.US);
        return formatter.format(now);
    }

    private static DateFormat formatter1 = null;
    public static String getSysTimeStr() {
    	if (formatter1 == null) {
    		 formatter1 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
    	}
    	Date now = new Date();
        return formatter1.format(now);
    }

    private static DateFormat formatter2 = null;
    public static String getSysTimeStamp() {
    	if (formatter2 == null) {
    		 formatter2 = new SimpleDateFormat("yyyyMMddHHmmss.SSS", Locale.US);
    	}
    	Date now = new Date();
        return formatter2.format(now);
    }
    
    public static String getTimeStrFromLongAndFormat(long nTimeMillis, String sFormat) {
    	Calendar c = Calendar.getInstance();
    	c.setTimeInMillis(nTimeMillis);
    	Date data = c.getTime();
    	DateFormat df = new SimpleDateFormat(sFormat);
    	return df.format(data);
    }
    
    public static long getLongFromDate(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	return c.getTimeInMillis();
    }
    
    public static String getZeroTime14() {
    	SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");

    	GregorianCalendar cal = new GregorianCalendar();

		cal.set(GregorianCalendar.HOUR_OF_DAY, 0);
		cal.set(GregorianCalendar.MINUTE, 0);
		cal.set(GregorianCalendar.SECOND, 0);
		
		return dateformat.format(new Date(cal.getTimeInMillis()));
    }
    
    private static final Random sample = new Random();
    public static String getRandomString(int nLen) {
        String strList[] = {
          "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        int nRandomRes = 0;
        String strRes = "";

        for (int i = 0; i < nLen; i++) {
            nRandomRes = sample.nextInt(strList.length);
            strRes = strRes + strList[nRandomRes];
        }

        return strRes;
    }

    public static boolean isFilePath(String strFilePath) {
        File fp = new File(strFilePath);
		return fp.isFile();
	}

    public static long getFileSize(String strFilePath) {
        File fp = new File(strFilePath);
        return fp.length();
    }

    public static String getFileType(String strFilePath) {
        int nPos = strFilePath.lastIndexOf('.');
        if (nPos == -1)
            return "";

        return strFilePath.substring(nPos+1).toLowerCase();
    }

    public static boolean createFile(String strFilePath) {
        int nStartPos = 0;
        int nEndPos1 = strFilePath.indexOf(File.separator, nStartPos);
        int nEndPos2 = strFilePath.indexOf('/', nStartPos);
        int nEndPos = min(nEndPos1, nEndPos2);

        String strTempFilePath = strFilePath.substring(0, nEndPos+1);
        while (createAFile(strTempFilePath)) {
            nStartPos = nEndPos + 1;

            nEndPos1 = strFilePath.indexOf(File.separator, nStartPos);
            nEndPos2 = strFilePath.indexOf('/', nStartPos);
            nEndPos = min(nEndPos1, nEndPos2);

            if (nEndPos == -1)
                return true;

            strTempFilePath = strFilePath.substring(0, nEndPos+1);
        }

        return false;
    }
    
	public static String getPreDay(String strFormat, int nDay) {
    	GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(GregorianCalendar.DAY_OF_MONTH, 0-nDay);

        DateFormat formatter = new SimpleDateFormat(strFormat, Locale.US);
        return formatter.format(cal.getTime());
    }

    private static int min(int n1, int n2) {
        if ((n1==-1) && (n2==-1))
            return -1;
        if (n1==-1)
            return n2;
        if (n2==-1)
            return n1;
        if (n1<n2)
            return n1;
        return n2;
    }

    private static boolean createAFile(String strFilePath) {
        boolean bRes = false;
        try {
			File fp = new File(strFilePath);
			bRes = fp.isDirectory() || fp.mkdir();
		} catch(Exception ignored) {}
        return bRes;
    }

    public static String getFileName(String strFilePath) {
        int nStart = strFilePath.lastIndexOf(File.separator);
        if (nStart == -1)
            return "";

        int nEnd = strFilePath.lastIndexOf('.');
        if (nEnd == -1)
            return strFilePath.substring(nStart+1);

        return strFilePath.substring(nStart+1, nEnd);
    }

    public static String format(String strSrc, Vector vec) {
        if ((strSrc == null) || (strSrc.length() == 0))
            return strSrc;

        if ((vec == null) || (vec.isEmpty()))
            return strSrc;

        String strFlag = "%";
        StringBuffer strRes = new StringBuffer();
        int nItem = 0, nStart = 0,
            nEnd = strSrc.indexOf(strFlag),
            nLen = vec.size(), nFlagLen = strFlag.length()+1;

        while (nEnd != -1) {
            strRes.append(strSrc.substring(nStart, nEnd));

            int nTemp = nEnd+strFlag.length();
            nItem = Integer.parseInt(strSrc.substring(nTemp, nTemp+1));
            if (nLen <= nItem)
                break;

            strRes.append(vec.get(nItem));

            if (nEnd + nFlagLen >= strSrc.length()) {
                nStart = -1;
                break;
            }

            nStart = nEnd + nFlagLen;
            nEnd = strSrc.indexOf(strFlag, nStart);
      }

      if (nStart != -1)
          strRes.append(strSrc.substring(nStart));

      return strRes.toString();
  }

    //  传入数据格式:YYYYMM***
    // add 20061009 20:40
    /**
     * nAfterMonth个月后的日期
     * 如果strDate为月中,到月底算是一个月,
     * 例如:	strDate:20061015221000		nAfterMonth:2
     * 	结果;		20061130221000
     * @param strStartDate			//开始时间
     * @param nValidPeriodType		//订购周期类型(0.秒;1.分;2.小时;3.日;4.月)
     * @param nValidPeriod			//订购周期
     * @param nTimeType				//计费方式(0.按时间正常偏移;1.时间偏移到月底)
     * @return
     */
    public static String getAfterMonth(String strStartDate, 
    		int nValidPeriodType, int nValidPeriod, int nTimeType) {

    	String strYear = strStartDate.substring(0, 4);
    	String strMonth = strStartDate.substring(4, 6);
    	String strDate = strStartDate.substring(6, 8);
    	String strHour = strStartDate.substring(8, 10);
    	String strMinute = strStartDate.substring(10, 12);
    	String strSecond = strStartDate.substring(12, 14);
        
    	GregorianCalendar cal = new GregorianCalendar();
    	cal.set(Integer.parseInt(strYear), 
    			Integer.parseInt(strMonth)-1, 
    			Integer.parseInt(strDate),
    			Integer.parseInt(strHour),
    			Integer.parseInt(strMinute),
    			Integer.parseInt(strSecond));

    	//包月并且时间偏移到月底
    	if ((nValidPeriodType == 4) && (nTimeType == 1)) {
    		cal.add(GregorianCalendar.MONDAY, nValidPeriod);
    		cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
    		cal.set(GregorianCalendar.HOUR_OF_DAY, 0);
    		cal.set(GregorianCalendar.MINUTE, 0);
    		cal.set(GregorianCalendar.SECOND, 0);
    		cal.add(GregorianCalendar.SECOND, -1);
    	}
    	
    	else if (nTimeType == 0) {
    		//秒
    		if (nValidPeriodType == 0) {
    			cal.add(GregorianCalendar.SECOND, nValidPeriod);
    		}
    		//分
    		else if (nValidPeriodType == 1) {
        		cal.add(GregorianCalendar.MINUTE, nValidPeriod);
        	}
    		//小时
    		else if (nValidPeriodType == 2) {
        		cal.add(GregorianCalendar.HOUR_OF_DAY, nValidPeriod);
        	}
    		//日
    		else if (nValidPeriodType == 3) {
        		cal.add(GregorianCalendar.DAY_OF_MONTH, nValidPeriod);
        	}
    		//月
    		else if (nValidPeriodType == 4) {
        		cal.add(GregorianCalendar.MONDAY, nValidPeriod);
        	}
    	}
		
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateformat.format(new Date(cal.getTimeInMillis()));
    }
    
    /**
     * 得到下一个月的月首日期
     * @param strDate
     * @return
     */
    public static String getNextMonthFirstDay(String strDate) {
    	SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");

    	String strYear 	= strDate.substring(0, 4);
    	String strMonth = strDate.substring(4, 6);
    	String strDay 	= strDate.substring(6, 8);
    	
    	GregorianCalendar cal = new GregorianCalendar();
		cal.set(Integer.parseInt(strYear), 
				Integer.parseInt(strMonth)-1,
				Integer.parseInt(strDay));

		cal.add(GregorianCalendar.MONDAY, 1);
		cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
		cal.set(GregorianCalendar.HOUR_OF_DAY, 0);
		cal.set(GregorianCalendar.MINUTE, 0);
		cal.set(GregorianCalendar.SECOND, 0);
		
		return dateformat.format(new Date(cal.getTimeInMillis()));
    }
    /**
	 * 判读是否为闰年
	 * @param nThisYear
	 * @return
	 */
	private static boolean isLeapYear(long nThisYear) {
		return(((nThisYear%4==0) && (nThisYear%100!=0)) || (nThisYear%400==0));
	}

//    public static String strChange(String str) {
//        StringBuffer s = new StringBuffer();
//        try {
//            byte[] bys = str.getBytes();
//            sun.io.ByteToCharConverter cover =
//                sun.io.ByteToCharConverter.getConverter("UTF-8");
//            char[] chs = cover.convertAll(bys);
//            for (int i = 0; i < chs.length; i++)
//                s.append(chs[i]);
//        } catch (Exception e) {}
//        return s.toString();
//    }
	
	public static String strChange(String str) {
		byte[] data = str.getBytes();
		try {
			return new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

    //让毫秒只占两位
    //例如:20061009000000.000
    //转换成:20061009000000.00
    public static String filtTimeFormat(String strTime) {
    	return strTime;
    }
    
    public static String addTimeFormat(String strTime) {
    	int nPos = strTime.indexOf('.');
    	if (nPos == -1) {
    		return strTime;
    	}
    	strTime = strTime + "0";
    	return strTime;
    }
    
    /**
     * 取得下一个月的开始时间
     * 例如:当前日期:2006-10-17
     * 返回值:2006-11-01 00:00:00
     * @return
     */
    public static long getNextMonthStartTimeLong() {
    	GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(GregorianCalendar.MONDAY, 1);
		cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
		cal.set(GregorianCalendar.HOUR_OF_DAY, 0);
		cal.set(GregorianCalendar.MINUTE, 0);
		cal.set(GregorianCalendar.SECOND, 0);
		
		return cal.getTimeInMillis();
    }
    
    public static String fillStringToOrderLen(String strSrc, int nLen) {
    	String strDes = strSrc;
    	
    	int nAddLen = nLen - strDes.length();
    	if (nAddLen <= 0) {
    		return strDes;
    	}
    	
    	for (int i=0; i<nAddLen; i++) {
    		strDes = '0' + strDes;
    	}
    	return strDes;
    }
    
    public static String remove(String str) {
    	byte[] bys = str.getBytes();
    	byte[] byRes = new byte[bys.length];

    	int nNum = 0;
    	for (int i=0; i<bys.length; i++) {
    		if ((bys[i] != 0x0A) && (bys[i] != 0x0D)) {
    			byRes[nNum] = bys[i];
    			nNum++;
    		}
    	}
    	return new String(byRes);
    } 

    public static boolean deleteFile(String strFile) {
		return (new File(strFile)).delete();
    } 

    public static String getPreDay() {
    	GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(GregorianCalendar.DAY_OF_MONTH, -1);

        DateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.US);
        return formatter.format(cal.getTime());
    }
    
    public static String getPreMonth() {
    	GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(GregorianCalendar.MONTH, -1);

        DateFormat formatter = new SimpleDateFormat("yyyyMM", Locale.US);
        return formatter.format(cal.getTime());
    }
    
    /**
     * 判断strBig中是否包含strSmall串
     * @param strBig
     * @param strSmall
     * @return
     */
    public static boolean isInclusion(String strBig, String strSmall) {
    	if ((strBig == null) || (strSmall == null)) {
    		return false;
    	}
    	int nPos = strBig.indexOf(strSmall);
		return nPos != -1;

	}
    
    /**
     * 把数字转换成指定长度的串
     * @param nNum
     * @param nLen
     * @return
     */
    public static String toFixLen(int nNum, int nLen) {
    	String str = String.valueOf(nNum);
    	for (int i=str.length(); i<nLen; i++) {
    		str = '0' + str;
    	}
    	
    	return str;
    }
    
    public static String toFixLen(String strNum, int nLen) {
    	String strTemp = strNum;
    	for (int i=strTemp.length(); i<nLen; i++) {
    		strTemp = '0' + strTemp;
    	}
    	
    	return strTemp;
    }
    
    public static String getBeforeDate(int nBeforeDay) {
		GregorianCalendar calendar = new GregorianCalendar(Locale.US);
		calendar.add(GregorianCalendar.DAY_OF_MONTH, nBeforeDay);
		
		Date now = calendar.getTime();
		DateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.US);
		
		return format.format(now);
	}
    
    public static String getBeforeDate(String strDate14, int nBeforeDay) {
    	String strYear = strDate14.substring(0, 4);
    	String strMonth = strDate14.substring(4, 6);
    	String strDate = strDate14.substring(6, 8);
    	String strHour = strDate14.substring(8, 10);
    	String strMinute = strDate14.substring(10, 12);
    	String strSecond = strDate14.substring(12, 14);
        
    	GregorianCalendar calendar = new GregorianCalendar();
    	calendar.set(Integer.parseInt(strYear), 
    			Integer.parseInt(strMonth)-1, 
    			Integer.parseInt(strDate),
    			Integer.parseInt(strHour),
    			Integer.parseInt(strMinute),
    			Integer.parseInt(strSecond));
    	
		calendar.add(GregorianCalendar.DAY_OF_MONTH, nBeforeDay);
		
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
		
		return format.format(calendar.getTime());
	}
    
    public static String getBeforeDate(String strDate14, int nBeforeDay, String strTimeFormat) {
    	String strYear = strDate14.substring(0, 4);
    	String strMonth = strDate14.substring(4, 6);
    	String strDate = strDate14.substring(6, 8);
    	String strHour = strDate14.substring(8, 10);
    	String strMinute = strDate14.substring(10, 12);
    	String strSecond = strDate14.substring(12, 14);
        
    	GregorianCalendar calendar = new GregorianCalendar();
    	calendar.set(Integer.parseInt(strYear), 
    			Integer.parseInt(strMonth)-1, 
    			Integer.parseInt(strDate),
    			Integer.parseInt(strHour),
    			Integer.parseInt(strMinute),
    			Integer.parseInt(strSecond));
    	
		calendar.add(GregorianCalendar.DAY_OF_MONTH, nBeforeDay);
		
		DateFormat format = new SimpleDateFormat(strTimeFormat, Locale.US);
		
		return format.format(calendar.getTime());
	}
    
    public static long coverDate18ToInfranet(String strDate18) {
    	if ((strDate18 == null) || (strDate18.length() != 18)) {
    		return -1;
    	}
    	
		long nDateInfranet = -1;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
			Date dateInfranet = sdf.parse(strDate18);
			nDateInfranet = dateInfranet.getTime();
			nDateInfranet = nDateInfranet / 1000;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return nDateInfranet;
    }
 
    public static long subBetweenMonth(String strBeginTime, String strEndTime) {
    	if ((strBeginTime == null) || (strBeginTime.length() < 6)) {
    		return 0;
    	}
    	if ((strEndTime == null) || (strEndTime.length() < 6)) {
    		return 0;
    	}
    	
    	String strBeginYear = strBeginTime.substring(0, 4);
    	String strBeginMonth = strBeginTime.substring(4, 6);
    	String strEndYear = strEndTime.substring(0, 4);
    	String strEndMonth = strEndTime.substring(4, 6);
    	
    	long nBeginMonthCount = Long.parseLong(strBeginYear) * 12 + Long.parseLong(strBeginMonth);
    	long nEndMonthCount = Long.parseLong(strEndYear) * 12 + Long.parseLong(strEndMonth);
    	
    	return (nEndMonthCount - nBeginMonthCount);
    }
    
    public static String ipToRaw(String strIp) {
		if (strIp == null) {
			return null;
		}

		StringTokenizer st = new StringTokenizer(strIp, ".");
		
		StringBuilder strBuf = new StringBuilder();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			int iToken = Integer.parseInt(token);
			token = Integer.toHexString(iToken);
			if (token.length() == 1) {
				strBuf.append('0');
				strBuf.append(token);
			} else {
				strBuf.append(token);
			}
		}
		
		return strBuf.toString().toUpperCase();
	}
}
