package storm_falcon.ipdiv;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
		IPIPNet.load("/home/falcon/文档/17monipdb.dat");
		
		String inFile = "F:/Tencent/874062225/FileRecv/爱立信bras统计.csv";
		String outFile = "F:/Tencent/874062225/FileRecv/out.txt";

        Files.lines(Paths.get("/home/falcon/test/20170105_access_ip.txt"))
                .map(ip -> new String[] {ip, getCityByIp(ip)})
                .filter(items -> items[1] != null)
                .collect(Collectors.groupingBy(items -> items[1]))
                .entrySet()
                .forEach(entry -> System.out.println(entry.getKey() + "\t" + entry.getValue().size()));

	}
}
