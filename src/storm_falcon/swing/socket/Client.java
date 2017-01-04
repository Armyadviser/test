package storm_falcon.swing.socket;

import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author Storm_Falcon
 * v1.2
 */
public class Client {

	private final String NAME = System.getProperty("user.name");

	private DatagramSocket socket;

    private String serverIp;

    public Client() {
        try {
            socket = new DatagramSocket(11106);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

	private void findServer() {
        try {
            SocketUtil.send(socket,
                    SocketUtil.getBroadcast(),
                    11105,
                    NAME + ".discover@server",
                    String::getBytes);

            serverIp = SocketUtil.receive(socket, String::new);

            System.out.println("Client:server ip->" + serverIp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private void start() throws Exception {
	    findServer();

		Window window = new Window(NAME, socket, serverIp, 11105);

		new Thread(() -> {
		    while (true) {
                String text = SocketUtil.receive(socket, String::new);
                window.appendMsg(text);
            }
        }).start();
	}
	
	public static void main(String[] args) throws Exception {
		Client client = new Client();
		client.start();
	}
}
