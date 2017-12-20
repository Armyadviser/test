package exercise.aop;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author gewp
 */
public class AOP {

    @Test
    public void test1() {
        Calculator calculator = new CalculatorImpl();
        Calculator proxy = (Calculator) Proxy.newProxyInstance(CalculatorImpl.class.getClassLoader(),
                new Class[] {Calculator.class}, new SomeHandler(calculator));
        int result = proxy.calculate(10, 20);
        System.out.println(result);
    }
}
