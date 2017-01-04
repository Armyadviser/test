package storm_falcon.ipdiv;

import storm_falcon.util.file.FileReader;
import storm_falcon.util.file.FileWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
	
	private static final Map<String, String> mLocalMap = new HashMap<>();
	
	private static void initLocalMap() {
		mLocalMap.put("沈阳", "sy");
		mLocalMap.put("大连", "dl");
		mLocalMap.put("鞍山", "as");
		mLocalMap.put("抚顺", "fs");
		mLocalMap.put("本溪", "bx");
		mLocalMap.put("丹东", "dd");
		mLocalMap.put("锦州", "jz");
		mLocalMap.put("营口", "yk");
		mLocalMap.put("阜新", "fx");
		mLocalMap.put("辽阳", "ly");
		mLocalMap.put("盘锦", "pj");
		mLocalMap.put("铁岭", "tl");
		mLocalMap.put("朝阳", "cy");
		mLocalMap.put("葫芦岛", "hld");
		mLocalMap.put("沈阳市", "sy");
		mLocalMap.put("大连市", "dl");
		mLocalMap.put("鞍山市", "as");
		mLocalMap.put("抚顺市", "fs");
		mLocalMap.put("本溪市", "bx");
		mLocalMap.put("丹东市", "dd");
		mLocalMap.put("锦州市", "jz");
		mLocalMap.put("营口市", "yk");
		mLocalMap.put("阜新市", "fx");
		mLocalMap.put("辽阳市", "ly");
		mLocalMap.put("盘锦市", "pj");
		mLocalMap.put("铁岭市", "tl");
		mLocalMap.put("朝阳市", "cy");
		mLocalMap.put("葫芦岛市", "hld");
	}

	public static String getCityByIp(String ip) {
		String[] res = IPIPNet.find(ip);//[国，省，市]
		if (!"".equals(res[2])) {
			return mLocalMap.get(res[2]);
		}

		if (res[0].equals("局域网") || res[1].equals("局域网")) {
			String city = ShowIp.find(ip);
			return mLocalMap.get(city);
		}
		return null;
	}

	public static void main(String path[]) throws Exception { 
		initLocalMap();
		IPIPNet.load("D:/test/ip/17monipdb.dat");
		
		String inFile = "F:/Tencent/874062225/FileRecv/爱立信bras统计.csv";
		String outFile = "F:/Tencent/874062225/FileRecv/out.txt";

		FileWriter outWriter = new FileWriter();
		outWriter.open(outFile);

		FileReader.mapForEach(inFile, (num, line) -> ContentInfo.parse(line, 0, ","))
			.filter(Objects::nonNull)
			.parallel()
			.map(contentInfo -> {
				contentInfo.city = getCityByIp(contentInfo.ip);
				return contentInfo;
			})
			.forEach(contentInfo -> outWriter.writeLine(contentInfo.toString()));
		
		outWriter.close();
	}
}
