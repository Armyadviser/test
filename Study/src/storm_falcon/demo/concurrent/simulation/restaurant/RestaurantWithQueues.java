package storm_falcon.demo.concurrent.simulation.restaurant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author gewp
 */
public class RestaurantWithQueues {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        Restaurant restaurant = new Restaurant(exec, 5, 2);
        exec.execute(restaurant);

        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();
        System.out.println();
        System.out.println();
    }
}
