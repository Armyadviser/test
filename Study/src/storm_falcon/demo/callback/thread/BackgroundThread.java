package storm_falcon.demo.callback.thread;

import java.util.Random;

/**
 * @author gewp
 */
public class BackgroundThread extends Thread {

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void run() {
        Random random = new Random();

        while (true) {
            int count = random.nextInt();

            callback.accept(count);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
