package storm_falcon.pattern.decorater.condiment;

import storm_falcon.pattern.decorater.Beverage;
import storm_falcon.pattern.decorater.CondimentDecorator;

public class Mocha extends CondimentDecorator {

    protected Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        return .20 + beverage.cost();
    }
}
