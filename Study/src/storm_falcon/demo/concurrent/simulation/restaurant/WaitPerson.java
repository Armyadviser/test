package storm_falcon.demo.concurrent.simulation.restaurant;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author gewp
 */
public class WaitPerson implements Runnable {

    private static int counter = 0;
    private final int id = counter++;

    private final Restaurant restaurant;
    BlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<>();

    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void placeOrder(Customer customer, Food food) {
        try {
            // Shouldn't actually block because this is a LinkedBlockingQueue with no size limit.
            restaurant.orders.put(new Order(customer, this, food));
        } catch (Exception e) {
            System.out.println(this + " placeOrder interrupted.");
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Blocks until a course is ready
                Plate plate = filledOrders.take();
                System.out.println(this + "received " + plate + " delivering to " + plate.getOrder().getCustomer());
                plate.getOrder().getCustomer().deliver(plate);
            }
        } catch (Exception e) {
            System.out.println(this + " interrupted.");
        }
        System.out.println(this + " off duty.");
    }

    public String toString() {
        return "WaitPerson " + id + " ";
    }
}
