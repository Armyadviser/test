package storm_falcon.swing.socket;

import java.net.DatagramSocket;

/**
 * Created by falcon on 17-1-1.
 *
 */
public class DiscoverThread extends Thread {

    private String localHost;

    public DiscoverThread() {
        localHost = SocketUtil.getLocalAddress();
        System.out.println("DiscoverThread: localhost->" + localHost);
    }

    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(11105);
            System.out.println("DiscoverThread: destPort->" + socket.getPort());
            System.out.println("DiscoverThread: localPort->" + socket.getLocalPort());

            while (true) {
                String data = SocketUtil.receive(socket, String::new);

                if (!(data != null && data.contains(".discover@server"))) {
                    continue;
                }

                data = data.substring(0, data.indexOf('.'));
                System.out.println("[" + data + "] connect to Server.");

                SocketUtil.send(socket, SocketUtil.getBroadcast(), 11106, localHost, String::getBytes);

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
