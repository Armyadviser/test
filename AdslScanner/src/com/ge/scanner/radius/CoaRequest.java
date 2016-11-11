package com.ge.scanner.radius;

import com.ge.scanner.vo.Bras;
import com.ge.scanner.vo.CoaInfo;
import com.ge.scanner.vo.Session;
import org.tinyradius.packet.RadiusPacket;

/**
 * Created by Storm_Falcon on 2016/11/10.
 *
 */
public interface CoaRequest {
	RadiusPacket moveBackToInternet(CoaInfo info);
	RadiusPacket moveToVpn(CoaInfo info);
}
