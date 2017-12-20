package com.gesoft.adsl.tools.commands.shell.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gesoft.adsl.tools.commands.shell.Command;
import com.gesoft.adsl.tools.ssh2.CrtException;
import com.gesoft.adsl.tools.ssh2.CrtExcutor;

/**
 * @author WU
 *#Method,kill the process in the PIDS_LIST
 */
class KillPids extends Command{

	@Override
	public String runItem(Map<String, Object> mGlobal, List<Object> arrArgs)
			throws Exception {
		String strPids = (String) arrArgs.get(0);
		@SuppressWarnings("unchecked")
		ArrayList<String> listPids = (ArrayList<String>) ifExistThenGet(mGlobal, strPids);

		if(null == listPids){
			throw new CrtException(this.getClass() + " Map doesn't have PIDS_LIST !!!");
		}
			
    	CrtExcutor crt = (CrtExcutor) mGlobal.get("SSH2");
    	for(int i = 0, size = listPids.size(); i < size; i++){
    		String pid = listPids.get(i);
    		crt.run("kill -9 " + pid);
    		Thread.sleep(500);
    	}
    	
    	return null;
	}

	@Override
	public int getArgsSize() {
		return 1;
	}
}
