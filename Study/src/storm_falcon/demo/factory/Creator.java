package storm_falcon.demo.factory;

import storm_falcon.demo.factory.impl.FunImpl1;
import storm_falcon.demo.factory.impl.FunImpl2;
import storm_falcon.demo.factory.impl.Function;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gewp
 */
public class Creator {

    private Map<String, Function> map;

    public Creator() {
        map = new HashMap<>();
        map.put("FunImpl1", new FunImpl1());
        map.put("FunImpl2", new FunImpl2());
    }

    public Function getFunction1(String name) {
        return map.get(name);
    }

    public Function getFunction2(String name) {
        try {
            Class<?> cls = Class.forName(name);
            return (Function) cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
