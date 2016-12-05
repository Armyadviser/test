package statistics;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
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
        final DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        long a = Files.lines(Paths.get("C:\\Users\\Storm_Falcon\\Desktop\\out.txt"))
            .map(line -> {
                try {
                    int index = line.indexOf(',');
                    line = line.substring(index + 1, line.length());
                    index = line.indexOf(' ');
                    line = line.substring(0, index);
                    return line;
                } catch (Exception ignore) {
                }
                return null;
            }).filter(line -> line != null && line.length() > 0)
                .peek(System.out::println)
            .map(line -> {
                try {
                    return df.parse(line);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }).filter(Objects::nonNull)
                .filter(date -> {
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    return c.get(Calendar.YEAR) == 2016 && c.get(Calendar.MONTH) <= 12;
                })
            .count();

        System.out.println(a);
    }
}
