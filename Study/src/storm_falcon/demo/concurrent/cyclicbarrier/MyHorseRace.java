package storm_falcon.demo.concurrent.cyclicbarrier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author gewp
 */
public class MyHorseRace {

    public static void main(String[] args) throws Exception {
        List<MyHorse> horses = new ArrayList<>();
        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            MyHorse horse = new MyHorse(200);
            horses.add(horse);
            exec.execute(horse);
        }

        new Thread(() -> {
            while (true) {
                int finish = 100;

                StringBuilder s = new StringBuilder();
                for (int i = 0; i < finish; i++) {
                    s.append("=");
                }
                System.out.println(s);
                horses.forEach(MyHorse::tracks);

                for (MyHorse horse : horses) {
                    if (horse.getStride() > finish) {
                        System.out.println(horse + " won!");
                        exec.shutdownNow();
                        return;
                    }
                }

                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Collections.synchronizedList(new ArrayList<>());
    }
}
