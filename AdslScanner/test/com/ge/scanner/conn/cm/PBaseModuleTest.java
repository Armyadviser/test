package com.ge.scanner.conn.cm;

import com.ge.scanner.bean.AccountBean;
import com.ge.scanner.bean.BrasBean;
import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.Poid;
import com.portal.pcm.PortalOp;
import com.portal.pcm.fields.FldPoid;

/**
 * Created by Storm_Falcon on 2016/12/7.
 *
 */
public class PBaseModuleTest {
	public static void main(String[] args) throws EBufException {
		System.setProperty("user.dir", "E:\\Code\\IDEAworkspace\\test\\AdslScanner");

		System.out.println(PBaseModule.runOpcode(PortalOp.SEARCH, BrasBean.getSearchFList("221.203.141.61")));
	}
}
