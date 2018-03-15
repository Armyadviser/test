package storm_falcon.pattern.decorater.coffee;

import storm_falcon.pattern.decorater.Beverage;

/**
 * 焦炒咖啡
 * （深度烘焙）
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "Dark Roast";
    }

    @Override
    public double cost() {
        return .99;
    }
}
