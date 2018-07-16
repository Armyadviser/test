package com.gesoft.adsl.tools.commands.shell.impl;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gesoft.adsl.tools.commands.shell.Command;
import com.gesoft.adsl.tools.ssh2.CrtException;
import com.gesoft.adsl.tools.ssh2.ShellExecutor;

/**
 * @author Wu
 * #Method class, tail-cmd.
 */
class Tail extends Command{

	@Override
	public int getArgsSize() {
		return 3;
	}

	@Override
	public String runItem(Map<String, Object> mGlobal,
			List<Object> arrArgs) throws Exception {
		ShellExecutor crt = (ShellExecutor)mGlobal.get("SSH2");
		
		if(null == crt){
			throw new CrtException("(Tail)The connection has not been created yet!!!");
		}
			
		String strCmd = (String) arrArgs.get(0);
		String strValueToFind = (String) arrArgs.get(1);
		String strTimeOut = (String) arrArgs.get(2);
		
		//if there is a string like "${}", replace it with the value of the GlobalMap
		strCmd = replaceArgs(mGlobal, strCmd);

		String strResult = crt.run(strCmd,strValueToFind,strTimeOut);
		mGlobal.put("STR_RETURN", strResult);
		
		return null;
			
	}
}
