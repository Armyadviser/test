package storm_falcon.math;

import java.awt.geom.Point2D;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class LocationUtils {

    public static void main(String[] args) throws Exception {
        long start, end;

        Location point1 = new Location(123.342000000001,41.7678276380705);
        Location point2 = new Location(123.343000000001,41.7678276380705);

        start = System.nanoTime();
        System.out.println(Location.getDistance(point1, point2));
        end = System.nanoTime();
        System.out.println((end - start) / 1000_000);

        String filePath = "C:\\Users\\gesoft\\Documents\\WeChat Files\\Storm_Falcon\\Files\\1.txt";
        List<Location> locations = Files.lines(Paths.get(filePath))
                .map(line -> line.split(","))
                .map(items -> new Location(items[0],
                        Double.valueOf(items[1]),
                        Double.valueOf(items[2])))
                .collect(Collectors.toList());
        System.out.println("总位置数：" + locations.size());

        Location userLoc = new Location(123.381875,41.802874);

        double range = 5D / 3600;// 5角秒，约400多米
        start = System.currentTimeMillis();
        int count = 0;
        for (Location loc : locations) {
            if (Math.abs(userLoc.longitude - loc.longitude) > range) {
                continue;
            }
            if (Math.abs(userLoc.latitute - loc.latitute) > range) {
                continue;
            }
            double distance = Location.getDistance(userLoc, loc);
            count++;
            if (distance < 200) {
                System.out.println(loc.id);
            }
        }
//        locations
////                .stream()
//                .parallelStream()
////                .filter(location -> Math.abs(userLoc.longitude - location.longitude) <= range)
////                .filter(location -> Math.abs(userLoc.latitute - location.latitute) <= range)
//                .filter(location -> Location.getDistance(userLoc, location) < 100)
//                .forEach(System.out::println);
        end = System.currentTimeMillis();
        System.out.println("消耗时长：" + (end - start));
        System.out.println("计算次数：" + count);
    }
}
