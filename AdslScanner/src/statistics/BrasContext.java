package statistics;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by falcon on 16-12-30.
 *
 */
public class BrasContext {
    public static void main(String[] args) throws Exception {
        String file = "fushun";
        int length = 5;
        int ipIndex = 0;
        int codeIndex = 4;

        Files.lines(Paths.get("/home/falcon/test/" + file))
                .map(line -> line.replaceAll("\\s+", " "))
                .map(line -> line.split(" "))
                .filter(items -> items.length == length)
                .map(items ->
                        "update cp_nas_t " +
                        "set nas_codes='secret=ln2003ok;context=" + items[codeIndex] + "' " +
                        "where nas_id='" + items[ipIndex] + "';")
                .forEach(System.out::println);
    }
}
