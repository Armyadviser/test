package com.ge.scanner.vo;

import com.ge.scanner.conn.cm.PBaseModule;
import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.Poid;
import com.portal.pcm.fields.*;

/**
 * Created by Storm_Falcon on 2016/11/7.
 * session vo
 */
public class Session {
	public Account account;
    public String userIp;
	public String brasIp;
    public String sessionId;
    public String desc;

	@Override
	public String toString() {
		return "Session{" +
			"brasIp='" + brasIp + '\'' +
			'}';
	}
}
