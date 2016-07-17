package storm_falcon.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

	public static void main(String[] args) throws Exception {
		try {
			DatagramSocket socket = new DatagramSocket(1106);

			byte[] buffer = new byte[1024];
			String serverIp = "127.0.0.1";
			int serverPort = 1105;
			DatagramPacket packet;

			int i = 1;
			while (true) {
				String data = "abc" + i++;
				packet = new DatagramPacket(buffer, buffer.length);
				packet.setData(data.getBytes("utf-8"));
				packet.setAddress(InetAddress.getByName(serverIp));

				packet.setPort(serverPort);
				socket.send(packet);

				packet.setPort(1106);
				socket.send(packet);

				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
