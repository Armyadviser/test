package storm_falcon.util.file;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by falcon on 17-1-9.
 *
 */
public class FileReaderTest {

    @Test
    public void mapForEach() throws Exception {
        String file = "/home/falcon/test/2017-01-08.log";
        FileReader.mapForEach(file,
                Pattern.compile("^(Receiving Decode).*"), Pattern.compile("", Pattern.CANON_EQ),
                Entry::new)
                .forEach(System.out::println);
    }

    static class Entry {
        List<String> list = new ArrayList<>();
        public Entry(List<String> list) {
            this.list = list;
        }
        public String toString() {
            return list.stream().reduce("",
                    (s1, s2) -> s1 + "\n" + s2,
                    (s1, s2) -> s1 + "\n" + s2);
        }
    }

    @Test
    public void regexTest() {
        String s = "<?xml version='1.0' encoding='UTF-8'?>\n";
        Pattern pattern = Pattern.compile("^(\\n)$");
        Matcher matcher = pattern.matcher(s);
        System.out.println(matcher.matches());
    }
}