import storm_falcon.util.file.FileWriter;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Storm_Falcon on 2016/10/14.
 *
 */
public class SmsDuplicateTest {
    public static void main(String[] args) throws Exception {
        final FileWriter writer = new FileWriter();
        writer.open("F:\\Tencent\\874062225\\FileRecv\\201609_1.txt");

        Files.lines(Paths.get("F:\\Tencent\\874062225\\FileRecv\\201609.txt"), Charset.forName("gbk"))
            .parallel()
            .map(line -> {
                int start = line.indexOf(',');
                int end = line.indexOf(',', start + 1);
                String number = line.substring(start, end);
                return new MyEntry(new AbstractMap.SimpleEntry<>(number, line));
            }).distinct()
            .map(AbstractMap.SimpleEntry::getValue)
//            .peek(System.out::println)
            .forEach(writer::writeLine);

        writer.close();
    }
}

class MyEntry extends AbstractMap.SimpleEntry<String, String> {

    public MyEntry(Map.Entry<? extends String, ? extends String> entry) {
        super(entry);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Map.Entry))
            return false;
        Map.Entry<?,?> e = (Map.Entry<?,?>)o;
        return Objects.equals(e.getKey(), this.getKey());
    }
}