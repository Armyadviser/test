package com.ge.scanner.bean;

import com.cp.fields.CpFldMonthHours;
import com.cp.fields.CpFldSlotS;
import com.cp.fields.CpFldVlanId;
import com.ge.scanner.conn.cm.PBaseModule;
import com.ge.scanner.vo.Account;
import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.Poid;
import com.portal.pcm.fields.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Storm_Falcon on 2016/11/23.
 * fList about Account.
 */
public class AccountBean {
	/**
	 0 PIN_FLD_POID                      POID [0] 0.0.0.1 /search -1 0
	 0 PIN_FLD_FLAGS                      INT [0] 0
	 0 PIN_FLD_TEMPLATE                   STR [0] "select X from /service/cp_broadband where service_t.poid_type = '/service/cp_broadband' and F1 <= V1 and F2 = V2 and F3 != V3"
	 0 PIN_FLD_RESULTS                  ARRAY [400] allocated 3, used 3
	 1     PIN_FLD_POID                  POID [0] NULL
	 1     PIN_FLD_LOGIN                  STR [0] NULL
	 1     PIN_FLD_SERVICE_IP       SUBSTRUCT [0] allocated 1, used 1
	 2         CP_FLD_SLOTS               STR [0] NULL
	 0 PIN_FLD_ARGS                     ARRAY [1] allocated 1, used 1
	 1     PIN_FLD_SERVICE_IP       SUBSTRUCT [0] allocated 1, used 1
	 2         CP_FLD_MONTH_HOURS         INT [0] 2016112213
	 0 PIN_FLD_ARGS                     ARRAY [2] allocated 1, used 1
	 1     PIN_FLD_SERVICE_IP       SUBSTRUCT [0] allocated 1, used 1
	 2         CP_FLD_VLAN_ID             INT [0] 1
	 0 PIN_FLD_ARGS                     ARRAY [3] allocated 1, used 1
	 1     PIN_FLD_SERVICE_IP       SUBSTRUCT [0] allocated 1, used 1
	 2         CP_FLD_MONTH_HOURS         INT [0] 0
	 */
	public static FList getSearchFList() {
		String sCurTime = new SimpleDateFormat("yyyyMMddHH").format(new Date());
		int nCurTime = Integer.parseInt(sCurTime);

		Poid poid = new Poid(PBaseModule.getCurrentDB(), -1, "/search");

		FList in = new FList();
		in.set(FldPoid.getInst(), poid);
		in.set(FldFlags.getInst(), 0);

		String sql = "select X " +
			"from /service/cp_broadband " +
			"where service_t.poid_type = '/service/cp_broadband' " +
			"and F1 <= V1 and F2 = V2 and F3 != V3";
		in.set(FldTemplate.getInst(), sql);

		FList args1_2 = new FList();
		args1_2.set(CpFldMonthHours.getInst(), nCurTime);
		FList args1 = new FList();
		args1.set(FldServiceIp.getInst(), args1_2);

		FList args2_2 = new FList();
		args2_2.set(CpFldVlanId.getInst(), 1);
		FList args2 = new FList();
		args2.set(FldServiceIp.getInst(), args2_2);

		FList args3_2 = new FList();
		args3_2.set(CpFldMonthHours.getInst(), 0);
		FList args3 = new FList();
		args3.set(FldServiceIp.getInst(), args3_2);

		in.setElement(FldArgs.getInst(), 1, args1);
		in.setElement(FldArgs.getInst(), 2, args2);
		in.setElement(FldArgs.getInst(), 3, args3);

		FList servIp = new FList();
		servIp.set(CpFldSlotS.getInst());

		FList result = new FList();
		result.set(FldPoid.getInst());
		result.set(FldLogin.getInst());
		result.set(FldServiceIp.getInst(), servIp);
		in.setElement(FldResults.getInst(), 400, result);

		return in;
	}

	public static Account parse(FList flist) {
		try {
			Poid poid = flist.get(FldPoid.getInst());
			if (poid == null) {
				return null;
			}
			Account account = new Account();
			account.poid = poid;
			account.login = flist.get(FldLogin.getInst());

			FList servIp = flist.get(FldServiceIp.getInst());
			String slotS = servIp.get(CpFldSlotS.getInst());
			if (slotS != null && slotS.length() != 0) {
				String[] item = slotS.split(",");
				if (item.length == 3) {
					account.userId = item[0];
					account.mobileNo = item[1];
					account.rpInstId = item[2];
				}
			}

			return account;
		} catch (EBufException e) {
			e.printStackTrace();
		}
		return null;
	}
}