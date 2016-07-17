package storm_falcon.swing.socket;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MessageContext {

	@NotNull
	private List<String> mList = new ArrayList<String>();
	
	public synchronized void putMssage(String msg) {
		System.out.println(msg);
		mList.add(msg);
		if (mList.size() == 1) {
			this.notify();
		}
	}
	
	public synchronized String getMessage() {
		if (mList.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return mList.remove(0);
	}
}
