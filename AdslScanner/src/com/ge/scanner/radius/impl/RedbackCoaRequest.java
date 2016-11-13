package com.ge.scanner.radius.impl;

import com.ge.adsl.radius.coa.CoaRequestUtil;
import com.ge.scanner.radius.CoaRequest;
import com.ge.scanner.vo.CoaInfo;
import org.tinyradius.packet.RadiusPacket;

/**
 * Created by Storm_Falcon on 2016/11/10.
 *
 */
class RedbackCoaRequest implements CoaRequest {

	private CoaRequestUtil util = new CoaRequestUtil();

	RedbackCoaRequest() {
		System.out.println("redback bras");
	}

	@Override
	public RadiusPacket moveBackToInternet(CoaInfo info) {
		util.setBrasIp(info.bras.ip);
		util.setSecret(info.bras.secret);
		util.setUserIp(info.session.userIp);
		util.setShowOut(false);
		return util.moveBackToInternet();
	}

	@Override
	public RadiusPacket moveToVpn(CoaInfo info) {
		util.setBrasIp(info.bras.ip);
		util.setSecret(info.bras.secret);
		util.setUserIp(info.session.userIp);
		util.setShowOut(false);
		return util.moveToVpn();
	}
}
