package storm_falcon.swing.socket;

import java.net.DatagramSocket;

/**
 * Created by falcon on 17-1-4.
 *
 */
public class ReceiveThread extends Thread {

    private DatagramSocket socket;

    private Window window;

    public ReceiveThread(Window window) {
        try {
            socket = new DatagramSocket(11105);
            this.window = window;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            String s = SocketUtil.receive(socket, String::new);
            window.appendMsg(s);
        }
    }

}
