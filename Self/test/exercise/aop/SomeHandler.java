package exercise.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author gewp
 */
public class SomeHandler implements InvocationHandler {

    private Object realProxy;

    public SomeHandler(Object realProxy) {
        this.realProxy = realProxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass());
        return method.invoke(realProxy, args);
    }
}
