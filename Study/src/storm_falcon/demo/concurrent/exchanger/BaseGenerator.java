package storm_falcon.demo.concurrent.exchanger;

/**
 * @author gewp
 */
public class BaseGenerator {

    public static <T> Generator<T> create(Class<T> cls) {
        return () -> {
            try {
                return cls.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        };
    }
}
