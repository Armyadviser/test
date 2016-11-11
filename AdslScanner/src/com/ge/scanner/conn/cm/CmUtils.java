package com.ge.scanner.conn.cm;

import com.cp.fields.CpFldVlanId;
import com.ge.scanner.vo.Account;
import com.ge.scanner.vo.Bras;
import com.ge.scanner.vo.CoaInfo;
import com.ge.scanner.vo.Session;
import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.PortalOp;
import com.portal.pcm.SparseArray;
import com.portal.pcm.fields.FldPoid;
import com.portal.pcm.fields.FldResults;
import com.portal.pcm.fields.FldServiceIp;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Storm_Falcon on 2016/11/11.
 *
 */
public class CmUtils {

	/**
	 * Search users which need offer.
	 * @return
	 */
	public static List<Account> getAccountList() {
		List<Account> list = new ArrayList<>();

		FList in = Account.getSearchFList();

		FList out = PBaseModule.runOpcode("Search", PortalOp.SEARCH, in);

		if (out == null || !out.hasField(FldResults.getInst())) {
			return list;
		}

		try {
			SparseArray sparseArray = out.get(FldResults.getInst());
			if (sparseArray == null) {
				return list;
			}
			Enumeration results = sparseArray.getValueEnumerator();
			while (results.hasMoreElements()) {
				FList flist = (FList) results.nextElement();
				Account account = Account.parse(flist);
				if (account != null) {
					list.add(account);
				}
			}

			System.out.println("There are " + list.size() + " users to be destory.");
			return list;
		} catch (EBufException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static Stream<Session> getSessionsByAccount(Account account) {

		FList in = Session.getSearchFList(account.login);
		FList out = PBaseModule.runOpcode("Search", PortalOp.SEARCH, in);

		if (out == null || !out.hasField(FldResults.getInst())) {
			return Stream.empty();
		}

		try {
			SparseArray sparseArray = out.get(FldResults.getInst());
			if (sparseArray == null) {
				return Stream.empty();
			}

			Enumeration results = sparseArray.getValueEnumerator();
			List<Session> list = new ArrayList<>();

			while (results.hasMoreElements()) {
				FList flist = (FList) results.nextElement();
				Session session = Session.parse(flist);
				if (session == null) {
					continue;
				}
				session.account = account;

				list.add(session);
			}

			System.out.println("Find " + list.size() + " sessions of " + account.login);
			list.stream();
		} catch (EBufException e) {
			e.printStackTrace();
		}

		return Stream.empty();
	}

	public static CoaInfo getCoaInfoBySession(Session session) {
		FList in = Bras.getSearchFList(session.brasIp);
		FList out = PBaseModule.runOpcode("Search", PortalOp.SEARCH, in);
		if (out == null) {
			return null;
		}
		Bras bras = Bras.parse(out);
		if (bras == null) {
			return null;
		}

		return new CoaInfo(session, bras);
	}

	public static void updateOfferSign(CoaInfo coaInfo) {
		FList in = new FList();
		in.set(FldPoid.getInst(), coaInfo.session.account.poid);
		FList args = new FList();
		args.set(CpFldVlanId.getInst(), 0);
		in.set(FldServiceIp.getInst(), args);

		PBaseModule.runOpcode("WriteFld", PortalOp.WRITE_FLDS, in);
	}
}
