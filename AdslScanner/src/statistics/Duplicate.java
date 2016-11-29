package statistics;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Storm_Falcon on 2016/11/28.
 *
 */
public class Duplicate {

    public static Stream<String> parseLoginFromFile(String path) throws Exception {
        return Files.lines(Paths.get(path))
                .filter(line -> line.startsWith("Find"))
                .map(line -> line.substring(19, line.length()));
    }

    public static void main(String[] args) throws Exception {
//        showLogins(Paths.get("D:", "nohup.out"));

//        for (int i = 25; i <= 28; i++) {
//            String fileName = "2016-11-" + i + ".log";
//            showLogins(Paths.get("D:", fileName));
//        }

        Set<String> set1 = parseLoginFromFile("C:\\Users\\Storm_Falcon\\Desktop\\历史推送.txt")
                .collect(Collectors.toSet());

        Set<String> set2 = parseLoginFromFile("C:\\Users\\Storm_Falcon\\Desktop\\历史推送.txt")
                .filter(line -> !set1.contains(line))
                .collect(Collectors.toSet());

        set1.addAll(set2);
        System.out.println(set1.size());
    }
}
