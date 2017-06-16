package storm_falcon.demo.callback.thread;

/**
 * @author gewp
 */
public class Main {
    public static void main(String[] args) throws Exception {
        MainThread t1 = new MainThread();
        t1.start();

        BackgroundThread t2 = new BackgroundThread();
        t2.setCallback(t1::setCount);
        t2.start();
    }
}
