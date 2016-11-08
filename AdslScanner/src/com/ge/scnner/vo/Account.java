package com.ge.scnner.vo;

import com.cp.fields.CpFldMaxTimeout;
import com.cp.fields.CpFldMonthHours;
import com.cp.fields.CpFldVlanId;
import com.ge.scnner.conn.PBaseModule;
import com.portal.pcm.*;
import com.portal.pcm.fields.*;

import java.util.Map;

/**
 * Created by Storm_Falcon on 2016/11/7.
 * user vo
 */
public class Account {
    public Poid poid;
    public String username;
    public Integer section;

    /**
        0 PIN_FLD_POID                      POID [0] 0.0.0.1 /search -1 0
        0 PIN_FLD_FLAGS                      INT [0] 0
        0 PIN_FLD_TEMPLATE                   STR [0] "select X from  /service/cp_broadband where F1 = V1 and F2 = V2"
        0 PIN_FLD_RESULTS                  ARRAY [*] allocated 1, used 1
        1     PIN_FLD_POID                  POID [0] NULL
        0 PIN_FLD_ARGS                     ARRAY [1] allocated 1, used 1
        1     PIN_FLD_SERVICE_IP       SUBSTRUCT [0] allocated 1, used 1
        2         CP_FLD_MONTH_HOURS         INT [0] 1
        0 PIN_FLD_ARGS                     ARRAY [2] allocated 1, used 1
        1     PIN_FLD_SERVICE_IP       SUBSTRUCT [0] allocated 1, used 1
        2         CP_FLD_VLAN_ID             INT [0] 1
    */
    public static FList getSearchFList() {
        Poid poid = new Poid(PBaseModule.getCurrentDB(), -1, "/search");

        FList in = new FList();
        in.set(FldPoid.getInst(), poid);
        in.set(FldFlags.getInst(), 0);

        String sql = "select X " +
            "from /service/cp_broadband " +
            "where service_t.poid_type = '/service/cp_broadband' " +
            "and F1 = V1 and F2 = V2";
        in.set(FldTemplate.getInst(), sql);

        FList args1_2 = new FList();
        args1_2.set(CpFldMonthHours.getInst(), 1);
        FList args1 = new FList();
        args1.set(FldServiceIp.getInst(), args1_2);

        FList args2_2 = new FList();
        args2_2.set(CpFldVlanId.getInst(), 1);
        FList args2 = new FList();
        args2.set(FldServiceIp.getInst(), args2_2);

        in.setElement(FldArgs.getInst(), 1, args1);
        in.setElement(FldArgs.getInst(), 2, args2);

        FList result = new FList();
        result.set(FldPoid.getInst());
        in.setElement(FldResults.getInst(), 100, result);

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

            return account;
        } catch (EBufException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Account{" +
            "poid=" + poid +
            '}';
    }
}
