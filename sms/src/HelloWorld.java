import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Storm_Falcon on 2016/10/24.
 *
 */
public class HelloWorld {

	public static void main(String[] args) throws Exception {
//		JSON.parseArray("");


		CloseableHttpClient httpClient = HttpClients.createDefault();

//		String url = "http://10.28.80.101:7001/WebRoot/httpServer";
		String url = "http://127.0.0.1:1105/Main";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("charset", "utf-8");

//		String jbody = com.ge.sms.encrypt.CryptUtil.encrypt(jsonBody, token);

//		System.out.println("jbody加密后:" + jbody);

		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");// 格式化时间
		String timestamp = d.format(new Date());// 按以上格式 将当前时间转换成字符串
//		String rr = app_id + timestamp + token;
//		String signature = com.ge.sms.encrypt.CryptUtil.getSHA(rr);

		List<NameValuePair> nvps = new ArrayList<>();
//		nvps.addElement(new BasicNameValuePair("token", token));
//		nvps.addElement(new BasicNameValuePair("app_id", app_id));
		nvps.add(new BasicNameValuePair("timestamp", timestamp));
//		nvps.addElement(new BasicNameValuePair("signature", signature));
		nvps.add(new BasicNameValuePair("interfaceType", "realNameCheck"));//userInfoRegister realNameCheck
//		nvps.addElement(new BasicNameValuePair("tradeID", "realNameCheck" + time));
//		nvps.addElement(new BasicNameValuePair("jsonBody", jbody));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps));

		try (CloseableHttpResponse response = httpClient.execute(httpPost)) {

			System.out.println("status:" + response.getStatusLine());

			HttpEntity entity = response.getEntity();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()))) {
				br.lines()
					.forEach(System.out::println);
			}

			//entity.getContent();
			EntityUtils.consume(entity);
		}

//		responseMsg = postMethod.getResponseBodyAsString().trim();
//		System.out.println("responseMsg:" + responseMsg);
//
//		byte[] results = com.ge.sms.encrypt.CryptUtil.decryptBASE64(responseMsg);
//		String rspMsg = new String(results, "utf-8");
//		System.out.println("最终结果解密后:" + rspMsg);

	}
}
