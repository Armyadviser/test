package storm_falcon.demo.concurrent.exchanger;

/**
 * @author gewp
 */
public interface Generator<T> {
    T next();
}
