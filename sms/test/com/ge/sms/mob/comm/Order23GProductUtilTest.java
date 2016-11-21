package com.ge.sms.mob.comm;

import com.ge.sms.mob.struct.OrderResult23G;
import com.ge.sms.mob.struct.Product23G;
import org.junit.Test;

/**
 * Created by Storm_Falcon on 2016/11/2.
 *
 */
public class Order23GProductUtilTest {
	@Test
	public void order() throws Exception {
		String number = "18642077938";
		Product23G product = new Product23G("DGAQYTQB", Product23G.OP_CLOSE);

		Order23GProductUtil.URL = "http://132.194.35.129:12026/XMLReceiver";
		Order23GProductUtil.URL_TEST = "http://132.194.33.9:12026/XMLReceiver";

		Order23GProductUtil util = new Order23GProductUtil();
		util.setDebug(true);
//		util.setTest(true);
		util.setLogger((content, title) -> System.out.println(title + "=========\n" + content));
		util.setTimeout(10 * 1000);

		OrderResult23G result = (OrderResult23G) util.order(number, product);

		System.out.println("RespCode:" + result.getRespCode());
		System.out.println("EffectTime:" + result.getEffectTime());
		System.out.println("RespDesc:" + result.getRespDesc());
	}

}