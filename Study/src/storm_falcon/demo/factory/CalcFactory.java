package storm_falcon.demo.factory;

import storm_falcon.demo.factory.impl.AddCalc;
import storm_falcon.demo.factory.impl.MinusCalc;
import storm_falcon.demo.factory.impl.Calc;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author gewp
 */
public class CalcFactory {

    private Map<String, Calc> map;

    public CalcFactory() {
        map = new HashMap<>();

        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("operator.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            String value = (String) properties.get(key);

            try {
                Class<?> cls = Class.forName(value);
                map.put(key, (Calc) cls.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Calc getCalc(String name) {
        return map.get(name);
    }

    public Calc getFunction2(String name) {
        try {
            Class<?> cls = Class.forName(name);
            return (Calc) cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
