package storm_falcon.util;

import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.PortalContext;

import java.util.Properties;

/**
 * @author gewp
 */
public class OpcodeRunner {

    private static Properties properties;

    static {
        properties = new Properties();
        properties.put("infranet.connection",
                "pcp://root.0.0.0.1:password@202.96.74.21:21960" +
                        "/service/admin_client 1");
        properties.put("infranet.login.type", "1");
    }

    public static FList execute(FList in, int opCode) {
        try {
            PortalContext conn = new PortalContext();
            conn.connect(properties);
            return conn.opcode(opCode, in);
        } catch (EBufException e) {
            e.printStackTrace();
        }
        return null;
    }
}
