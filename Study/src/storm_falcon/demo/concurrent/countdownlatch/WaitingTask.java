package storm_falcon.demo.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author gewp
 */
public class WaitingTask implements Runnable {

    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;

    public WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("Latch barrier passed for " + this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return String.format("WaitingTask %1$-3d", id);
    }
}
