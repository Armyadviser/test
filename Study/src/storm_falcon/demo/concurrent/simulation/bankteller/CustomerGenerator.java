package storm_falcon.demo.concurrent.simulation.bankteller;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author gewp
 *
 * Randomly add a customer to queue.
 */
public class CustomerGenerator implements Runnable {

    private CustomerLine customers;

    private static Random random = new Random(47);

    public CustomerGenerator(CustomerLine customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
                customers.put(new Customer(random.nextInt(1000)));
            }
        } catch (Exception e) {
            System.out.println("CustomerGenerator interrupted.");
        }
        System.out.println("CustomerGenerator terminating.");
    }
}
