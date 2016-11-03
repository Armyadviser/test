package com.ge.sms.mob.struct;

/**
 * Created by Storm_Falcon on 2016/10/26.
 *
 */
public class OrderResult23G extends OrderResult {
	private String respCode;
	private String effectTime;
	private String respDesc;

	public OrderResult23G(String respCode, String effectTime, String respDesc) {
		this.respCode = respCode;
		this.effectTime = effectTime;
		this.respDesc = respDesc;
	}

	public String getRespCode() {
		return respCode;
	}

	public String getEffectTime() {
		return effectTime;
	}

	public String getRespDesc() {
		return respDesc;
	}
}
