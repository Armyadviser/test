package storm_falcon.demo.concurrent.simulation.bankteller;

import java.sql.Time;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author gewp
 */
public class TellerManager implements Runnable {

    private ExecutorService exec;
    private CustomerLine customers;

    private PriorityQueue<Teller> workingTellers = new PriorityQueue<>();
    private Queue<Teller> tellersDoingOtherThings = new LinkedList<>();

    private int adjustmentPeriod;

    private static Random random = new Random(47);

    public TellerManager(ExecutorService exec, CustomerLine customers, int adjustmentPeriod) {
        this.exec = exec;
        this.customers = customers;
        this.adjustmentPeriod = adjustmentPeriod;

        // start whith a single teller.
        Teller teller = new Teller(customers);
        exec.execute(teller);
        workingTellers.add(teller);
    }

    public void adjustTellerNumber() {
        // This is actually a control system. By adjusting the numbers.
        // you can reveal stability issues in the control mechanism.
        // If line is too long. add another teller.
        if (customers.size() / workingTellers.size() > 2) {
            // If tellers are on break or doing another job, bring one back.
            if (tellersDoingOtherThings.size() > 0) {
                Teller teller = tellersDoingOtherThings.remove();
                teller.serveCustomerLine();
                workingTellers.offer(teller);
                return;
            }

            // Else create (hire) a new teller.
            Teller teller = new Teller(customers);
            exec.execute(teller);
            workingTellers.add(teller);
            return;
        }

        // If line is short enough. remove a teller.
        if (workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2) {
            reassignOneTeller();
        }

        // If there is no line. we only need one teller.
        if (customers.size() == 0) {
            while (workingTellers.size() > 1) {
                reassignOneTeller();
            }
        }
    }

    // Give a teller a different job or a break.
    private void reassignOneTeller() {
        Teller teller = workingTellers.poll();
        teller.doSomethingElse();
        tellersDoingOtherThings.offer(teller);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();

                System.out.print(customers + " { ");
                for (Teller teller : workingTellers) {
                    System.out.print(teller.shortString() + " ");
                }
                System.out.println("}");
            }
        } catch (Exception e) {
            System.out.println(this + " interrupted.");
        }
        System.out.println(this + " terminating.");
    }

    public String toString() {
        return "TellerManager ";
    }
}
