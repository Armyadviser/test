package storm_falcon.demo.init;

/**
 * @author gewp
 */
public class Tool {

    public static int a = 0;

    static {
        a = 1;
        System.out.println(a); // 4
    }

    public Tool() {
        a = 2;
        System.out.println(a); // 5
    }
}
