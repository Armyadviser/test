package storm.falcon.injection.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import storm.falcon.spring.Student;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestAnnotation {

    /**
     * 123
     */
    @Recordable(text = "abc")
    void logMethod() {
        int a = 2;
        System.out.println(a);
    }

    @Test
    void testLog() throws NoSuchMethodException {
        Method logMethod = TestAnnotation.class.getDeclaredMethod("logMethod");
        System.out.println(logMethod);
//        Annotation[] annotations = logMethod.getAnnotations();
//        System.out.println(Arrays.toString(annotations));

        System.out.println(logMethod.isAnnotationPresent(Recordable.class));

        Recordable annotation = logMethod.getAnnotation(Recordable.class);
        System.out.println(annotation);
        System.out.println(annotation.text());
        System.out.println(Arrays.toString(annotation.args()));
        System.out.println(annotation.level());
    }

    @Timing(showArgs = true, showReturn = true)
    String timeMethod(String a, String b) {
        ApplicationContext ac = new FileSystemXmlApplicationContext("D:\\IdeaProjects\\HelloWorld.HelloWorld\\RoutineInjection\\src\\main\\java\\applicationContext.xml");
        Student stu = (Student) ac.getBean("stu");
        System.out.println(stu.getName());
        System.out.println(stu.getAge());

        System.out.println(a);
        System.out.println(b);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "5";
    }

    @Test
    void testTiming() throws InvocationTargetException, IllegalAccessException {
        Method[] declaredMethods = this.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            Timing annotation = method.getAnnotation(Timing.class);
            if (annotation != null) {
                Object[] args = new Object[] {"1", "2"};
                long start = System.currentTimeMillis();
                Object ret = method.invoke(this, args);
                long end = System.currentTimeMillis();

                String line = "Method:" + method.getName();
                if (annotation.showArgs()) {
                    line += ", Args:" + Arrays.toString(args);
                }
                if (annotation.showReturn()) {
                    line += ", Return:" + ret;
                }
                line += ", Exec " + (end - start) + "ms";
                System.out.println(line);
            }
        }
    }
}
