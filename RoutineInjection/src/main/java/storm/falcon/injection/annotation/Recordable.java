package storm.falcon.injection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Recordable {
    String text();
    String[] args() default {};
    int level() default 1;
    boolean logException() default true;
}
