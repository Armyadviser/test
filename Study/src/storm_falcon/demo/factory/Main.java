package storm_falcon.demo.factory;

import storm_falcon.demo.factory.impl.Calc;

/**
 * @author gewp
 */
public class Main {
    public static void main(String[] args) throws Exception {
        CalcFactory factory = new CalcFactory();
        Calc calc = factory.getCalc("*");
        double result = calc.getResult(1, 2);
        System.out.println(result);
    }
}
