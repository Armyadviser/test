package com.ge.scanner.radius.impl;

import com.ge.scanner.radius.CoaRequest;
import com.ge.scanner.vo.CoaInfo;
import org.tinyradius.packet.RadiusPacket;

/**
 * Created by Storm_Falcon on 2016/11/10.
 *
 */
class HuaweiCoaRequest implements CoaRequest {
	HuaweiCoaRequest() {
		System.out.println("huawei bras");
	}

	@Override
	public RadiusPacket moveBackToInternet(CoaInfo info) {
		return null;
	}

	@Override
	public RadiusPacket moveToVpn(CoaInfo info) {
		return null;
	}
}
