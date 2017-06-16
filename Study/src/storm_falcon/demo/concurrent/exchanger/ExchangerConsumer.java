package storm_falcon.demo.concurrent.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @author gewp
 */
public class ExchangerConsumer<T> implements Runnable {

    private Exchanger<List<T>> exchanger;

    private List<T> holder;

    private volatile T value;

    public ExchangerConsumer(Exchanger<List<T>> exchanger, List<T> holder) {
        this.exchanger = exchanger;
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                holder = exchanger.exchange(holder);
                for (T x : holder) {
                    value = x;
                    holder.remove(x);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Final value: " + value);
    }
}
