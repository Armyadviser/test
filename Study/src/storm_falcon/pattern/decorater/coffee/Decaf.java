package storm_falcon.pattern.decorater.coffee;

import storm_falcon.pattern.decorater.Beverage;

/**
 * 无咖啡因咖啡
 */
public class Decaf extends Beverage {

    public Decaf() {
        description = "Decaf";
    }

    @Override
    public double cost() {
        return 1.05;
    }
}
