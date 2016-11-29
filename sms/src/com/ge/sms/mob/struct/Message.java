package com.ge.sms.mob.struct;

import com.alibaba.fastjson.JSON;
import com.ge.util.DateHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Storm_Falcon on 2016/10/24.
 *
 */
@SuppressWarnings("CanBeFinal")
public class Message implements Serializer {

	/**
	 * 流水号
	 */
	private String ordersId;

	/**
	 * 操作员id
	 */
	private String operatorId;

	/**
	 * 省份，91
	 */
	private String province;

	/**
	 * 地市，总部编码，比如910
	 */
	private String city;

	/**
	 * 区县，总部编码，910472
	 */
	private String district;

	/**
	 * 渠道编码，与工号对应
	 */
	private String channelId;

	/**
	 * 渠道类型，与工号对应
	 */
	private String channelType;

	/**
	 * 手机号，业务号
	 */
	private String serialNumber;

	/**
	 * 产品信息
	 */
	private List<Product4G> productInfo = new ArrayList<>();

//	/**
//	 * 发展人标识
//	 */
//	private String recomPersonId;
//
//	/**
//	 * 发展人名称
//	 */
//	private String recomPersonName;

//	/**
//	 * 保留字段
//	 */
//	private Para para;

	public Message(String operatorId, String province,
				   String city, String district, String channelId, String channelType) {
		this.operatorId = operatorId;
		this.province = province;
		this.city = city;
		this.district = district;
		this.channelId = channelId;
		this.channelType = channelType;
		this.ordersId = nextNo();
//		recomPersonId = "";
//		recomPersonName = "";
//		para = new Para();
	}

	public void setNumber(String number) {
		this.serialNumber = number;
	}

	/**
	 * 交易流水号
	 * @return
	 */
	private synchronized String nextNo() {
		String suffix = DateHelper.getTimestamp("HHmmssSSS");
		long now = System.currentTimeMillis();
		long hash = String.valueOf(now).hashCode();
		String prefix = String.valueOf(Math.abs(hash));
		return prefix + suffix;
	}

	public void addProduct(Product4G product4G) {
		productInfo.add(product4G);
	}

	public String getOperatorId() {
		return operatorId;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getDistrict() {
		return district;
	}

	public String getChannelId() {
		return channelId;
	}

	public String getChannelType() {
		return channelType;
	}

	public List<Product4G> getProductInfo() {
		return productInfo;
	}

//	public String getRecomPersonId() {
//		return recomPersonId;
//	}
//
//	public String getRecomPersonName() {
//		return recomPersonName;
//	}
//
//	public Para getPara() {
//		return para;
//	}

	public String getOrdersId() {
		return ordersId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	@Override
	public String serialize() {
		return JSON.toJSONString(this, false);
	}
}
