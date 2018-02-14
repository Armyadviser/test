package storm.falcon.proxy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {

    @Test
    void test() {
        TestObject target = new TestObject();
        InvocationHandler invocationHandler = new MyProxy(target);
        TestInterface proxy = (TestInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), invocationHandler);
        int res = proxy.testMethod(2);
        System.out.println(res);
    }

}
