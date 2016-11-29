package storm_falcon.swing.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Storm_Falcon
 * v1.2
 */
public class Client {

	private final String name = System.getProperty("user.name");
	
	private void start(String encode) throws Exception {
		String ip = "127.0.0.1";
		int port = 8888;

		Socket socket = new Socket(ip, port);
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		
		Window window = new Window(name, out);
		
		//read
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), encode));
		while (socket.isConnected()) {
			try {
				String line = reader.readLine();
				window.appendMsg(line);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		reader.close();
		socket.close();
	}
	
	public static void main(String[] args) throws Exception {
		Client client = new Client();
		client.start(args[0]);
	}
}
