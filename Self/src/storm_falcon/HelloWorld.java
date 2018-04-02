package storm_falcon;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Storm_Falcon on 2015/10/10.
 *
 * Print a string "Hello World!".
 * Or just for test.
 */
public class HelloWorld {

    public static void main(String[] args) throws Exception {
        Map<String, List<String[]>> collect = Stream.concat(
                Files.lines(Paths.get("d:/test/reject.2")),
                Files.lines(Paths.get("d:/test/reject.3")))
                .map(line -> {
                    int start = line.indexOf("Login");
                    int end = line.indexOf(",", start + 1);
                    String login = line.substring(start + 6, end);
                    start = line.indexOf("City");
                    end = line.indexOf(",", start + 1);
                    String city = line.substring(start + 5, end);
                    return login + ',' + city;
                })
                .distinct()
                .map(line -> line.split(","))
                .collect(Collectors.groupingBy(array -> array[1]));

        collect.forEach((k, v) -> System.out.println(k + "\t" + v.size()));

    }

}
