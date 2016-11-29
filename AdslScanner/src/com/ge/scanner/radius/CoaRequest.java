package com.ge.scanner.radius;

import com.ge.scanner.vo.CoaInfo;
import org.tinyradius.packet.RadiusPacket;

/**
 * Created by Storm_Falcon on 2016/11/10.
 *
 */
@SuppressWarnings("UnusedReturnValue")
public interface CoaRequest {
	RadiusPacket moveBackToInternet(CoaInfo info);
	RadiusPacket moveToVpn(CoaInfo info);
}
