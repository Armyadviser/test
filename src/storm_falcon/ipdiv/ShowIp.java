package storm_falcon.ipdiv;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.show.api.ShowApiRequest;

//https://www.showapi.com/api/lookPoint/20/1
public class ShowIp {

	private static DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static String getIpJson(String ip) throws Exception {
		String timestamp = df.format(new Date());
		URL u=new URL("http://route.showapi.com/20-1?"
				+ "showapi_appid=5216"
				+ "&showapi_timestamp=" + timestamp
				+ "&ip=" + ip
				+ "&showapi_sign=65a16cf2e9ba4b90b1574e95b945a6d2");
		InputStream in=u.openStream();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		try {
			byte buf[]=new byte[1024];
			int read;
			while ((read = in.read(buf)) > 0) {
				out.write(buf, 0, read);
			}
		}  finally {
			if (in != null) {
				in.close();
			}
		}
		byte b[]=out.toByteArray( );
		return new String(b,"utf-8");
	}
	
	public static String fun(String ip) {
		try {
			ShowApiRequest request = new ShowApiRequest(
					"http://route.showapi.com/20-1",
					"5216",
					"65a16cf2e9ba4b90b1574e95b945a6d2"
					);
			request.setReadTimeout(150);
			request.setConnectTimeout(150);
			request.addTextPara("ip", ip);
			return request.post();
		} catch (NoClassDefFoundError e) {
			return "";
		}
	}
	
	public static synchronized String find(String ip) {
		String json = fun(ip);
		int st = json.indexOf("city") + 7;
		if (st == 6) {
			System.out.println(ip + "\n\t" + json);
		}
		String remain = json.substring(st);
		
		int ed = remain.indexOf('"');
		String city = remain.substring(0, ed);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return city;
	}
	
	public static void main(String[] args) {
		System.out.println(fun("42.84.144.189"));
	}
}
