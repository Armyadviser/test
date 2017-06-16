package storm_falcon.demo.concurrent.simulation.restaurant;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author gewp
 */
public class Chef implements Runnable {

    private static int counter = 0;
    private final int id = counter++;

    private final Restaurant restaurant;

    private final Random random = new Random(47);

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Blocks until an order appears.
                Order order = restaurant.orders.take();
                Food requestedItem = order.item();

                // Time to prepare order.
                TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                Plate plate = new Plate(order, requestedItem);
                order.getWaitPerson().filledOrders.put(plate);
            }
        } catch (Exception e) {
            System.out.println(this + " interrupted.");
        }
        System.out.println(this + " off duty.");
    }

    public String toString() {
        return "Chef " + id + " ";
    }
}
