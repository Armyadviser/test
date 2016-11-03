package com.ge.sms.mob.struct;

/**
 * Created by Storm_Falcon on 2016/10/24.
 *
 */
public class Para {

	/**
	 * 保留字段id
	 */
	private String id = "";

	/**
	 * 保留字段值
	 */
	private String value = "";

	public Para() {

	}

	public Para(String id, String value) {
		this.id = id;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
}
