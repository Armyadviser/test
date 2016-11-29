
package storm_falcon;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: Java工具类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class JTools {
	
	public static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (byte byte0 : md) {
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
    public static byte[] ip2Bytes(String ip) {
		if (ip == null) {
			return null;
		}
		try {
			return InetAddress.getByName(ip).getAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
    
    /**
	 * 将字符串按照指定字符拆分成字符串数组
	 * @param source 源字符串
	 * @param split 拆分标记
	 */
    public static String[] split(String source, String split) {
		List<String> mList = new ArrayList<>();
		int nLocal = source.indexOf(split);
		while (nLocal != -1) {
			String strItem = source.substring(0, nLocal);
			mList.add(strItem);
			source = source.substring(nLocal + 1);
			nLocal = source.indexOf(split);
		}
		mList.add(source);

		String[] res = new String[mList.size()];
		mList.toArray(res);
		return res;
	}
    
    public static String bytesToHexString(byte[] data) {
		List<Integer> list = new ArrayList<>(data.length);
		for (byte b : data) {
			list.add((int) b);
		}
		return list.stream()
			.map(Integer::toHexString)
			.map(String::toUpperCase)
			.reduce(new StringBuilder(),
					(sb, string) -> {
						sb.append(string);
						return sb;
					},
					(sb, sb1) -> sb.append(sb1.toString())
			).toString();
    }
    
}
