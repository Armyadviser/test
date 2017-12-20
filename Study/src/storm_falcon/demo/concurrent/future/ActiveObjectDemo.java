package storm_falcon.demo.concurrent.future;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author gewp
 */
public class ActiveObjectDemo {

    private ExecutorService ex = Executors.newSingleThreadExecutor();

    private Random random = new Random(47);

    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(factor));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Future<Integer> calcInt(final int x, final int y) {
        return ex.submit(() -> {
            System.out.println("start " + x + "+" + y);
            pause(500);
            return x + y;
        });
    }

    public Future<Float> calcFloat(final float x, final float y) {
        return ex.submit(() -> {
            System.out.println("start " + x + "+" + y);
            pause(500);
            return x + y;
        });
    }

    public void shutdown() {
        ex.shutdown();
    }

    public static void main(String[] args) throws Exception {
        ActiveObjectDemo d1 = new ActiveObjectDemo();
        List<Future> results = new CopyOnWriteArrayList<>();

        for (float f = 0.0f; f < 1.0f; f += 0.2f) {
            results.add(d1.calcFloat(f, f));
        }
        for (int i = 0; i < 5; i++) {
            results.add(d1.calcInt(i, i));
        }

        System.out.println("All async calls made.");

        while (results.size() > 0) {
            for (Future f : results) {
                if (f.isDone()) {
                    System.out.println(f.get());
                    results.remove(f);
                }
            }
        }
        d1.shutdown();
    }
}
