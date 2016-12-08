package com.ge.scanner.radius;

import com.ge.scanner.radius.impl.CoaFactory;
import com.ge.scanner.vo.Account;
import com.ge.scanner.vo.Bras;
import com.ge.scanner.vo.CoaInfo;
import com.ge.scanner.vo.Session;
import org.tinyradius.packet.RadiusPacket;

/**
 * Created by Storm_Falcon on 2016/12/7.
 *
 */
public class CoaRequestTest {
	public static void main(String[] args) {
		System.setProperty("user.dir", "E:\\Code\\IDEAworkspace\\test\\AdslScanner");

		Account account = new Account();
		account.login = "test";
		Session session = new Session();
		session.userIp = "127.0.0.1";
		session.account = account;
		Bras bras = new Bras();
		bras.vendorId = 2352;
		bras.ip = "202.96.74.21";
		bras.brasCodes = "secret=ln2008ok;context=163";
		CoaInfo info = new CoaInfo(session, bras);

		CoaFactory factory = CoaFactory.getInstance();
		CoaUtil util = factory.getCoaRequest(2352);
		RadiusPacket response = util.unlock(info);
		System.out.println(response);
	}
}
