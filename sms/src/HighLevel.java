import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Storm_Falcon on 2016/10/24.
 *
 */
public class HighLevel {
	public static void main(String[] args) throws Exception {
		String url = "http://127.0.0.1:1105/Main";
		Content content = Request.Post(url)
			.bodyForm(
				Form.form()
					.add("timestamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
					.add("key", "value")
					.build()
			).execute()
			.returnContent();

		System.out.println(content.asString());

		Thread.sleep(1000);

		HttpEntity entity = Request.Post(url)
			.bodyForm(
				Form.form()
					.add("timestamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
					.add("key", "value")
					.build()
			).execute()
			.returnResponse()
			.getEntity();

		System.out.println(EntityUtils.toString(entity));
	}
}
