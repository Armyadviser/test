/**
 *  Change Log:
 *
 *    $Log: JTools.java,v $
 *    Revision 1.1  2009/08/17 11:05:47  zhuming
 *    v1.0 apply at 20090817
 *
 *    Revision 1.1  2009/07/31 08:21:09  zhuming
 *    *** empty log message ***
 *
 *    Revision 1.1  2008/02/27 08:12:42  gesoft
 *    *** empty log message ***
 *
 *    Revision 1.1  2008/01/11 06:05:04  yuhl
 *    *** empty log message ***
 *
 *    Revision 1.18  2007/10/10 07:46:13  yuhl
 *    *** empty log message ***
 *
 *    Revision 1.17  2007/10/10 07:44:48  yuhl
 *    dfddd
 *
 *    Revision 1.12  2007/10/10 07:41:53  hanyu
 *    *** empty log message ***
 *
 */
package storm_falcon;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	public static String httpGet(String strHttp) {
		StringBuilder getPost = new StringBuilder();
		try {
			URL url1 = new URL(strHttp);
			HttpURLConnection url = (HttpURLConnection) url1.openConnection();
			url.setRequestMethod("GET");
			url.setDoOutput(true);
			url.setDoInput(true);
			url.setAllowUserInteraction(false);
			url.setUseCaches(true);
			DataOutputStream outStream = new DataOutputStream(url
					.getOutputStream());
			outStream.flush();
			outStream.close();
			InputStreamReader in = new InputStreamReader(url.getInputStream());
			int chr = in.read();
			while (chr != -1) {
				getPost.append(String.valueOf((char) chr));
				chr = in.read();
			}
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return getPost.toString();
	}
	
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
	
    public static String getFileSuffix(String filePath) {
        int nPos = filePath.lastIndexOf('.');
        if (nPos == -1)
            return "";

        return filePath.substring(nPos+1).toLowerCase();
    }

    public static boolean convertUTF8FileToGBKFile(String strUTF8File, String strGBKFile) {

    	try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(strUTF8File), "UTF8"));
            Writer out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(strGBKFile), "GBK"));
            
            String str = in.readLine();
            while (str != null) {
            	out.write(str);
            	out.write("\r\n");
            	str = in.readLine();
            }
            in.close();
            out.close();
        } catch (UnsupportedEncodingException e) {
        	return false;
        } catch (IOException e) {
        	return false;
        }
        
        return true;
    }
    
    /**
     * 把数字转换成指定长度的串
     * @param nNum
     * @param nLen
     * @return
     */
    public static String num2FixLengthString(int nNum, int nLen) {
    	String str = String.valueOf(nNum);
    	for (int i=str.length(); i<nLen; i++) {
    		str = '0' + str;
    	}
    	
    	return str;
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
    
	public static void main(String[] args) {
		String url = "https://share.dmhy.org..";
	    httpGet(url);
		MD5(url);

		String filePath = "D:/text.txt";
		getFileSuffix(filePath);

		convertUTF8FileToGBKFile(filePath, "D:/text2.txt");
		num2FixLengthString(3, 4);

		ip2Bytes("127.0.0.1");

		String[] ss = split(url, ".");
		System.out.println(Arrays.toString(ss));

		System.out.println(bytesToHexString(url.getBytes()));
	}

}
