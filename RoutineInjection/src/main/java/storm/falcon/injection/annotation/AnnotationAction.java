package storm.falcon.injection.annotation;

import java.lang.reflect.Method;

public interface AnnotationAction {
    void doAction(Method method, Object obj, Object[] args);
}
