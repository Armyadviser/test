package com.ge.scanner;

import com.ge.scanner.radius.CoaRequest;
import com.ge.scanner.radius.impl.CoaFactory;
import com.ge.scanner.vo.CoaInfo;
import com.ge.util.WaitSynLinkedList;

/**
 * Created by Storm_Falcon on 2016/11/10.
 * Scan the vpn user every once a while.
 * Move some of them back to the Internet.
 */
public class Healer extends Thread {

	/** 1 minute */
	private static final long TIME_LIMIT = 60 * 1000;

	private CoaFactory factory = CoaFactory.getInstance();

	private WaitSynLinkedList<CoaInfo> mSyncList;

	public Healer(WaitSynLinkedList<CoaInfo> mSyncList) {
		this.mSyncList = mSyncList;
	}

	public void run() {

		while (true) {
			long now = System.currentTimeMillis();

			CoaInfo coaInfo = mSyncList.removeFirst();
			if (now - coaInfo.birthTime > TIME_LIMIT) {
				doCoa(coaInfo);
			} else {
				mSyncList.addLast(coaInfo);
			}
		}
	}

	private void doCoa(CoaInfo coaInfo) {
		CoaRequest request = factory.getCoaRequest(coaInfo.bras.vendorId);
		request.moveBackToInternet(coaInfo);
	}
}
