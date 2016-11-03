package com.ge.sms.mob.comm;

import com.ge.sms.mob.struct.OrderResult;
import com.ge.sms.mob.struct.OrderResult4G;
import org.apache.http.client.ResponseHandler;

/**
 * Created by gewp on 2016/11/2.
 * a interface with order functions.
 */
public interface OrderUtil {

	/**
	 * 返回请求地址
	 * @return
	 */
	String getUrl();

	/**
	 * 生成流水号
	 * @return
	 */
	String getSerialNo();

	/**
	 * 处理回复报文的 handler
	 * @return
	 */
	ResponseHandler<OrderResult> responseHandler();
}
