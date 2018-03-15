package storm_falcon.pattern.decorater.condiment;

import storm_falcon.pattern.decorater.Beverage;
import storm_falcon.pattern.decorater.CondimentDecorator;

public class Milk extends CondimentDecorator {

    protected Beverage beverage;

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Milk";
    }

    @Override
    public double cost() {
        return .10 + beverage.cost();
    }
}
