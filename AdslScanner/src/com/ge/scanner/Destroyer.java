package com.ge.scanner;

import com.ge.scanner.radius.CoaRequest;
import com.ge.scanner.radius.impl.CoaFactory;
import com.ge.scanner.vo.CoaInfo;
import org.tinyradius.packet.RadiusPacket;

import java.util.List;

/**
 * Created by Storm_Falcon on 2016/11/10.
 * Move a list of users' coa info which searched by scanner to vpn.
 */
public class Destroyer {

	/**
	 * Kick off a list of CoaInfos.
	 * @param list
	 */
	public static void kickOff(List<CoaInfo> list) {
		list.forEach(coaInfo -> {
			CoaFactory factory = CoaFactory.getInstance();
			CoaRequest request = factory.getCoaRequest(coaInfo.bras.vendorId);
			RadiusPacket response = request.moveToVpn(coaInfo);
			System.out.println(response);
		});

		System.out.println(list.size() + " coa info kicked off.");
	}
}
