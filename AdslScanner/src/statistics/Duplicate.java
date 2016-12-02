package statistics;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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
        List<String> list1 = parseLoginFromFile("D:/2016-12-01.log").collect(Collectors.toList());
        List<String> list2 = parseLoginFromFile("D:/2016-12-02.log").collect(Collectors.toList());

        list1.addAll(list2);

        list1.stream()
            .distinct()
            .forEach(System.out::println);
    }
}
