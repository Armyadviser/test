package storm_falcon.swing.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WriteOutStream extends Thread {

	private List<MySocket> mSocList;
	
	private Map<Socket, PrintWriter> mWriterMap;
	
	private MessageContext mContext;
	
	WriteOutStream(List<MySocket> socketList, MessageContext context) {
		mSocList = socketList;
		mContext = context;
		mWriterMap = new HashMap<>();
	}
	
	/**
	 * @param socket
	 * @throws IOException 
	 */
	private void sendMsg(MySocket socket, String msg) throws IOException {
		if (!socket.socket.isConnected()) {
			mSocList.remove(socket);
			mWriterMap.remove(socket.socket);
			return;
		}
		
		PrintWriter out = mWriterMap.get(socket.socket);
		if (out == null) {
			out = new PrintWriter(socket.socket.getOutputStream());
			mWriterMap.put(socket.socket, out);
		}
		
		out.println(msg);
		out.flush();
	}
	
	/**
	 * @param msg xxx:@yyy zzz
	 * 		from xxx
	 * 		to yyy
	 * 		content zzz
	 * @throws IOException 
	 */
	private void whisper(String msg) throws IOException {
		int nStart = msg.indexOf(" ");
		if (nStart == -1) {
			return;
		}
		
		int nStop;
		
		nStop = msg.indexOf(":");
		String from = msg.substring(0, nStop);
		
		nStart = msg.indexOf("@");
		nStop = msg.indexOf(" ");
		String to = msg.substring(nStart + 1, nStop);
		
		msg = msg.substring(nStop + 1, msg.length());

		for (MySocket socket : mSocList) {
			System.out.print(socket.name + "x");
			System.out.println(from + "x" + to + "x" + msg + "x");
			if (socket.name.equals(to)) {
				sendMsg(socket, from + "@��:" + msg);
				break;
			}
		}
	}
	
	public void run() {
		try {
			while (true) {
				String msg = mContext.getMessage();
				
				if (msg.contains("@")) {
					whisper(msg);
					continue;
				}

				for (MySocket socket : mSocList) {
					sendMsg(socket, msg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
