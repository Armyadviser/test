package com.ge.sms.mob.comm;

import com.ge.sms.mob.OrderException;
import com.ge.sms.mob.struct.OrderResult;
import com.ge.sms.mob.struct.OrderResult23G;
import com.ge.sms.mob.struct.Product23G;
import com.ge.util.DateHelper;
import com.ge.util.xml.XMLUtils;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

/**
 * Created by gewp on 2016/11/2.
 * 2,3g product order tool.
 */
public class Order23GProductUtil extends UnitUtil {

	public Order23GProductUtil() {
		URL = "";
		URL_TEST = "http://132.194.33.9:12026/XMLReceiver";
	}

	@Override
	public String getSerialNo() {
		return "gwtfcsub" + DateHelper.getTimestamp("yyyyMMddHHmmss")
			+ RANDOM.nextInt(9) + RANDOM.nextInt(9)
			+ RANDOM.nextInt(9) + RANDOM.nextInt(9);
	}

	@Override
	public ResponseHandler<OrderResult> responseHandler() {
		return response -> {
			String xmlContent = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));

			if (debug && logger != null) {
				logger.accept(xmlContent);
			}

			return XMLUtils.parse(xmlContent, document -> {
				String svcCont = XMLUtils.getFirstValue(document, "SvcCont");
				return XMLUtils.parse(svcCont, contDoc -> {
					String effectTime = XMLUtils.getFirstValue(document, "EffectTime");
					String respCode = XMLUtils.getFirstValue(document, "RespCode");
					String respDesc = XMLUtils.getFirstValue(document, "RespDesc");
					if (respDesc != null) {
						try {
							respDesc = URLDecoder.decode(respDesc, "utf-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
					return new OrderResult23G(respCode, effectTime, respDesc);
				});
			});
		};
	}

	/**
	 * 预处理请求xml
	 * @param number 客户号码，即手机号码
	 * @param product 2、3G产品信息
	 * @return
	 */
	private String prepareRequestXml(String number, Product23G product) {
		String procId = getSerialNo();
		String transId = procId;
		String xml = "" +
//			"<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
			"<UniBSS>\n" +
			"  <OrigDomain>ECIP</OrigDomain>\n" +
			"  <HomeDomain>UCRM</HomeDomain>\n" +
			"  <BIPCode>BIP2A001</BIPCode>" +
			"  <BIPVer>0100</BIPVer>\n" +
			"  <ActivityCode>T2000101</ActivityCode>\n" +
			"  <ActionCode>0</ActionCode>\n" +
			"  <ActionRelation>0</ActionRelation>\n" +
			"  <Routing>\n" +
			"    <RouteType>01</RouteType>\n" +
			"    <RouteValue>" + number + "</RouteValue>\n" +
			"  </Routing>\n" +
			"  <ProcID>" + procId + "</ProcID>\n" +
			"  <TransIDO>" + transId + "</TransIDO>\n" +
//			"  <TransIDH />\n" +
			"  <ProcessTime>" + DateHelper.getTimestamp("yyyyMMddHHmmss") + "</ProcessTime>\n" +
//			"  <Response>\n" +
//			"    <RspType/>\n" +
//			"    <RspCode/>\n" +
//			"    <RspDesc/>\n" +
//			"  </Response>\n" +
			"  <SPReserve>\n" +
			"    <TransIDC>" + transId + "</TransIDC>\n" +
			"    <CutOffDay>" + DateHelper.getTimestamp("yyyyMMdd") + "</CutOffDay>\n" +
			"    <OSNDUNS>0002</OSNDUNS>\n" +
			"    <HSNDUNS>9100</HSNDUNS>\n" +
			"    <ConvID></ConvID>\n" +
			"  </SPReserve>\n" +
			"  <TestFlag>1</TestFlag>\n" +
			"  <MsgSender>0002</MsgSender>\n" +
			"  <MsgReceiver>0001</MsgReceiver>\n" +
			"  <SvcContVer>0100</SvcContVer>\n" +
			"  <SvcCont><![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"    <PackageChangeReq>\n" +
			"      <UserNumber>" + number + "</UserNumber>\n" +
			"      <PackageCode>" + product.getPackageCode() + "</PackageCode>\n" +
			"      <OperCode>" + product.getOperCode() + "</OperCode>\n" +
			"      <ProcTime>" + DateHelper.getTimestamp("yyyyMMddHHmmssSSS") + "</ProcTime>\n" +
			"    </PackageChangeReq>]]>\n" +
			"  </SvcCont>\n" +
			"</UniBSS>";
		if (debug && logger != null) {
			logger.accept(xml);
		}
		return xml;
	}

	private Request getRequest(String reqXml) {
		return Request.Post(getUrl())
			.socketTimeout(timeout)
			.addHeader("content-type", "application/x-www-form-urlencoded;charset=UTF-8")
			.bodyForm(
				Form.form()
					.add("xmlmsg", reqXml)
					.build(),
				Charset.forName("utf-8")
			);
	}

	public OrderResult order(String number, Product23G product) {
		String requestXml = prepareRequestXml(number, product);

		if (debug && logger != null) {
			logger.accept(requestXml);
		}

		try {
			return getRequest(requestXml)
				.execute()
				.handleResponse(responseHandler());
		} catch (Exception e) {
			e.printStackTrace();
			throw new OrderException("order failed.");
		}
	}
}
