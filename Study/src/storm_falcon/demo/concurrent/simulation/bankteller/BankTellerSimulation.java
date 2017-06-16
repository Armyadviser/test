package storm_falcon.demo.concurrent.simulation.bankteller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gewp
 */
public class BankTellerSimulation {
    static final int MAX_LINE_SIZE = 50;
    static final int ADJUSTMENT_PERIOD = 1000;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();

        CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
        exec.execute(new CustomerGenerator(customers));
        exec.execute(new TellerManager(exec, customers, ADJUSTMENT_PERIOD));

        System.out.println("Press 'Enter' to quit.");
        System.in.read();
        exec.shutdownNow();
    }
}
