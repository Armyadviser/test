package com.ge.scanner;

import com.ge.scanner.config.ScannerConfig;
import com.ge.scanner.radius.CoaUtil;
import com.ge.scanner.radius.impl.CoaFactory;
import com.ge.scanner.vo.CoaInfo;
import com.ge.util.log.Log;
import org.tinyradius.packet.RadiusPacket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Storm_Falcon on 2016/11/10.
 * Move a list of users' coa info which searched by scanner to vpn.
 */
public class Destroyer {

	private static DateFormat formatter = new SimpleDateFormat("[HH:mm:ss]");

	/**
	 * Kick off a list of CoaInfos.
	 * @param list
	 */
	public static void kickOff(List<CoaInfo> list) {
		String logPath = ScannerConfig.getInstance().getScannerValue("LogPath");
		Log logger = Log.getSystemLog(logPath);

		CoaFactory factory = CoaFactory.getInstance();
		list.forEach(coaInfo -> {
			CoaUtil request = factory.getCoaRequest(coaInfo.bras.vendorId);
			RadiusPacket response = request.lock(coaInfo);
			System.out.println(response);
			System.out.println("-------------------------\n");
			logger.toLog(formatter.format(new Date()) + " Kick off:" + coaInfo.session.account.login +
				"," + coaInfo.bras.city);
		});

		logger.toLog("\n\n" + formatter.format(new Date()) +
			" " + list.size() + " coa info kicked off.\n\n");
	}
}
