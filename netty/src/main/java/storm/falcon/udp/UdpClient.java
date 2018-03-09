package storm.falcon.udp;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class UdpClient {

    public static void main(String[] args) throws Exception {
        String data = "5678";
        byte[] bytes = data.getBytes(Charset.forName("UTF-8"));
        InetSocketAddress targetHost = new InetSocketAddress("127.0.0.1", 8080);

        DatagramSocket socket = new DatagramSocket();
        socket.send(new java.net.DatagramPacket(bytes, 0, bytes.length, targetHost));
        socket.close();

//        System.out.println(Charset.defaultCharset());
    }
}
