package com.ge.scanner;

import com.ge.scanner.config.ScannerConfig;
import com.ge.scanner.conn.cm.CmUtils;
import com.ge.scanner.conn.crm.CrmModule;
import com.ge.scanner.vo.Account;
import com.ge.scanner.vo.CoaInfo;
import com.ge.util.WaitSynLinkedList;
import com.ge.util.log.Log;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 * Created by Storm_Falcon on 2016/11/10.
 * Scan the specific users to destroy.
 * move them to vpn.
 */
public class Scanner extends Thread {

	private WaitSynLinkedList<CoaInfo> mSyncList;

	public Scanner(WaitSynLinkedList<CoaInfo> mSyncList) {
		this.mSyncList = mSyncList;
	}

	/**
	 * Get coa needed information from user's info.
	 * @param users
	 * @return
	 */
	public List<CoaInfo> account2CoaInfos(List<Account> users) {
		return users.stream()

			//map account to session info
			.flatMap(CmUtils::getSessionsByAccount)

			//map to CoaInfo
			.map(CmUtils::getCoaInfoBySession)
			.filter(Objects::nonNull)
			.collect(toList());
	}

	public void run() {
		String logPath = ScannerConfig.getInstance().getScannerValue("logpath");
		Log logger = Log.getSystemLog(logPath);
		while (true) {
			logger.toLog(new Date().toString());

			//search users.
			List<Account> users = CmUtils.getAccountList();
			logger.toLog("There are " + users.size() + " users to be moved to vpn.");

			//filter users which are not needed offer, query crm.
			users = users.stream()
				.filter(CrmModule::isNeedOffer)
				.collect(toList());
			logger.toLog("After search crm. " + users.size() + " users left.");

			//convert to coa info.
			List<CoaInfo> coaInfos = account2CoaInfos(users);
			logger.toLog("Convert to " + coaInfos.size() + " CoaInfos.");

			//update offer sign.
			int nSucNum = coaInfos.stream()
				.mapToInt(coaInfo -> CmUtils.updateOfferSign(coaInfo.session.account) ? 1 : 0)
				.sum();
			logger.toLog("Update user's vlan_id " + nSucNum + " success.");

			//kick them off.
			Destroyer.kickOff(coaInfos);

			//add to sync pool.
//			coaInfos.forEach(mSyncList::addLast);

			logger.toLog("-------------------------------------\n");
			sleep();
		}
	}

	private void sleep() {
		String sTime = ScannerConfig.getInstance().getScannerValue("sleep");
		int nTime = Integer.valueOf(sTime);
		try {
			Thread.sleep(nTime * 60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
