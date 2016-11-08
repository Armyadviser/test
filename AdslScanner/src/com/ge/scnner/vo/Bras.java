package com.ge.scnner.vo;

import com.cp.fields.CpFldVendorId;
import com.ge.scnner.conn.PBaseModule;
import com.portal.pcm.EBufException;
import com.portal.pcm.Element;
import com.portal.pcm.FList;
import com.portal.pcm.Poid;
import com.portal.pcm.fields.*;

/**
 * Created by Storm_Falcon on 2016/11/7.
 * bras vo
 */
public class Bras {
    public String ip;
    public int vendorId;

    public static FList getSearchFList(String brasIp) {
        Poid poid = new Poid(PBaseModule.getCurrentDB(), -1, "/search");

        FList in = new FList();
        in.set(FldPoid.getInst(), poid);
        in.set(FldFlags.getInst(), 0);

        String sql = "select X from /cp_nas where F1 = V1";
        in.set(FldTemplate.getInst(), sql);

        FList args = new FList();
        args.set(FldTermservId.getInst(), brasIp);
        in.setElement(FldArgs.getInst(), 1, args);

        FList result = new FList();
        result.set(CpFldVendorId.getInst());

        in.setElement(FldResults.getInst(), Element.ELEMID_ANY, result);

        return in;
    }

    public static Bras parse(FList flist) {
        try {
            Bras bras = new Bras();
            bras.vendorId = flist.get(CpFldVendorId.getInst());
            bras.ip = flist.get(FldTermservId.getInst());
            return bras;
        } catch (EBufException e) {
            e.printStackTrace();
        }
        return null;
    }
}
