package com.ge.scnner;

import com.ge.scnner.conn.PBaseModule;
import com.ge.scnner.vo.Account;
import com.ge.scnner.vo.Bras;
import com.ge.scnner.vo.Session;
import com.portal.pcm.*;
import com.portal.pcm.fields.*;

import java.util.*;

/**
 * Created by Storm_Falcon on 2016/11/7.
 * Scan users and kick them off.
 */
public class Main {

    public static List<Account> getAccountList() {
        List<Account> list = new ArrayList<>();

        FList in = Account.getSearchFList();
        System.out.println(in);

        FList out = PBaseModule.runOpcode("Search", PortalOp.SEARCH, in);
        System.out.println(out);

        if (out == null || !out.hasField(FldResults.getInst())) {
            return list;
        }

        try {
            SparseArray sparseArray = out.get(FldResults.getInst());
            if (sparseArray == null) {
                return list;
            }

            Enumeration results = sparseArray.getValueEnumerator();

            while (results.hasMoreElements()) {
                FList flist = (FList) results.nextElement();
                Account account = Account.parse(flist);
                if (account == null) {
                    continue;
                }

                list.add(account);
            }

            return list;
        } catch (EBufException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static String getLoginByPoid(Poid poid) {
        FList in = new FList();
        in.set(FldPoid.getInst(), poid);
        in.set(FldLogin.getInst());
        System.out.println(in);
        FList out = PBaseModule.runOpcode("ReadField", PortalOp.READ_FLDS, in);
        if (out == null) {
            return null;
        }
        try {
            return out.get(FldLogin.getInst());
        } catch (EBufException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Session> getSessionList(String username) {
        List<Session> list = new ArrayList<>();

        FList in = Session.getSearchFList(username);
        FList out = PBaseModule.runOpcode("Search", PortalOp.SEARCH, in);

        if (out == null || !out.hasField(FldResults.getInst())) {
            return list;
        }

        try {
            SparseArray sparseArray = out.get(FldResults.getInst());
            if (sparseArray == null) {
                return list;
            }

            Enumeration results = sparseArray.getValueEnumerator();

            while (results.hasMoreElements()) {
                FList flist = (FList) results.nextElement();
                Session session = Session.parse(flist);
                if (session == null) {
                    continue;
                }

                list.add(session);
            }
        } catch (EBufException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void kickOff(List<Session> list) {
        list.stream()
            .map(session -> Bras.getSearchFList(session.brasIp))
            .map(fList -> PBaseModule.runOpcode("Search", PortalOp.SEARCH, fList))
            .filter(Objects::nonNull)
            .map(Bras::parse)
            .forEach(bras -> {
                if (bras.vendorId == 2352) {
                    //COA
                    System.out.println("redback");
                } else {
                    //DM
                    System.out.println("huawei");
                }
            });
    }

    public static void main(String[] args) {
//        List<Account> users = getAccountList();
//        System.out.println(users);
//        System.out.println(users != null ? users.size() : 0);

//        Poid poid = Poid.valueOf("0.0.0.1 /service/cp_broadband 94976963 21");
//        String username = getLoginByPoid(poid);
//        System.out.println(username);

//        List<Session> sessions = getSessionList("ln_boss_gd_test_08");
//        System.out.println(sessions);
    }

}
