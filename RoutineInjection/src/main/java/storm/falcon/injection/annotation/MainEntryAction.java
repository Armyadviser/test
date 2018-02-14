package storm.falcon.injection.annotation;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.Advised;

import java.lang.reflect.Method;

class MainEntryAction implements AnnotationAction {


    @Override
    public void doAction(Method method, Object obj, Object[] args) {
        // create InvocationHandler
        BeforeAdvice s;
    }
}
