package storm_falcon.demo.concurrent.simulation.bankteller;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author gewp
 *
 * Teach the customer line to display itself
 */
public class CustomerLine extends ArrayBlockingQueue<Customer> {

    public CustomerLine(int maxSize) {
        super(maxSize);
    }

    @Override
    public String toString() {
        if (size() == 0) {
            return "[Empty]";
        }
        StringBuilder s = new StringBuilder();
        for (Customer customer : this) {
            s.append(customer);
        }
        return s.toString();
    }
}
