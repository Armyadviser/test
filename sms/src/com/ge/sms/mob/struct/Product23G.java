package com.ge.sms.mob.struct;

/**
 * Created by Storm_Falcon on 2016/10/27.
 *
 */
public class Product23G {

	/**开通*/
	public final static String OP_OPEN = "0";

	/**取消*/
	public final static String OP_CLOSE = "1";

	/**省份的产品编码*/
	private String packageCode;

	/**
	 * 操作
	 * @see this.OP_OPEN
	 * @see this.OP_CLOSE
	 * */
	private String operCode;

	public Product23G(String packageCode, String operCode) {
		this.packageCode = packageCode;
		this.operCode = operCode;
	}

	public String getPackageCode() {
		return packageCode;
	}

	public String getOperCode() {
		return operCode;
	}
}
