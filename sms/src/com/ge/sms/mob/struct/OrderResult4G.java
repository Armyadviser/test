package com.ge.sms.mob.struct;

/**
 * Created by Storm_Falcon on 2016/10/25.
 *
 */
public class OrderResult4G extends OrderResult {
	private int xmlCode;
	private String xmlDetail;
	private String provOrderId;

	public OrderResult4G(int xmlCode, String xmlDetail) {
		this.xmlCode = xmlCode;
		this.xmlDetail = xmlDetail;
	}

	public OrderResult4G(String provOrderId) {
		this.provOrderId = provOrderId;
	}

	public int getXmlCode() {
		return xmlCode;
	}

	public String getXmlDetail() {
		return xmlDetail;
	}

	public String getProvOrderId() {
		return provOrderId;
	}

}
