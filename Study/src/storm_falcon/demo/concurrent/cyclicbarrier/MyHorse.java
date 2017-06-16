package storm_falcon.demo.concurrent.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author gewp
 */
public class MyHorse implements Runnable {
    private static int counter = 0;
    private final int id = counter++;

    private static Random random = new Random(47);

    private int stride = 0;

    private int pause;

    public MyHorse(int pause) {
        this.pause = pause;
    }

    public int getStride() {
        return stride;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (this) {
                stride += random.nextInt(3);
            }

            try {
                TimeUnit.MILLISECONDS.sleep(pause);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void tracks() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < stride; i++) {
            s.append("*");
        }
        System.out.println(s.toString() + id);
    }

    public String toString() {
        return "Horse " + id;
    }
}
