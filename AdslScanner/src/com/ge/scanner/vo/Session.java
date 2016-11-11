package com.ge.scanner.vo;

import com.ge.scanner.conn.cm.PBaseModule;
import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.Poid;
import com.portal.pcm.fields.*;

/**
 * Created by Storm_Falcon on 2016/11/7.
 * session vo
 */
public class Session {
	public Account account;
    public String userIp;
	public String brasIp;
    public String sessionId;
    public String desc;

    public static FList getSearchFList(String login) {
        Poid poid = new Poid(PBaseModule.getCurrentDB(), -1, "/search");

        FList in = new FList();
        in.set(FldPoid.getInst(), poid);
        in.set(FldFlags.getInst(), 0);

        String sql = "select X from /cp_ip_dialup_session where F1 = V1";
        in.set(FldTemplate.getInst(), sql);

        FList args = new FList();
        args.set(FldLogin.getInst(), login);
        in.setElement(FldArgs.getInst(), 1, args);

		FList result = new FList();
		result.set(FldIpaddr.getInst());
		result.set(FldDescr.getInst());
		in.setElement(FldResults.getInst(), -1, result);

        return in;
    }

    public static Session parse(FList flist) {
		try {
			String desc = flist.get(FldDescr.getInst());

			//login;ip;brasIp;portId
			//tc_5705501;124.95.30.53;123.189.197.242;560234798
			if (desc == null) {
				return null;
			}

			String[] item = desc.split(";");
			if (item.length != 4) {
				return null;
			}

			Session session = new Session();
			session.brasIp = item[2];

			return session;
		} catch (EBufException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return "Session{" +
			"brasIp='" + brasIp + '\'' +
			'}';
	}
}
