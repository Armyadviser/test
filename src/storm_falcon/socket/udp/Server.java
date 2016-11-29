package storm_falcon.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket(1105);

		byte[] buffer = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

		try {
			while (true) {
				socket.receive(packet);

				String data = new String(
						packet.getData(),
						packet.getOffset(),
						packet.getLength(),
						"utf-8");
				System.out.println(data + "\t" + packet.getPort());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		socket.close();
	}
}
