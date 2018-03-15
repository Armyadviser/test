package storm_falcon.pattern.decorater.condiment;

import storm_falcon.pattern.decorater.Beverage;
import storm_falcon.pattern.decorater.CondimentDecorator;

/**
 * 豆奶，豆浆
 */
public class Soy extends CondimentDecorator {

    protected Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    @Override
    public double cost() {
        return .15 + beverage.cost();
    }
}
