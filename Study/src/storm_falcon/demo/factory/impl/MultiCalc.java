package storm_falcon.demo.factory.impl;

/**
 * @author gewp
 */
public class MultiCalc implements Calc {
    @Override
    public double getResult(double a, double b) {
        return a * b;
    }
}
