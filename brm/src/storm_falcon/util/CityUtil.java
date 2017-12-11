package storm_falcon.util;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gewp
 */
public class CityUtil {

    private static final LinkedHashMap<String, String> cities = new LinkedHashMap<>(14);

    static {
        cities.put("ln/sy", "沈阳");
        cities.put("ln/dl", "大连");
        cities.put("ln/as", "鞍山");
        cities.put("ln/fs", "抚顺");
        cities.put("ln/bx", "本溪");
        cities.put("ln/dd", "丹东");
        cities.put("ln/jz", "锦州");
        cities.put("ln/yk", "营口");
        cities.put("ln/fx", "阜新");
        cities.put("ln/ly", "辽阳");
        cities.put("ln/tl", "铁岭");
        cities.put("ln/cy", "朝阳");
        cities.put("ln/pj", "盘锦");
//        cities.put("ln/hl", "葫芦岛");
        cities.put("ln/hl", "葫芦");
    }

    public static List<Map.Entry<String, Integer>> convertCity(Map<String, Integer> map) {
        return cities.entrySet()
                .stream()
                .map(entry -> {
                    String cityEn = entry.getKey();
                    int num = map.getOrDefault(cityEn, 0);
                    return new AbstractMap.SimpleEntry<>(entry.getValue(), num);
                }).collect(Collectors.toList());
    }

    public static <K> Map<K, Integer> convertResult(Map<K, ? extends List<?>> map) {
        Map<K, Integer> result = new HashMap<>(14);
        map.forEach((k, v) -> result.put(k, v.size()));
        return result;
    }
}
