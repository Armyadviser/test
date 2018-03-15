package storm_falcon.pattern.decorater.coffee;

import storm_falcon.pattern.decorater.Beverage;

/**
 * 黑咖啡
 * （混合咖啡）
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend";
    }

    @Override
    public double cost() {
        return .89;
    }
}
