package com.ge.scanner.conn.cm;

import com.cp.fields.CpFldVlanId;
import com.ge.scanner.bean.AccountBean;
import com.ge.scanner.bean.BrasBean;
import com.ge.scanner.bean.SessionBean;
import com.ge.scanner.config.ScannerConfig;
import com.ge.scanner.vo.Account;
import com.ge.scanner.vo.Bras;
import com.ge.scanner.vo.CoaInfo;
import com.ge.scanner.vo.Session;
import com.ge.util.log.Log;
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

	private static final Log logger;

	static {
		String logPath = ScannerConfig.getInstance().getScannerValue("LogPath");
		logger = Log.getSystemLog(logPath);
	}

	/**
	 * Search users which need offer.
	 * @param pageSize
	 * @return
	 */
	public static List<Account> getAccountList(int pageSize) {
		List<Account> list = new ArrayList<>();

		FList in = AccountBean.getSearchFList(pageSize);

		FList out = null;
		try {
			out = PBaseModule.runOpcode(PortalOp.SEARCH, in);
		} catch (EBufException e) {
			e.printStackTrace();
		}

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
				Account account = AccountBean.parse(flist);
				if (account != null) {
					list.add(account);
				}
			}

			return list;
		} catch (EBufException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static Stream<Session> getSessionsByAccount(Account account) {

		FList in = SessionBean.getSearchFList(account.login);
		FList out = null;
		try {
			out = PBaseModule.runOpcode(PortalOp.SEARCH, in);
		} catch (EBufException e) {
			e.printStackTrace();
		}

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
				Session session = SessionBean.parse(flist);
				if (session == null) {
					continue;
				}
				session.account = account;

				list.add(session);
			}

			logger.toLog("Find " + list.size() + " sessions of " + account.login);
			return list.stream();
		} catch (EBufException e) {
			e.printStackTrace();
		}

		return Stream.empty();
	}

	public static CoaInfo getCoaInfoBySession(Session session) {
		FList in = BrasBean.getSearchFList(session.brasIp);
		FList out = null;
		try {
			out = PBaseModule.runOpcode(PortalOp.SEARCH, in);
		} catch (EBufException e) {
			e.printStackTrace();
		}
		if (out == null) {
			return null;
		}
		Bras bras = BrasBean.parse(out);
		if (bras == null) {
			return null;
		}

		return new CoaInfo(session, bras);
	}

	/**
	 * Update user offer status.
	 * @param account
	 * @param status
	 * @return {@code true}: success; {@code false}: fail.
	 */
	public static boolean updateOfferSign(Account account, int status) {
		FList in = new FList();
		in.set(FldPoid.getInst(), account.poid);
		FList args = new FList();
		args.set(CpFldVlanId.getInst(), status);
		in.set(FldServiceIp.getInst(), args);

		try {
			PBaseModule.runOpcode(PortalOp.WRITE_FLDS, in);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
