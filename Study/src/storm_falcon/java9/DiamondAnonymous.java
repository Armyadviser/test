package storm_falcon.java9;

import java.util.HashMap;
import java.util.Map;

/**
 * 菱形符号后支持匿名类
 */
public class DiamondAnonymous {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>() {
            @Override
            public int size() {
                return 1;
            }
        };

        System.out.println(map.size());
        map.put("1", "2");
        System.out.println(map.size());
    }
}
