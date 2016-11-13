package com.ge.scanner.conn.cm;

import com.ge.scanner.vo.CoaInfo;
import com.ge.scanner.vo.Session;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Storm_Falcon on 2016/11/13.
 *
 */
public class CmUtilsTest {
	@Test
	public void getCoaInfoBySession() throws Exception {
		System.setProperty("user.dir", "E:\\Code\\IDEAworkspace\\test\\AdslScanner");

		Session session = new Session();
		session.brasIp = "218.25.0.151";
		CoaInfo info = CmUtils.getCoaInfoBySession(session);
		System.out.println(info);
	}

}