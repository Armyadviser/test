package storm_falcon.swing.socket;

import org.junit.Test;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

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

    @Test
    public void test() throws SocketException {
        DatagramSocket socket = new DatagramSocket();
        socket.bind(new InetSocketAddress("127.0.0.1", 1234));
        socket.bind(new InetSocketAddress("127.0.0.1", 1234));

    }
}
