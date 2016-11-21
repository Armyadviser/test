package com.ge.sms.mob.comm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ge.sms.encrypt.CryptUtil;
import com.ge.sms.mob.OrderException;
import com.ge.sms.mob.struct.Message;
import com.ge.sms.mob.struct.OrderResult;
import com.ge.sms.mob.struct.OrderResult4G;
import com.ge.util.DateHelper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;

/**
 * Created by gewp on 2016/11/2.
 * 4g product order tool.
 */
public class Order4GProductUtil extends UnitUtil {

	@Override
	public String getSerialNo() {
		return "gwtfcsub" + DateHelper.getTimestamp("yyyyMMddHHmmss")
			+ RANDOM.nextInt(9) + RANDOM.nextInt(9)
			+ RANDOM.nextInt(9) + RANDOM.nextInt(9);
	}

	/**
	 * 4G请求体
	 * @param message
	 * @return
	 */
	private Request getRequest4G(Message message) {
		String appId = "sysgw";
		String token = "cbc61d92d55bc54454a43d66f6f658f9";
		String timestamp = DateHelper.getTimestamp("yyyyMMddHHmmss");
		String signature;
		String jsonBody = message.serialize();

		if (debug && logger != null) {
			logger.accept(jsonBody, "request:");
		}

		try {
			String rr = appId + timestamp + token;
			signature = CryptUtil.getSHA(rr);

			jsonBody = CryptUtil.encrypt(jsonBody, token);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OrderException("encrypt error.");
		}

		return Request.Post(getUrl())
			.socketTimeout(timeout)
			.bodyForm(
				Form.form()
					.add("tradeID", getSerialNo())
					.add("interfaceType", "trafficSub")
					.add("jsonBody", jsonBody)
					.add("app_id", appId)
					.add("timestamp", timestamp)
					.add("token", token)
					.add("signature", signature)
					.build(),
				Charset.forName("utf-8")
			);
	}

	@Override
	public ResponseHandler<OrderResult> responseHandler() {
		return response -> {
			int status = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			String content = null;
			if (entity != null) {
				content = EntityUtils.toString(entity, Charset.forName("utf-8"));
				try {
					byte[] data = CryptUtil.decryptBASE64(content);
					content = new String(data, "utf-8");
					content = content.replaceAll("\n", "");
				} catch (Exception e) {
					throw new OrderException("decrypt error.");
				}
			}

			if (debug && logger != null) {
				logger.accept(content, "response:");
			}

			if (status != 200) {
				throw new OrderException("connection error, status=" + status);
			}

			JSONObject json = JSON.parseObject(content);
//			JSONObject desc = JSON.parseObject(json.getString("RESP_DESC"));
//			int descCode = desc.getIntValue("code");
//			String descDetail = desc.getString("detail");
			JSONObject xml = JSON.parseObject(json.getString("RESP_XML"));
			String orderId = xml.getString("provOrderId");
			int xmlCode = xml.getIntValue("code");
			String xmlDetail = xml.getString("detail");

			return orderId == null
				? new OrderResult4G(xmlCode, xmlDetail)
				: new OrderResult4G(orderId);
		};
	}

	public OrderResult order(Message message) {
		try {
			return getRequest4G(message)
				.execute()
				.handleResponse(responseHandler());
		} catch (Exception e) {
			e.printStackTrace();
			throw new OrderException("order failed.");
		}
	}
}
