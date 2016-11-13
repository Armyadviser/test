package com.ge.scanner.vo;

/**
 * Created by Storm_Falcon on 2016/11/10.
 * A vo contains all information of coa request.
 *
 * main:{UserIp, BrasIp, secret, context}
 * support:{AccountObj, login, birthTime}
 */
public class CoaInfo {
	public Session session;
	public Bras bras;
	public long birthTime;

	public CoaInfo(Session session, Bras bras) {
		this.session = session;
		this.bras = bras;
		birthTime = System.currentTimeMillis();
	}

	@Override
	public String toString() {
		return "CoaInfo{" +
			"session=" + session +
			", bras=" + bras +
			", birthTime=" + birthTime +
			'}';
	}
}
