package storm_falcon.swing.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class ReadInStream extends Thread {

	private MessageContext mContext;
	
	private MySocket mSocket;
	
	ReadInStream(MySocket socket, MessageContext context) {
		mSocket = socket;
		mContext = context;
	}
	
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(mSocket.socket.getInputStream(), "utf-8"));
			String sStartInfo = reader.readLine();
			System.out.println(sStartInfo);
			mSocket.name = sStartInfo.substring(sStartInfo.indexOf("%") + 1, sStartInfo.indexOf("&"));
			
			while (mSocket.socket.isConnected()) {
				String line = reader.readLine();
				if (line != null) {
					mContext.putMessage(line);
				}
				
				if ("bye".equals(line)) {
					mSocket.socket.close();
				}
			}
			
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
