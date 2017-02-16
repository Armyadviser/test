package storm_falcon.swing.socket;

import org.junit.Test;

/**
 * Created by falcon on 17-1-27.
 *
 */
public class SocketUtilTest {
    @Test
    public void getBroadcast() throws Exception {
        String ip = SocketUtil.getBroadcast("wlp5s0");
        System.out.println(ip);
    }
}
