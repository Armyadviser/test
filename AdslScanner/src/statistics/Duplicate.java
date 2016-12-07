package statistics;

import java.nio.file.Files;
import java.nio.file.Paths;
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
    }
}
