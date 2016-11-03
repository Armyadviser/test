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
		String number = "13130364411";
		Product23G product = new Product23G("99105484", Product23G.OP_CLOSE);

		Order23GProductUtil util = new Order23GProductUtil();
		util.setDebug(true);
		util.setTest(true);
		util.setLogger(System.out::println);
		util.setTimeout(10 * 1000);

		OrderResult23G result = (OrderResult23G) util.order(number, product);

		System.out.println("RespCode:" + result.getRespCode());
		System.out.println("EffectTime:" + result.getEffectTime());
		System.out.println("RespDesc:" + result.getRespDesc());
	}

}