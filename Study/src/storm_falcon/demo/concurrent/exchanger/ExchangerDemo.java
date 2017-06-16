package storm_falcon.demo.concurrent.exchanger;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author gewp
 */
public class ExchangerDemo {
    static int size = 10;
    static int delay = 5;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<List<Fat>> xc = new Exchanger<>();

        List<Fat> producerList = new CopyOnWriteArrayList<>();
        List<Fat> consumerList = new CopyOnWriteArrayList<>();

        exec.execute(new ExchangerProducer<>(BaseGenerator.create(Fat.class), xc, producerList));
        exec.execute(new ExchangerConsumer<>(xc, consumerList));

        TimeUnit.SECONDS.sleep(delay);
        exec.shutdownNow();
    }
}
