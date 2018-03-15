package storm_falcon.pattern.decorater.condiment;

import storm_falcon.pattern.decorater.Beverage;
import storm_falcon.pattern.decorater.CondimentDecorator;

/**
 * 搅打奶油，奶泡
 */
public class Whip extends CondimentDecorator {

    protected Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    @Override
    public double cost() {
        return .10 + beverage.cost();
    }
}
