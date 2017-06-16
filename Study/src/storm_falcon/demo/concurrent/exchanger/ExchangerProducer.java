package storm_falcon.demo.concurrent.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @author gewp
 */
public class ExchangerProducer<T> implements Runnable {

    private Generator<T> generator;

    private Exchanger<List<T>> exchanger;

    private List<T> holder;

    public ExchangerProducer(Generator<T> generator, Exchanger<List<T>> exchanger, List<T> holder) {
        this.generator = generator;
        this.exchanger = exchanger;
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                for (int i = 0; i < ExchangerDemo.size; i++) {
                    holder.add(generator.next());
                }
                // Exchange full for empty.
                holder = exchanger.exchange(holder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
