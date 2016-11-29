package com.ge.scanner;

import com.ge.scanner.config.ScannerConfig;
import com.ge.scanner.radius.CoaRequest;
import com.ge.scanner.radius.impl.CoaFactory;
import com.ge.scanner.vo.CoaInfo;
import com.ge.util.log.Log;
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
			request.moveToVpn(coaInfo);
		});

		String logPath = ScannerConfig.getInstance().getScannerValue("logpath");
		Log logger = Log.getSystemLog(logPath);
		logger.toLog(list.size() + " coa info kicked off.");
	}
}
