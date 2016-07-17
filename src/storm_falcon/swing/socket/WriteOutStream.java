package storm_falcon.swing.socket;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteOutStream extends Thread {

	private List<MySocket> mSocList;
	
	private Map<Socket, PrintWriter> mWriterMap;
	
	private MessageContext mContext;
	
	public WriteOutStream(List<MySocket> socketList, MessageContext context) {
		mSocList = socketList;
		mContext = context;
		mWriterMap = new HashMap<Socket, PrintWriter>();
	}
	
	/**
	 * ������Ϣ
	 * @param socket
	 * @throws IOException 
	 */
	private void sendMsg(@NotNull Socket socket, String msg) throws IOException {
		//�ж�socket����״̬������Ͽ����򲻷���
		if (!socket.isConnected()) {
			mSocList.remove(socket);
			mWriterMap.remove(socket);
			return;
		}
		
		//��map�л�ȡ�����
		PrintWriter out = mWriterMap.get(socket);
		if (out == null) {
			out = new PrintWriter(socket.getOutputStream());
			mWriterMap.put(socket, out);
		}
		
		//������Ϣ
		out.println(msg);
		out.flush();
	}
	
	/**
	 * �������Ļ�
	 * @param msg xxx:@yyy zzz
	 * 		from xxx
	 * 		to yyy
	 * 		content zzz
	 * @throws IOException 
	 */
	private void whisper(@NotNull String msg) throws IOException {
		int nStart = msg.indexOf(" ");
		if (nStart == -1) {
			return;
		}
		
		int nStop = 0;
		
		//��Դ�ˣ�xxx
		nStop = msg.indexOf(":");
		String from = msg.substring(0, nStop);
		
		//Ŀ���ˣ�yyy
		nStart = msg.indexOf("@");
		nStop = msg.indexOf(" ");
		String to = msg.substring(nStart + 1, nStop);
		
		//��Ϣ����
		msg = msg.substring(nStop + 1, msg.length());
		
		for (int i = 0; i < mSocList.size(); i++) {
			MySocket socket = mSocList.get(i);
			System.out.print(socket.name + "x");
			System.out.println(from + "x" + to + "x" + msg + "x");
			if (socket.name.equals(to)) {
				sendMsg(socket.socket, from + "@��:" + msg);
				break;
			}
		}
	}
	
	public void run() {
		try {
			while (true) {
				String msg = mContext.getMessage();
				
				//�ж�ǰ׺��@��ʾ���Ļ��������͹㲥
				if (msg.indexOf("@") != -1) {
					whisper(msg);
					continue;
				}
				
				//�㲥
				for (int i = 0; i < mSocList.size(); i++) {
					MySocket socket = mSocList.get(i);
					sendMsg(socket.socket, msg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
