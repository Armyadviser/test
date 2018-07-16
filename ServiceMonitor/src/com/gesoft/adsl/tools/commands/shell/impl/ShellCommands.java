package com.gesoft.adsl.tools.commands.shell.impl;

import java.util.List;
import java.util.Map;

import com.gesoft.adsl.tools.commands.shell.Command;
import com.gesoft.adsl.tools.ssh2.CrtException;
import com.gesoft.adsl.tools.ssh2.ShellExecutor;

/**
 * @author Wu
 * Execute shell commands directly.
 */
class ShellCommands extends Command{

	@Override
	public int getArgsSize() {
		return 1;
	}

	@Override
	public String runItem(Map<String, Object> mGlobal,
			List<Object> arrArgs) throws Exception {
		ShellExecutor crt = (ShellExecutor)mGlobal.get("SSH2");
		
		if(null == crt){
			throw new CrtException("(ShellCommands)The connection has not been created yet!!!");
		}
			
		String strCmd = (String) arrArgs.get(0);
		//if there is a string like "${}", replace it with the value of the GlobalMap
		strCmd = replaceArgs(mGlobal, strCmd);


		String strReturn = crt.run(strCmd);
		mGlobal.put("STR_RETURN", strReturn);
		return null;
	}

}
