package com.ge.sms.mob.comm;

import com.ge.sms.mob.struct.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Storm_Falcon on 2016/11/2.
 *
 */
public class Order4GProductUtilTest {
	@Test
	public void order() throws Exception {

		//package id, element id, element type
		PackageElement element = new PackageElement("51358911", "5906000", PackageElement.TYPE_FEE);

		//product4G id, optType, enableTag
		Product4G product4G = new Product4G("89016252", Product4G.TYPE_SUBSCRIBE, Product4G.EFFECT_NOW);
		product4G.addElement(element);

		Message message = new Message(
			"ln-lilh5",		//操作员工号
			"91",			//省份
			"910",			//地市
			"910472",		//区县
			"91a0080",		//工号对应的渠道编码
			"1010200");		//工号对应的渠道类型
		message.setNumber("13124207491");
		message.addProduct(product4G);

		Order4GProductUtil.URL = "http://132.194.41.1:8082/infserver/httpServer";
		Order4GProductUtil.URL_TEST = "http://132.194.41.2:8083/lnopserver/httpServer";

		Order4GProductUtil util = new Order4GProductUtil();
		util.setDebug(true);
		util.setTest(true);
		util.setLogger((content, title) -> System.out.println(title + "=========\n" + content));
		util.setTimeout(10 * 1000);

		OrderResult4G result = (OrderResult4G) util.order(message);

		System.out.println("code:" + result.getXmlCode());
		System.out.println("detail:" + result.getXmlDetail());
		System.out.println("id:" + result.getProvOrderId());
	}

}