package storm.falcon.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestAspectWithXml {

    void beforeAdvice() {
        System.out.println("before");
    }

    void afterAdvice() {
        System.out.println("after");
    }

    void afterReturnAdvice(String result) {
        System.out.println("afterReturn:" + result);
    }

    String aroundAdvice(org.aspectj.lang.ProceedingJoinPoint joinPoint) {
        System.out.println("around start");
        try {
            return (String) joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around end");
        return null;
    }

    void throwingAdvice(Exception e) {
        System.out.println("exception:" + e.getMessage());
    }

    public static void main(String[] args) {
        ApplicationContext ac = new FileSystemXmlApplicationContext(
                "D:\\IdeaProjects\\test\\RoutineInjection\\src\\main\\java\\applicationContext.xml");
        StudentService testService = (StudentService) ac.getBean("testService");
        System.out.println("return:" + testService.save());
    }
}
