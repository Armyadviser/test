package storm_falcon.demo.exception;

/**
 * @author gewp
 */
public class Main {

    public static String f(String s) {
        try {
            if (s == null) {
                throw new NullPointerException();
            }

            if (s.length() == 0) {
                throw new IllegalArgumentException();
            }

            System.out.println(s);
        } catch (NullPointerException e) {
            System.out.println("null");
        } catch (IllegalArgumentException e) {
            System.out.println("empty");
        } catch (Exception e) {
            System.out.println("unknown");
        } finally {
            s = "Hello World";
        }
        return s;
    }

    public static void main(String[] args) {
        String s = null;
        System.out.println(f(s));
    }
}
