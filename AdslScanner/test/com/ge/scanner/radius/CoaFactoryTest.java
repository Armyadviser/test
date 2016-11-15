package com.ge.scanner.radius;

import com.ge.scanner.radius.impl.CoaFactory;
import com.ge.scanner.vo.Bras;
import com.ge.scanner.vo.CoaInfo;
import com.ge.scanner.vo.Session;
import org.junit.Test;

/**
 * Created by Storm_Falcon on 2016/11/10.
 *
 */
public class CoaFactoryTest {
	@Test
	public void getCoaRequest() throws Exception {
		Session session = new Session();
		session.userIp = "113.237.246.143";
		session.brasIp = "221.203.141.252";

		Bras bras = new Bras();
		bras.context = "163";
		bras.secret = "ln2003ok";
		bras.ip = "221.203.141.252";

		CoaInfo info = new CoaInfo(session, bras);

		CoaFactory factory = CoaFactory.getInstance();
		CoaRequest request = factory.getCoaRequest("2352");
		request.moveToVpn(info);
	}

}