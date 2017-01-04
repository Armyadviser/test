package storm_falcon.swing.socket;

import java.io.IOException;
import java.net.DatagramSocket;

public class Server {

    private String localhost;

    private String broadcast;

    public Server() {
        localhost = SocketUtil.getLocalAddress();
        broadcast = SocketUtil.getBroadcast();
        System.out.println("DiscoverThread: localhost->" + localhost);
        System.out.println("DiscoverThread: broadcast->" + broadcast);
    }

	private void start() {
		while (true) {
            try {
                DatagramSocket socket = new DatagramSocket(11105);
                System.out.println("DiscoverThread: destPort->" + socket.getPort());
                System.out.println("DiscoverThread: localPort->" + socket.getLocalPort());

                while (true) {
                    String data = SocketUtil.receive(socket, String::new);
                    System.out.println(data);

                    if (data != null && data.contains(".discover@server")) {
                        data = data.substring(0, data.indexOf('.'));
                        System.out.println("[" + data + "] connect to Server.");
                        SocketUtil.send(socket, broadcast, 11106, localhost, String::getBytes);
                        continue;
                    }

                    SocketUtil.send(socket, broadcast, 11106, data, String::getBytes);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.start();
	}
}
