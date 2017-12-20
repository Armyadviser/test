package com.gesoft.adsl.monitor;

import com.gesoft.adsl.tools.JTools;
import com.gesoft.adsl.tools.log.Log;
import com.gesoft.adsl.tools.ssh2script.IniOperation;


/**
 * @author Storm_Falcon
 *	读取监控服务文件类，与D:\test\config01 service_monitor.txt对应
 */
public final class ServiceMonitorReader {
	
	private String mBasePath = null;
	
	private String mLogPath = null;

	private IniOperation mOperReader = null;
	
	private Log mSysLog = null;
	
	private ServiceMonitorReader(final String iniFilePath) {
		mOperReader = loadIni(iniFilePath);
		new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(5 * 60 * 1000);
					} catch (InterruptedException e) {}
					
					IniOperation iniTemp = loadIni(iniFilePath);
					
					synchronized (mOperReader) {
						mOperReader = iniTemp;
					}
				}
			}
		}.start();
	}
	
	private IniOperation loadIni(String iniFilePath) {
		IniOperation iniTemp = new IniOperation();
		boolean flag = iniTemp.loadIni(iniFilePath);
		if (!flag) {
			System.err.println("读取配置文件失败:" + iniFilePath);
			System.exit(-1);
		}
		
		mLogPath = iniTemp.getKeyValue("Global", "LogPath");
		if (!mLogPath.endsWith("/") && !mLogPath.endsWith("\\")) {
			mLogPath += "/";
		}
		JTools.createFile(mLogPath);
		
		mBasePath = iniTemp.getKeyValue("Global", "BasePath");
		if (!mBasePath.endsWith("/") && !mBasePath.endsWith("\\")) {
			mBasePath += "/";
		}
		
		mSysLog = Log.getSystemLog(mLogPath);
		mSysLog.setShowT(false);
		mSysLog.setShowSystemOut(true);
		
		return iniTemp;
	}
	
	private static ServiceMonitorReader mReader = null;
	
	public static synchronized ServiceMonitorReader getServiceMonitorReader(String iniFilePath) {
		if (mReader == null) {
			mReader = new ServiceMonitorReader(iniFilePath);
		}
		
		return mReader;
	}
	
	public static synchronized ServiceMonitorReader getServiceMonitorReader() {
		return mReader;
	}
	
	/**
	 * 获取服务名
	 * @param strSection
	 * @return
	 */
	public String getName(String strSection) {
		return mOperReader.getKeyValue(strSection, "name");
	}
	
	/**
	 * 该模块是否有效
	 * @param strSection
	 * @return
	 */
	public boolean isEnable(String strSection) {
		String sEnable = mOperReader.getKeyValue(strSection, "enable");
		if ("false".equals(sEnable)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 获取脚本路径
	 * @param strSection
	 * @return
	 */
	public String getScriptPath(String strSection) {
		return mOperReader.getKeyValue(strSection, "path");
	}
	
	/**
	 * 获取服务检查时间
	 * @param strSection
	 * @return
	 */
	public String getCheckTime(String strSection) {
		return mOperReader.getKeyValue(strSection, "time");
	}
	
	/**
	 * 获取重试次数
	 * @param strSection
	 * @return
	 */
	public int getRepeatTime(String strSection) {
		String strRepeat = mOperReader.getKeyValue(strSection, "repeat");
		if (strRepeat == null) {
			return 1;
		}
		return Integer.parseInt(strRepeat);
	}
	
	/**
	 * 获取处理动作
	 * @param strSection
	 * @return SendMsg/SendEmail
	 */
	public String[] getAction(String strSection) {
		return mOperReader.getKeyValue(strSection, "action").split(",");
	}
	
	/**
	 * 获取发动条件
	 * @param strSection
	 * @return SendMsg/SendEmail
	 */
	public String[] getCondition(String strSection) {
		return mOperReader.getKeyValue(strSection, "condition").split(",");
	}
	
	/**
	 * 获取发送手机号
	 * @param strSection
	 * @return 
	 */
	public String[] getPhone(String strSection) {
		return mOperReader.getKeyValue(strSection, "phone").split(",");
	}
	
	/**
	 * 获取短信url
	 * @param strSection
	 * @return 
	 */
	public String getSMSUrl(String strSection) {
		return mOperReader.getKeyValue(strSection, "url");
	}
	
	/**
	 * 获取Email地址
	 * @param strSection
	 * @return 
	 */
	public String[] getEamil(String strSection) {
		return mOperReader.getKeyValue(strSection, "email").split(",");
	}
	
	/**
	 * 获取退出码对应意义
	 * @param iExitCode
	 * @return
	 */
	public String getMessage(int iExitCode) {
		return mOperReader.getKeyValue("Message", iExitCode + "");
	}
	
	public Log getSysLog() {
		return mSysLog;
	}
	
	public String getBasePath() {
		return mBasePath;
	}
}
