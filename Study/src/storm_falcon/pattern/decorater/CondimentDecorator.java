package storm_falcon.pattern.decorater;

/**
 * 调料装饰器
 */
public abstract class CondimentDecorator extends Beverage {

    @Override
    public abstract String getDescription();
}
