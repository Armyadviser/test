package storm_falcon.swing.socket;

import storm_falcon.util.date.DateHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Window extends JFrame {

	private static final long serialVersionUID = -4560169860038231159L;

	private JTextArea mContent;
	private JTextArea mInput;
	private JButton mSend;
	
	private final String name;
	private DatagramSocket socket;
	private final String destIp;
	private final int destPort = 11105;

	Window(String name, String adapter) {
		super(name);
		initFrame();
		
		this.name = name;
		try {
			this.socket = new DatagramSocket(11106);
		} catch (SocketException e) {
			e.printStackTrace();
		}

		if (adapter == null || adapter.length() == 0) {
			this.destIp = SocketUtil.getBroadcast(adapter);
		} else {
			this.destIp = SocketUtil.getBroadcast().get(0);
		}

		setListener();
	}
	
	private void initFrame() {
		setLayout(new BorderLayout());
		
		mContent = new JTextArea();
		mContent.setEditable(false);
		mInput = new JTextArea(5, 20);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel();
		label.setText("Input:");
		mSend = new JButton();
		mSend.setText("Send");
		panel.add(label, BorderLayout.WEST);
		panel.add(mInput, BorderLayout.CENTER);
		panel.add(mSend, BorderLayout.EAST);
		
		add(mContent, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		
		setSize(500, 500);
		setLocation(650, 100);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	
	private void setListener() {
		mInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && e.isControlDown()) {
                    String msg = mInput.getText();
                    mInput.setText("");

                    msg = name + ":" + msg;
                    SocketUtil.send(socket, destIp, destPort, msg, String::getBytes);
                }
			}
        });
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				socket.close();
				setVisible(false);
				System.exit(0);
			}
		});
		
		mSend.addActionListener(e -> {
			String msg = mInput.getText();
			mInput.setText("");

			msg = name + ":" + msg;
			SocketUtil.send(socket, destIp, destPort, msg, s -> {
				try {
					return s.getBytes("gbk");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				return new byte[0];
			});
		});
	}
	
	void appendMsg(String msg) {
	    String time = DateHelper.getTimeStrFromLongAndFormat(System.currentTimeMillis(), "HH:mm");
		msg = msg.replace(":", ":" + time + "\n") + "\n\n";
		mContent.append(msg);
	}

	public static void main(String[] args) throws Exception {
	    String name = System.getProperty("user.name");
	    String adapter = "";
	    if (args.length == 1) {
			adapter = args[0];
		}
	    new ReceiveThread(new Window(name, adapter)).start();
	}
}
