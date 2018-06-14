package storm_falcon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LocationUtils {

    /**
     * 从数据库加载所有坐标
     * 文件格式：id,longitude,latitude
     * @param filePath 文件路径
     */
    public static List<Location> loadLocations(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath))
                .map(line -> line.split(","))
                .map(items -> new Location(items[0],
                        Double.valueOf(items[1]),
                        Double.valueOf(items[2])))
                .collect(Collectors.toList());
    }

    public static String locations2Json(Stream<Location> locationStream) {
        return locationStream
                .map(Location::toJson)
                .reduce(new StringJoiner(",", "[", "]"),
                        StringJoiner::add,
                        StringJoiner::merge)
                .toString();
    }

    /**
     * 坐标数据预处理
     * 将经纬度转换成7位GeoHash编码（精确度约为76米）
     * 返回key为坐标编码，value为该范围内的坐标id
     */
    public static Map<String, List<String>> prepareData(List<Location> locations) {
        return locations
                .stream()
                .map(location -> {
                    String geoHashBase32 = new GeoHash(location).getGeoHashBase32();
                    geoHashBase32 = geoHashBase32.substring(0, geoHashBase32.length() - 1);
                    return new String[]{location.id, geoHashBase32};
                })
                .collect(Collectors.toMap(
                        strings -> strings[1],
                        strings -> {
                            List<String> list = new ArrayList<>();
                            list.add(strings[0]);
                            return list;
                        },
                        (value1, value2) -> {
                            List<String> list = new ArrayList<>(value1);
                            list.addAll(value2);
                            return list;
                        }));
    }

    public static Set<String> getNearby(Map<String, List<String>> cachedLocations, Location location) {
        return new GeoHash(location)
                .getGeoHashBase32For9()
                .stream()
                .map(s -> s.substring(0, s.length() - 1))   // 扩大范围，8位编码精确度约19米
                .map(cachedLocations::get)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }

}
