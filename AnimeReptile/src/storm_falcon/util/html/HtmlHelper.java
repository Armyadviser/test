package storm_falcon.util.html;

import storm_falcon.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HtmlHelper {

	/**
	 * ���ַ������зָ��list����
	 * @param strContent
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public static List<String> splitStringAsList(String strContent, String regex) {
		String[] contents = strContent.split(regex);
		List<String> list = new ArrayList<>();
		Collections.addAll(list, contents);
		return list;
	}
	
	/**
	 * ��ȡ��ҳԴ�룬��String��ʽ����
	 * @param strUrl ��ַ
	 * @param encode ����
	 * @return
	 * @throws IOException
	 */
	public static String getHtmlContent(String strUrl, String encode) {
		BufferedReader br = null;
		try {
			URL url = new URL(strUrl);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(15 * 1000);
			conn.setReadTimeout(15 * 1000);
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encode));
			
			String line;
			StringBuilder contents = new StringBuilder();
			while ((line = br.readLine()) != null) {
				contents.append(line).append("\n");
			}
			return contents.toString();
		} catch (IOException e) {
			Log.err("Connection timeout");
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	/**
	 * ��ȡ��1��ƥ�俪ʼ��Ǻͽ������֮�������
	 * @param source asd<td>abc</td>jkl<td>def</td>
	 * @param sStartTag <td>
	 * @param sEndTag </td>
	 * @return abc
	 */
	public static String getCenterContent(String source, String sStartTag, String sEndTag) {
		int nStart = source.indexOf(sStartTag) + sStartTag.length();
		int nEnd = source.indexOf(sEndTag, nStart);
		
		if (nStart >= nEnd) {
			return "";
		}
		
		try {
			return source.substring(nStart, nEnd);
		} catch (StringIndexOutOfBoundsException e) {
			Log.err("==============\n" + source + " " + sStartTag + " " + sEndTag + "\n==============");
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * ��ȡ��n��ƥ�俪ʼ��Ǻͽ������֮�������
	 * @param source asd<td>abc</td>jkl<td>def</td>
	 * @param n 2
	 * @param sStartTag <td>
	 * @param sEndTag </td>
	 * @return def
	 */
	public static String getCenterContent(int n, String source, String sStartTag, String sEndTag) {
		if (!source.contains(sStartTag) || !source.contains(sEndTag)) {
			return "";
		}
		
		int nStart = 0, nEnd = 0;
		for (int i = 0; i < n; i++) {
			nStart = source.indexOf(sStartTag, 1 + nStart) + sStartTag.length();
			nEnd = source.indexOf(sEndTag, 1 + nStart);
		}
		
		try {
			return source.substring(nStart, nEnd);
		} catch (StringIndexOutOfBoundsException e) {
			Log.err("==============\n" + source + " " + sStartTag + " " + sEndTag + "\n==============");
			e.printStackTrace();
		}
		return "";
	}

	@SuppressWarnings("unused")
	public static String trimRegex(String source, String regex) {
		int len = regex.length();
		return source.substring(len, source.length() - len);
	}
	
	public static void main(String[] args) {}
}
