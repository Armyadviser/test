package storm_falcon.demo.concurrent.simulation.restaurant;

import java.util.concurrent.SynchronousQueue;

/**
 * @author gewp
 */
public class Customer implements Runnable {

    private static int counter = 0;
    private final int id = counter++;

    private final WaitPerson waitPerson;

    // only one course at a time can be received.
    private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<>();

    public Customer(WaitPerson waitPerson) {
        this.waitPerson = waitPerson;
    }

    public void deliver(Plate plate) throws InterruptedException {
        // only blocks if customer is still eating the previous course.
        placeSetting.put(plate);
    }

    @Override
    public void run() {
        for (Course course : Course.values()) {
            Food food = course.randomSelection();
            try {
                waitPerson.placeOrder(this, food);

                // Blocks until course has been dilevered.
                System.out.println(this + "eating " + placeSetting.take());
            } catch (Exception e) {
                System.out.println(this + "waiting for " + course + " interrupted.");
                break;
            }
        }
        System.out.println(this + "finished meal. leaving.");
    }

    public String toString() {
        return "Customer " + id + " ";
    }
}
