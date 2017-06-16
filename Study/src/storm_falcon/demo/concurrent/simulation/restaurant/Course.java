package storm_falcon.demo.concurrent.simulation.restaurant;

import java.util.Random;

/**
 * @author gewp
 */
public enum Course {
    SPRING_ROLLS, BURRITO, SOUP, VINDALOO;

    private final static Random random = new Random(47);

    public Food randomSelection() {
        return new Food(values()[(random.nextInt(values().length))]);
    }
}
