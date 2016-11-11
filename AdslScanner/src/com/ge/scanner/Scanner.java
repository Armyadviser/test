package com.ge.scanner;

import com.ge.scanner.conn.cm.CmUtils;
import com.ge.scanner.conn.crm.CrmModule;
import com.ge.scanner.vo.CoaInfo;
import com.ge.util.WaitSynLinkedList;

import java.time.LocalDateTime;
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

	public List<CoaInfo> getCoaInfos() {
		//search the specific users without login, only poid.
		List<CoaInfo> coaInfos = CmUtils.getAccountList()
			.stream()
			.filter(Objects::nonNull)

			//map account to session info
			.flatMap(CmUtils::getSessionsByAccount)

			//map to CoaInfo
			.map(CmUtils::getCoaInfoBySession)
			.filter(Objects::nonNull)
			.collect(toList());

		System.out.println("Convert to " + coaInfos.size() + " CoaInfos.");

		return coaInfos;
	}

	public void run() {
		while (true) {
			System.out.println(LocalDateTime.now());

			List<CoaInfo> coaInfos = getCoaInfos();

			//update offer sign.
			coaInfos.forEach(CmUtils::updateOfferSign);

			//filter users which are not needed offer, query crm.
			coaInfos = coaInfos.stream()
				.filter(coaInfo -> CrmModule.isNeedOffer(coaInfo.session.account))
				.collect(toList());

			System.out.println("After search crm. " + coaInfos.size() + " CoaInfos left.");

			//kick them off.
			Destroyer.kickOff(coaInfos);

			coaInfos.forEach(mSyncList::addLast);

			System.out.println("-------------------------------------\n");
			try {
				Thread.sleep(10 * 60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
