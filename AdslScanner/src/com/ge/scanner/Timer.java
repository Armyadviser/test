package com.ge.scanner;

import com.ge.scanner.config.ScannerConfig;
import com.ge.scanner.vo.CoaInfo;
import com.ge.util.WaitSynLinkedList;
import com.ge.util.file.FileWriter;

/**
 * Created by Storm_Falcon on 2016/11/11.
 * Print synchronized pool's size every once a while.
 */
public class Timer extends Thread {

	private WaitSynLinkedList<CoaInfo> mList;

	private FileWriter logger;

	private int mSleep;

	public Timer(WaitSynLinkedList<CoaInfo> mList) {
		this.mList = mList;

		ScannerConfig config = ScannerConfig.getInstance();
		String path = config.getTimeLogValue("path");
		mSleep = Integer.valueOf(config.getTimeLogValue("timegap"));
		mSleep = mSleep * 1000;

		logger = new FileWriter();
		logger.open(path);
	}

	public void run() {
		while (true) {
			logger.writeLine(String.valueOf(mList.size()));

			try {
				Thread.sleep(mSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
