package storm_falcon.demo.concurrent.simulation.bankteller;

/**
 * @author gewp
 *
 * Read-only objects don't require sychronization.
 */
public class Customer {
    private final int serviceTime;

    public Customer(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "[" + serviceTime +
                ']';
    }
}
