package com.ge.util.xml;

import com.ge.sms.mob.struct.OrderResult23G;
import org.junit.Test;

/**
 * Created by Storm_Falcon on 2016/11/2.
 *
 */
public class XMLUtilsTest {
	@Test
	public void parse() throws Exception {
		String xmlContent = "<UniBSS>\n" +
			"  <OrigDomain>ECIP</OrigDomain>\n" +
			"  <HomeDomain>UCRM</HomeDomain>\n" +
			"  <BIPCode>BIP2A001</BIPCode>  <BIPVer>0100</BIPVer>\n" +
			"  <ActivityCode>T2000101</ActivityCode>\n" +
			"  <ActionCode>0</ActionCode>\n" +
			"  <ActionRelation>0</ActionRelation>\n" +
			"  <Routing>\n" +
			"    <RouteType>01</RouteType>\n" +
			"    <RouteValue>13130364411</RouteValue>\n" +
			"  </Routing>\n" +
			"  <ProcID>gwtfcsub201611021623162214</ProcID>\n" +
			"  <TransIDO>gwtfcsub201611021623162214</TransIDO>\n" +
			"  <ProcessTime>20161102162316</ProcessTime>\n" +
			"  <SPReserve>\n" +
			"    <TransIDC>gwtfcsub201611021623162214</TransIDC>\n" +
			"    <CutOffDay>20161102</CutOffDay>\n" +
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
			"      <UserNumber>13130364411</UserNumber>\n" +
			"      <PackageCode>99105484</PackageCode>\n" +
			"      <OperCode>1</OperCode>\n" +
			"      <ProcTime>20161102162316711</ProcTime>\n" +
			"    </PackageChangeReq>]]>\n" +
			"  </SvcCont>\n" +
			"</UniBSS>";
		OrderResult23G result = XMLUtils.parse(xmlContent, document -> {
			String svcCont = XMLUtils.getFirstValue(document, "SvcCont");
			System.out.println(svcCont);
			return XMLUtils.parse(svcCont, contDoc -> {
				String effectTime = XMLUtils.getFirstValue(contDoc, "UserNumber");
				String respCode = XMLUtils.getFirstValue(contDoc, "PackageCode");
				String respDesc = XMLUtils.getFirstValue(contDoc, "ProcTime");
				return new OrderResult23G(respCode, effectTime, respDesc);
			});
		});

		if (result != null) {
			System.out.println(result.getRespCode());
			System.out.println(result.getEffectTime());
			System.out.println(result.getRespDesc());
		}
	}

}