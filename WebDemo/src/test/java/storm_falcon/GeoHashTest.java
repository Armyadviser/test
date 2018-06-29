package storm_falcon;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import static storm_falcon.LocationUtils.getNearby;
import static storm_falcon.LocationUtils.loadLocations;
import static storm_falcon.LocationUtils.prepareData;

class GeoHashTest {

    @Test
    void distance() {
        Location l1 = new Location(123.342106524344,41.769768292031);
        Location l2 = new Location(123.392708607101,41.8583135197246);
        double distance = Location.getDistance(l1, l2);
        System.out.println(distance);
    }

    @Test
    void getGeoHashBase32For9() {
        GeoHash g = new GeoHash(123.342106524344,41.769768292031);
        System.out.println(g.getGeoHashBase32());
        System.out.println(g.getGeoHashBase32For9());
    }

    /**
     * 遍历比较，小于5角秒进行计算
     */
    @Test
    void test1(double lng, double lat) throws Exception {
        long start, end;

        String filePath = "D:\\Code\\IdeaProjects\\test\\WebDemo\\web\\data\\1.txt";
        List<Location> locations = loadLocations(filePath);
        System.out.println("总位置数：" + locations.size());

        Location userLoc = new Location(lng, lat);

        double range = 5D / 3600;// 5角秒，约400多米
        start = System.currentTimeMillis();
        int count = 0;
        for (Location loc : locations) {
            if (Math.abs(userLoc.longitude - loc.longitude) > range) {
                continue;
            }
            if (Math.abs(userLoc.latitude - loc.latitude) > range) {
                continue;
            }
            double distance = Location.getDistance(userLoc, loc);
            count++;
            if (distance < 80) {
                System.out.println(loc.id);
            }
        }
//        locations
////                .stream()
//                .parallelStream()
////                .filter(location -> Math.abs(userLoc.longitude - location.longitude) <= range)
////                .filter(location -> Math.abs(userLoc.latitude - location.latitude) <= range)
//                .filter(location -> Location.getDistance(userLoc, location) < 100)
//                .forEach(System.out::println);
        end = System.currentTimeMillis();
        System.out.println("消耗时长：" + (end - start));
        System.out.println("计算次数：" + count);
    }

    /**
     * geo hash 算法
     */
    @Test
    void test2(double lng, double lat) throws Exception {
        List<Location> locations = loadLocations("D:\\Code\\IdeaProjects\\test\\WebDemo\\web\\data\\1.txt");
        Map<String, List<String>> map = prepareData(locations);
        GeoHash g = new GeoHash(lng, lat);
        System.out.println(g.getGeoHashBase32());
        List<String> strings = g.getGeoHashBase32For9();
        strings = strings.stream()
                .map(string -> string.substring(0, string.length() - 1))
                .collect(Collectors.toList());

        System.out.println(strings);

        long start = System.currentTimeMillis();
        Set<String> set = strings.stream()
                .map(map::get)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
        long end = System.currentTimeMillis();
        set.forEach(System.out::println);
        System.out.println(end - start);
    }

    /**
     * 批量geo hash算平均时间
     */
    @Test
    void test3() throws Exception {
        List<Location> locations = loadLocations("D:\\Code\\IdeaProjects\\test\\WebDemo\\web\\data\\1.txt");
        Map<String, List<String>> cache = prepareData(locations);

        long start = System.currentTimeMillis();
        locations.stream()
                .map(GeoHash::new)
                .map(GeoHash::getGeoHashBase32For9)
                .forEach(locationStrings -> {
                    Set<String> set = locationStrings.stream()
                            .map(cache::get)
                            .filter(Objects::nonNull)
                            .flatMap(List::stream)
                            .collect(Collectors.toSet());
                    System.out.println(set.size());
                });
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 36193);
    }

    /**
     * 批量遍历比较，小于5角秒进行计算
     * 计算平均时间
     */
    @Test
    void test4() throws Exception {
        String filePath = "D:\\Code\\IdeaProjects\\test\\WebDemo\\web\\data\\1.txt";
        List<Location> locations = loadLocations(filePath);

        double range = 5D / 3600;// 5角秒，约400多米
        long start = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger();
        locations.forEach(userLoc -> {
            for (Location loc : locations) {
                if (Math.abs(userLoc.longitude - loc.longitude) > range) {
                    continue;
                }
                if (Math.abs(userLoc.latitude - loc.latitude) > range) {
                    continue;
                }
                double distance = Location.getDistance(userLoc, loc);
                if (distance < 80) {
                    count.incrementAndGet();
                }
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("消耗时长：" + (end - start));
    }

    @Test
    void testAsTool() throws Exception {

        // 加载数据
        // 程序初始化时执行，缓存在内存

        // loadLocations方法是从文件加载，
        // 文件格式：id,longitude,latitude
        // 需修改变为从数据库加载
        String filePath = "D:\\Code\\IdeaProjects\\test\\WebDemo\\web\\data\\1.txt";
        List<Location> locations = LocationUtils.loadLocations(filePath);

        // 坐标预处理
        Map<String, List<String>> cachedLocations = LocationUtils.prepareData(locations);


        // 拿10个点测试
        double[][] points = {
                {1, 123.401383664688, 41.8228926173066},
                {2, 123.392708607101, 41.8583135197246},
                {3, 123.423761806083, 41.8588707869005},
                {4, 123.423673059624, 41.859129579245},
                {5, 123.412058988256, 41.8621706229432},
                {6, 123.455877776123, 41.769565912636},
                {7, 123.455938159669, 41.7726900576179},
                {8, 123.45483462708, 41.7727041049783},
                {9, 123.454764325391, 41.7639028002993},
                {10, 123.454770058681, 41.7642507859377},
                };

        long start = System.nanoTime();
        for (double[] point : points) {
            Set<String> result = getNearby(
                    cachedLocations, new Location(point[1], point[2]));

            System.out.println(point[0] + "号周围76米内的点数：" + result.size());
            System.out.println(result);
        }
        long end = System.nanoTime();

        System.out.println("平均耗时：" + ((end - start) / 10 / 1000) + "ums");
    }

    @Test
    void testForValidity() throws Exception {
        String filePath = "D:\\Code\\IdeaProjects\\test\\WebDemo\\web\\data\\1.txt";
        List<Location> locations = LocationUtils.loadLocations(filePath);

        Map<String, List<String>> geoIdCache = LocationUtils.prepareData(locations);
        Map<String, Location> idLocCache = locations.stream()
                .collect(Collectors.toMap(location -> location.id, Function.identity()));

        Location userLoc = idLocCache.get("64365753");

        Set<String> nearby = getNearby(geoIdCache, userLoc);
        System.out.println(nearby);

        for (String id : nearby) {
            Location nearLoc = idLocCache.get(id);
            double distance = Location.getDistance(userLoc, nearLoc);
            System.out.println(distance);
        }
    }
}