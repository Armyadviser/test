package storm_falcon;

import org.junit.Test;

/**
 * Created by falcon on 17-1-11.
 *
 */
public class CacheManagerTest {

    @SuppressWarnings("unchecked")
    private static final CacheManager<Integer, Integer> cache = CacheManager.getInstance();

    static {
        cache.setMaxSize(5);
    }

    @Test
    public void put() throws Exception {
        cache.put(1, 2);
        cache.put(2, 2);
        cache.put(3, 2);
        cache.put(4, 2);
        cache.put(1, 2);
        cache.put(1, 2);
        cache.put(1, 2);
        Integer v = cache.get(5);
        System.out.println(v);
        System.out.println(cache.size());
    }
}