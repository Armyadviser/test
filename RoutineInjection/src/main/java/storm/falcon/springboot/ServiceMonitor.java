package storm.falcon.springboot;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitor {

    @Before("execution(* storm.falcon..*.*(..))")
    public void before() {
        System.out.println("before");
    }
}
