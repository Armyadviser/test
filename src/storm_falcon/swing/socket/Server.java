package storm_falcon.swing.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	
	private List<MySocket> mSocList;
	
	private ServerSocket mServer;

	public Server() {
		mSocList = new ArrayList<>();
		try {
			mServer = new ServerSocket(8888);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		MessageContext context = new MessageContext();
		
		new WriteOutStream(mSocList, context).start();

		while (true) {
			try {
				//ÿ����һ���ͻ������ӣ����������̼߳���
				Socket socket = mServer.accept();
				MySocket mSocket = new MySocket();
				mSocket.socket = socket;
				mSocList.add(mSocket);
				new ReadInStream(mSocket, context).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.start();
	}
}
