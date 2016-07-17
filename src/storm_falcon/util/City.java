package storm_falcon.util;

import storm_falcon.util.file.FileReader;
import storm_falcon.util.file.FileWriter;
import storm_falcon.util.string.StringHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class City {

//	public static void main(String[] args) {
//		String httpUrl = "http://apis.baidu.com/showapi_open_bus/mobile/find";
//		String httpArg = "num=15640258800";
//		String jsonResult = request(httpUrl, httpArg);
//		//System.out.println(jsonResult);
//		
//		int n1 = jsonResult.indexOf("\"city\":\"");
//		int n2 = jsonResult.indexOf("\",", n1+"\"city\":\"".length() + 1);
//		
//		System.out.println(jsonResult.substring( n1+"\"city\":\"".length(), n2));
//	}
	public static void main(String[] args) {
		String readPath = "I:\\3003wrong.txt";
		String writePath = "I:\\3003（添加地市）ｗろんｇ.csv";
		try {
			getCity(readPath, writePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("完成");
	}
	
	public static void getCity(String readPath, String writePath) throws IOException {
		String httpUrl = "http://apis.baidu.com/showapi_open_bus/mobile/find";
		FileReader fr = new FileReader();
		fr.open(readPath);
		FileWriter fw = new FileWriter();
		fw.open(writePath);
		while(fr.hasNext()) {
			String line = fr.getLine();
			List arr = Arrays.asList(StringHelper.split(line, ","));
			String mobile = (String) arr.get(1);
			mobile = mobile.trim();
			String httpArg = "num=" + mobile;
			String jsonResult = request(httpUrl, httpArg);
			int n1 = jsonResult.indexOf("\"city\":\"");
			int n2 = jsonResult.indexOf("\",", n1+"\"city\":\"".length() + 1);
			String city = jsonResult.substring( n1+"\"city\":\"".length(), n2);
			String writeLine = line + "," + city;
			fw.writeLine(writeLine);

			int num = fr.getLineNumber();
			if (num % 100 == 0) {
				System.out.println(num);
			}
		}
		fr.close();
		fw.close();
	}
	
	/**
	 * @param httpUrl
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String request(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    httpUrl = httpUrl + "?" + httpArg;

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("GET");
	        // 填入apikey到HTTP header
	        connection.setRequestProperty("apikey",  "ae1013cc1ead9ee1732d2edb2a9dbdca");
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
}
