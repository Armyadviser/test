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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Storm_Falcon on 2016/11/11.
 *
 */
public class CmUtils {

	private static final Log logger;

	private static DateFormat formatter = new SimpleDateFormat("[HH:mm:ss]");

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
		ObjectReader objReader = new ObjectReader(in);

		FList out = objReader.stepSearch();
		while (out != null && out.hasField(FldResults.getInst())) {
			try {
				SparseArray sparseArray = out.get(FldResults.getInst());
				if (sparseArray == null) {
					break;
				}
				Enumeration results = sparseArray.getValueEnumerator();
				while (results.hasMoreElements()) {
					FList flist = (FList) results.nextElement();
					Account account = AccountBean.parse(flist);
					if (account != null) {
						list.add(account);
					}
				}
			} catch (EBufException e) {
				e.printStackTrace();
			}
			out = objReader.stepNext();
		}
		objReader.stepEnd();

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
			logger.toLog(formatter.format(new Date()) + " User offline " + account.login);
			return Stream.empty();
		}

		try {
			SparseArray sparseArray = out.get(FldResults.getInst());
			if (sparseArray == null) {
				logger.toLog(formatter.format(new Date()) + " User offline " + account.login);
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

			logger.toLog(formatter.format(new Date()) + " Find " + list.size() + " sessions of " + account.login);
			return list.stream();
		} catch (EBufException e) {
			e.printStackTrace();
		}
		logger.toLog(formatter.format(new Date()) + " User offline " + account.login);
		return Stream.empty();
	}

	public static CoaInfo getCoaInfoBySession(Session session) {
		FList in = BrasBean.getSearchFList(session.brasIp);
		FList out = null;
		try {
			out = PBaseModule.runOpcode(PortalOp.SEARCH, in);
		} catch (EBufException e) {
			logger.toLog(formatter.format(new Date()) + " Bras info error of " + session.account.login);
			e.printStackTrace();
		}
		if (out == null) {
			logger.toLog(formatter.format(new Date()) + " Bras info error of " + session.account.login);
			return null;
		}
		Bras bras = BrasBean.parse(out);
		if (bras == null) {
			logger.toLog(formatter.format(new Date()) + " Bras info error of " + session.account.login);
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
			logger.toLog(formatter.format(new Date()) + " Update " + account.login + " sign to " + status + " success.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.toLog(formatter.format(new Date()) + " Update " + account.login + " sign to " + status + " failed.");
			return false;
		}
	}
}
