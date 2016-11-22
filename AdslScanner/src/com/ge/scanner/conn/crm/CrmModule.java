package com.ge.scanner.conn.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ge.scanner.config.ScannerConfig;
import com.ge.scanner.vo.Account;
import com.ge.util.HttpTools;

/**
 * Created by Storm_Falcon on 2016/11/11.
 * Request the proxy server.
 * use the crm arguments.
 */
public class CrmModule {

	public static boolean isNeedOffer(Account account) {
		try {
			if (account.login.startsWith("ln_boss_gd_test")) {
				return true;
			}

			ScannerConfig config = ScannerConfig.getInstance();
			String url = config.getCrmProxy();

			url = url.replace("<mobileno>", account.mobileNo);
			url = url.replace("<servid>", account.userId);
			url = url.replace("<rpinstid>", account.rpInstId);

			String resp = HttpTools.get(url);
			if (resp == null) {
				return false;
			}

			try {
				int index = resp.indexOf('=');
				String sign = resp.substring(index + 1, index + 2);
				return "0".equals(sign);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
