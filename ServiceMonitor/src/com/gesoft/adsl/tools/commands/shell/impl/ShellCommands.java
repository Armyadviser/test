package com.gesoft.adsl.tools.commands.shell.impl;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gesoft.adsl.tools.commands.shell.Command;
import com.gesoft.adsl.tools.ssh2.CrtException;
import com.gesoft.adsl.tools.ssh2.CrtExcutor;

/**
 * @author Wu
 * @-Methods class, execute shell commands directly.
 */
class ShellCommands extends Command{

	@Override
	public int getArgsSize() {
		return 1;
	}

	@Override
	public String runItem(Map<String, Object> mGlobal,
			List<Object> arrArgs) throws Exception {
		CrtExcutor crt = (CrtExcutor)mGlobal.get("SSH2");
		
		if(null == crt){
			throw new CrtException("(ShellCommands)The connection has not been created yet!!!");
		}
			
		String strCmd = (String) arrArgs.get(0);
		//if there is a string like "${}", replace it with the value of the GlobalMap
		if(strCmd.indexOf("${") != -1){
			String strParameter = strCmd;
			Pattern p = Pattern.compile("\\{.*?\\}");
	        Matcher m = p.matcher(strParameter);
	        
	        while (m.find()) {
	        	strParameter = m.group(0).replaceAll("\\{([^\\]]*)\\}", "$1");
			}
	        
	        if(mGlobal.containsKey(strParameter)){
	        	strCmd = strCmd.replace(strParameter, (String)mGlobal.get(strParameter));
	        	strCmd = strCmd.replace("$", "");
	        	strCmd = strCmd.replace("{", "");
	        	strCmd = strCmd.replace("}", "");
	        	strCmd = strCmd.replace("\"", "");
	        }
		}
		
		
		String strReturn = crt.run(strCmd);
		mGlobal.put("STR_RETURN", strReturn);
		return null;
	}

}
