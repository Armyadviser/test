package com.ge.sms.mob.struct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Storm_Falcon on 2016/10/24.
 *
 */
@SuppressWarnings("CanBeFinal")
public class Product4G {

	/**
	 * 00代表订购
	 */
	public static final String TYPE_SUBSCRIBE = "00";

	/**
	 * 01代表退订
	 */
	public static final String TYPE_UNSUBSCRIBE = "01";

	/**
	 * 生效方式，立即生效
	 */
	public static final String EFFECT_NOW = "1";

	/**
	 * 生效方式，次月生效
	 */
	public static final String EFFECT_NEXT = "2";

	/**
	 * 产品id，
	 */
	private String productId;

	/**
	 * 操作类型
	 * @see this.TYPE_SUBSCRIBE
	 * @see this.TYPE_UNSUBSCRIBE
	 */
	private String optType;

	/**
	 * 生效方式
	 * @see this.EFFECT_NOW
	 * @see this.EFFECT_NEXT
	 */
	private String enableTag;

	/**
	 * 产品下附加包及报元素（资费，服务）
	 */
	private List<PackageElement> packageElement = new ArrayList<>();

	public Product4G(String productId, String optType, String enableTag) {
		this.productId = productId;
		this.optType = optType;
		this.enableTag = enableTag;
	}

	public void addElement
		(PackageElement element) {
		packageElement.add(element);
	}

	public String getProductId() {
		return productId;
	}

	public String getOptType() {
		return optType;
	}

	public String getEnableTag() {
		return enableTag;
	}

	public List<PackageElement> getPackageElement() {
		return packageElement;
	}
}
