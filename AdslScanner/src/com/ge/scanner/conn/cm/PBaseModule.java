package com.ge.scanner.conn.cm;

import com.ge.scanner.config.ScannerConfig;
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

    private static Properties properties;

    static {
        ScannerConfig config = ScannerConfig.getInstance();
        String host = config.getCmInfo("host");
        String login = config.getCmInfo("login");
        String pwd = config.getCmInfo("password");
        String port = config.getCmInfo("port");
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

    public static FList runOpcode(String strOpcodeName, int nOpcodeType, FList inFList) throws EBufException {
        PortalContext conn = getConnection();
        if (conn == null) {
            return null;
        }

        FList outFList = null;
        try {
            outFList = conn.opcode(nOpcodeType, inFList);
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

}
