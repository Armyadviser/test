package storm_falcon.calc;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author gewp
 */
public class HelloWorld {

    private static Map<String, AbstractCalc> map = new HashMap<>();

    static {
        InputStream inputStream = HelloWorld.class.getResourceAsStream("op.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);

            Enumeration<?> names = properties.propertyNames();
            while (names.hasMoreElements()) {
                String key = (String) names.nextElement();
                String value = properties.getProperty(key);

                AbstractCalc calc = (AbstractCalc) Class.forName(value).newInstance();
                map.put(key, calc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int calc(int a, int b, String c) {
        AbstractCalc calc = map.get(c);
        return calc.getResult(a, b);
    }

    public static void main(String args[]) {
        int a = 5;
        int b = 3;
        String c = "+";

        System.out.println(calc(a, b, c));
    }

}
