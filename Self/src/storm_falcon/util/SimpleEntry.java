package storm_falcon.util;

import java.util.AbstractMap;
import java.util.Map;

public class SimpleEntry<K, V> extends AbstractMap.SimpleEntry<K, V> {
    public SimpleEntry(K key, V value) {
        super(key, value);
    }

    public SimpleEntry(Map.Entry<K, V> entry) {
        super(entry);
    }
}
