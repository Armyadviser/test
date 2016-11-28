package statistics;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Storm_Falcon on 2016/11/28.
 *
 */
public class Duplicate {

    public static void showLogins(Path path) throws Exception {
        Files.lines(path)
                .filter(line -> line.startsWith("Find"))
                .map(line -> line.substring(19, line.length()))
                .forEach(System.out::println);
    }

    public static void main(String[] args) throws Exception {
//        showLogins(Paths.get("D:", "nohup.out"));

//        for (int i = 25; i <= 28; i++) {
//            String fileName = "2016-11-" + i + ".log";
//            showLogins(Paths.get("D:", fileName));
//        }

        Set<String> set1 = Files.lines(Paths.get("C:\\Users\\Storm_Falcon\\Desktop\\历史推送.txt"))
                .collect(Collectors.toSet());

        Set<String> set2 = Files.lines(Paths.get("C:\\Users\\Storm_Falcon\\Desktop\\新推送.txt"))
                .filter(line -> !set1.contains(line))
                .collect(Collectors.toSet());

        set1.addAll(set2);
        System.out.println(set1.size());
    }
}
