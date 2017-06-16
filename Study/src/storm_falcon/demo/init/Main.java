package storm_falcon.demo.init;

/**
 * @author gewp
 */
public class Main {

    private static int a = 0;

    static {
        a = 1;
        System.out.println(a); // 1
    }

    public static void main(String[] args) {
        System.out.println(Tool.a); // 2
        new Tool();
        System.out.println(Tool.a); // 3
    }
}
