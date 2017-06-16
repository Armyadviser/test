package storm_falcon.demo.concurrent.producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author gewp
 */
public class Main {

    public static void main(String[] args) throws Exception {
        final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);
        queue.put(1);
        queue.take();
        final Random random = new Random();

        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        int x = random.nextInt();
                        queue.put(x);
                        System.out.println(x);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println("\t" + queue.take());
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


    }


}
