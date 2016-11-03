package com.ge.sms.mob.struct;

/**
 * Created by Storm_Falcon on 2016/10/24.
 *
 */
public class PackageElement {

	/**
	 * 元素类型，D资费
	 */
	public static final String TYPE_FEE = "D";

	/**
	 * 元素类型，S服务
	 */
	public static final String TYPE_SERVICE = "S";

	/**
	 * 元素类型，A活动
	 */
	public static final String TYPE_ACTIVITY = "A";

	/**
	 * 元素类型，X S服务
	 */
	public static final String TYPE_S_SERVICE = "S";


	/**
	 * 包编号，资费对应的包
	 */
	private String packageId;

	/**
	 * 元素编号，资费编码
	 */
	private String elementId;

	/**
	 * 元素类型
	 * @see this.TYPE_FEE
	 * @see this.TYPE_SERVICE
	 * @see this.TYPE_ACTIVITY
	 * @see this.TYPE_S_SERVICE
	 */
	private String elementType;

	public PackageElement(String packageId, String elementId, String elementType) {
		this.packageId = packageId;
		this.elementId = elementId;
		this.elementType = elementType;
	}

	public String getPackageId() {
		return packageId;
	}

	public String getElementId() {
		return elementId;
	}

	public String getElementType() {
		return elementType;
	}
}
