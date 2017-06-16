package storm_falcon.demo.concurrent.simulation.restaurant;

/**
 * @author gewp
 *
 * This is what comes back from the chef.
 */
public class Plate {
    private final Order order;
    private final Food food;

    public Plate(Order order, Food food) {
        this.order = order;
        this.food = food;
    }

    public Order getOrder() {
        return order;
    }

    public Food getFood() {
        return food;
    }

    public String toString() {
        return food.toString();
    }
}
