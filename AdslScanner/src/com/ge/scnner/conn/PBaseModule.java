package com.ge.scnner.conn;

import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.PortalContext;

import java.util.Date;
import java.util.Properties;

/**
 * Created by Storm_Falcon on 2016/11/7.
 * exe FList
 */
public class PBaseModule {
    public static boolean SHOW_TIME = false;

    public static boolean SHOW_DEBUG = true;

    private static Properties properties;

    static {
        String login = "root.0.0.0.1";
        String pwd = "password";
        String host = "202.96.74.21";
        String port = "21960";
        properties = new Properties();
        properties.put("infranet.connection",
            "pcp://" + login + ":" + pwd + "@" + host +
                ":" + port + "/service/admin_client 1");
        properties.put("infranet.login.type", "1");
    }

    private static PortalContext getConnection() {
        try {
            PortalContext conn = new PortalContext();
            conn.connect(properties);
            return conn;
        } catch (EBufException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void freeConnection(PortalContext conn) {
        try {
            conn.close(true);
        } catch (EBufException e) {
            e.printStackTrace();
        }
    }

    public static FList runOpcode(String strOpcodeName, int nOpcodeType, FList inFList) {
        PortalContext conn = getConnection();
        if (conn == null) {
            return null;
        }

        FList outFList = null;
        try {
            long nStart = System.currentTimeMillis();
            outFList = conn.opcode(nOpcodeType, inFList);

            if (SHOW_TIME) {
                System.out.print(new Date() + " ");
                System.out.print(strOpcodeName + " = ");
                System.out.print(System.currentTimeMillis() - nStart);
                System.out.println(" ms");
            }
        } catch(Exception e) {
            doLog(strOpcodeName + " / Input Flist:", inFList.toString());
            doLog(strOpcodeName + " / Output Flist:", e.getMessage());
            e.printStackTrace();
        } finally {
            freeConnection(conn);
        }

        return outFList;
    }

    public static long getCurrentDB() {
        PortalContext conn = getConnection();
        if (conn == null) {
            return 0;
        }

        long nCurrentDB = 0;
        try {
            nCurrentDB = conn.getCurrentDB();
        } finally {
            freeConnection(conn);
        }

        return nCurrentDB;
    }

    private static void doLog(String title, String sContent) {
        if (!SHOW_DEBUG) {
            return;
        }

        if (sContent != null) {
            StringBuffer strBf = new StringBuffer();
            strBf.append('\r');
            strBf.append(title);
            strBf.append('\r');
            strBf.append(sContent);

            System.out.println(strBf);
        }
    }
}
