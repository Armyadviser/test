package storm_falcon.test;

import java.lang.reflect.Field;

/**
 * @author gewp
 */
public class Main {
    public String s;
    public String a;

    public static void main(String[] args) throws Exception {
        Class<Main> aClass = Main.class;
        Field s = aClass.getField("s");

        System.out.println(s);

        Object obj = aClass.newInstance();
        s.set(obj, "123");

        System.out.println(((Main) obj).s);
    }
}
