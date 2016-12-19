package com.ge.scanner.conn.cm;

import com.ge.scanner.vo.Account;
import com.ge.scanner.vo.CoaInfo;
import com.ge.scanner.vo.Session;

import java.util.List;

/**
 * Created by Storm_Falcon on 2016/12/7.
 *
 */
public class CmUtilTest {
	public static void main(String[] args) {
		System.setProperty("user.dir", "D:\\ideaProjects\\test\\AdslScanner");

	    List<Account> list = CmUtils.getAccountList(100);
	    list.forEach(account -> System.out.println(account.login));
		System.out.println(list.size());

//		Session session = new Session();
//		session.brasIp = "221.203.141.61";
//		CoaInfo coaInfo = CmUtils.getCoaInfoBySession(session);
//		System.out.println(coaInfo);

//		String login = "lyexgxe1216dhy";
//		Account account = CmUtils.getAccountByLogin(login);
//		System.out.println(account);

	}
}
