package statistics;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;
import java.util.stream.Stream;

/**
 * Created by Storm_Falcon on 2016/11/28.
 *
 */
public class Duplicate {

    public static void main(String[] args) throws Exception {
//        Files.lines(Paths.get("D:/bras清单.txt"))
//            .filter(line -> line.length() != 0)
//            .map(line -> {
//                String[] items = line.split("\t");
//                return new String[] {items[0], items[3]};
//            })
//            .map(items -> "update cp_nas_t set nas_codes='secret=ln2003ok;context=" + items[1] + "' where nas_id='" + items[0] + "';")
//            .forEach(System.out::println);

        String sql = Files.lines(Paths.get("D:/bras清单.txt"))
            .map(line -> line.split("\t")[0])
            .map(line -> "'" + line + "'")
            .reduce(new StringJoiner(",\n", "select nas_codes from cp_nas_t where nas_id in (", ");"),
                (joiner, s) -> {joiner.add(s); return joiner;},
                (joiner1, joiner2) -> joiner1.add(joiner2.toString())
                ).toString();
        System.out.println(sql);
    }
}
