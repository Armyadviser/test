package com.gesoft.adsl.tools.log;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.gesoft.adsl.tools.JTools;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class Log {	
	
	protected static int TYPE_AUTOCHANGE_FILENAME = 1;
	
	protected static int TYPE_NOTCHANGE_FILENAME = 0;
	
	public final static int ONE_DAY_MINUTES = 1*60*24;
	
	/**
	 * 存放已经存在的Log句柄
	 * 分为三类模式：
	 * 		0. 日志全路径，key为0-Path
	 * 		1. 日志路径加切换时间，key为1-Path
	 * 		2. 日志目录，按日期切换，key为2-Path
	 */
	private static Map mLogMap = new HashMap();
	
	/**
	 * @param strPath		日志文件路径(文件)
	 * @return
	 */
	public static Log getLog(String strPath) {
		if (strPath == null || strPath.length() == 0) {
			return new NoLog();
		}
		
		String strPathModeTmp = "0-" + strPath;
		Log mLog = (Log) mLogMap.get(strPathModeTmp);
		if (mLog != null) {
			return mLog;
		}
		
		mLog = new LocalLog(strPath, TYPE_NOTCHANGE_FILENAME);
		
		mLog.strPathMode = strPathModeTmp;
		
		mLogMap.put(strPathModeTmp, mLog);
		
		return mLog;
	}
	
	/**
	 * @param strPath		日志路径(文件夹)
	 * @param nTime			切换时间间隔(单位：分钟)
	 * @return
	 */
	public static Log getLog(String strPath, final int nTime) {
		return getLog(strPath, nTime, null);
	}
	
	/**
	 * @param strPath		日志路径(文件夹)
	 * @param nTime			切换时间间隔(单位：分钟)
	 * @param strPostfix	文件生产过程中后缀
	 * @return
	 */
	public static Log getLog(String strPath, final int nTime, String strPostfix) {
		if (strPath == null || strPath.length() == 0) {
			return new NoLog();
		}
		
		String strPathModeTmp = "1-" + strPath;
		Log mLog = (Log) mLogMap.get(strPathModeTmp);
		if (mLog != null) {
			return mLog;
		}
		
		final Log mLogTmp = new LocalLog(strPath, TYPE_AUTOCHANGE_FILENAME, strPostfix);
		
		Thread mThread = new Thread() {
			public void run() {
				long nSleepT = nTime * 60 * 1000;
				
				while (mLogTmp.bRunThread) {
					try {
						Thread.sleep(nSleepT);
					} catch (InterruptedException e) {
					}
					mLogTmp.bChangeFileName = true;
				}
			}
		};
		
		mThread.start();
		
		mLogTmp.strPathMode = strPathModeTmp;
		
		mLogMap.put(strPathModeTmp, mLogTmp);
		
		return mLogTmp;
	}

	/**
	 * @param strPath		日志路径(文件夹)
	 * @return
	 */
	public static Log getSystemLog(String strPath) {
		if (strPath == null || strPath.length() == 0) {
			return new NoLog();
		}
		
		String strPathModeTmp = "2-" + strPath;
		Log mLog = (Log) mLogMap.get(strPathModeTmp);
		if (mLog != null) {
			return mLog;
		}
		
		final Log mLogTmp = new LocalLog(strPath, "yyyy-MM-dd", TYPE_AUTOCHANGE_FILENAME);
		
		Thread mThread = new Thread() {
			public void run() {
				while (mLogTmp.bRunThread) {
					
					String strStartD = JTools.getSysTimeStr("yyyy-MM-dd");
					
					long nStart = System.currentTimeMillis();
					
					GregorianCalendar calendar = new GregorianCalendar(Locale.US);
					calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
					calendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
					calendar.set(GregorianCalendar.MINUTE, 0);
					calendar.set(GregorianCalendar.SECOND, 0);
					calendar.set(GregorianCalendar.MILLISECOND, 0);
					Date dateEnd = calendar.getTime();
					
					long nEnd = dateEnd.getTime();
					
					long nSleepT = nEnd - nStart;
					try {
						Thread.sleep(nSleepT);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					String strStopD = JTools.getSysTimeStr("yyyy-MM-dd");
					while (strStartD.equals(strStopD)) {
						
						try {
							Thread.sleep(1*1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						strStopD = JTools.getSysTimeStr("yyyy-MM-dd");
					}

					mLogTmp.bChangeFileName = true;
				}
			}
		};
		
		mThread.start();
		
		mLogTmp.strPathMode = strPathModeTmp;
		
		mLogMap.put(strPathModeTmp, mLogTmp);
		
		return mLogTmp;
	}

	protected String strFilePath = null;
	
	protected String strFileName = null;
	
	protected String strPathMode = null;
	
	//0,绝对路径（含文件名）；
	//1，路径名，无文件名，每天自动生成文件名
	protected int nType = 0;
	
	protected boolean bChangeFileName = false;
	
	protected boolean bRunThread = true;
	
	protected String strFileNameFormat = "yyyy-MM-dd HH:mm";
	
	public synchronized void toLog(String strContent) {
		if (bShowSystemOut) {
			System.out.println(strContent);
		}
		
		toLogCore(strContent);
	}
	
	protected abstract void toLogCore(String strContent);
	
	public synchronized void close() {
		bRunThread = false;
		mLogMap.remove(strPathMode);
		closeCore();
	}
	
	protected abstract void closeCore();
	
	protected boolean bShowT = false;
	
	protected boolean bShowSystemOut = false;
	
	public void setShowT(boolean bShowT) {
		this.bShowT = bShowT;
	}
	
	public void setShowSystemOut(boolean bShow) {
		this.bShowSystemOut = bShow;
	}
}
