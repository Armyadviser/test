package storm_falcon.demo.concurrent.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * @author gewp
 */
public class Horse implements Runnable {

    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random random = new Random(47);
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier b) {
        barrier = b;
    }

    public synchronized int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (this) {
                    strides += random.nextInt(3);
                }
//                barrier.await();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "Horse " + id;
    }

    public String tracks() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < strides; i++) {
            s.append("*");
        }
        return s.toString() + id;
    }
}
