package storm_falcon.test;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;

/**
 * @author gewp
 */
public class Test01 {
    public static void main(String[] args) throws Exception {
        CacheAccess<String, Integer> cacheAccess = JCS.getInstance("a");

        for (int i = 0; i < 10000; i++) {
            cacheAccess.put("id" + i, i);
        }

        Thread.sleep(3000);

        int count = 0;
        for (int i = 0; i < 10000; i++) {
            Integer integer = cacheAccess.get("id" + i);
            if (integer == null) {
                cacheAccess.put("id" + i, i);
                count++;
            }
        }

        System.out.println("缓存次数：" + count);
        count = 0;

        for (int i = 0; i < 10000; i++) {
            Integer integer = cacheAccess.get("id" + i);
            if (integer == null) {
                cacheAccess.put("id" + i, i);
                count++;
            }
        }
        System.out.println("缓存次数：" + count);
    }
}
