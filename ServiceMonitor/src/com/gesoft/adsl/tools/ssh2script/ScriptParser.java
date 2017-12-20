package com.gesoft.adsl.tools.ssh2script;

import java.util.ArrayList;

import com.gesoft.adsl.tools.ssh2.CommandInfo;

/**
 * @author Storm_Falcon
 *	脚本解析工具类
 *	提供各种字符串操作
 */
public class ScriptParser {
	
	/**
	 * 检查命令中括号是否匹配
	 * @param strInfo
	 * @return	true：匹配 false：不匹配
	 */
	private static boolean checkBrackets(String strInfo) {
		int left = strInfo.indexOf('(');
		int right = strInfo.indexOf(')');
		
		if (left > right) {
			return false;
		}
		if (left == -1 && right == -1) {
			return true;
		}
		return true;
	}
	
	/**
	 * 解析脚本文件
	 * @param strPath	文件绝对路径
	 * @return	根据各条命令生成list
	 * @throws Exception	命令中有语法错误
	 */
	public static ArrayList<CommandInfo> parse(String strPath) {
		FileReader reader = new FileReader();
		boolean bOpen = reader.open(strPath);
		if (!bOpen) {
			System.err.println("文件打开失败：" + strPath);
			return null;
		}
		
		ArrayList<CommandInfo> commandList = null;
		
		try {
			commandList = new ArrayList<CommandInfo>();
			while (reader.hasNext()) {
				String strCmd = reader.getLine();
				
				//空行
				if (strCmd.length() == 0) {
					continue;
				}
				//注释
				if (strCmd.charAt(0) == '#'){
					continue;
				}
				//跳转其他文件
				if (strCmd.charAt(0) == '~') {
					String strOtherPath = strCmd.substring(1, strCmd.length());
					ArrayList<CommandInfo> otherList = parse(strOtherPath);
					commandList.addAll(otherList);
					continue;
				}
				
				if (!checkBrackets(strCmd)) {
					throw new Exception("命令：" + strCmd + "括号不匹配");
				}
				
				CommandInfo ci = CommandInfo.parse(strCmd);
				if (ci == null) {
					throw new Exception("命令:" + strCmd + "错误");
				}
				
				commandList.add(ci);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		reader.close();
		
		return commandList;
	}
	
	
}
