package storm_falcon.pattern.decorater.coffee;

import storm_falcon.pattern.decorater.Beverage;

/**
 * 浓咖啡，蒸馏咖啡
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
