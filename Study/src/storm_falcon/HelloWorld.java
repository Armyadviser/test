package storm_falcon;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloWorld {

    public static void main(String[] args) {
        List.of(1, 2, 3, 4)
                .stream()
                .filter(x -> x == 1)
                .filter(x -> false) // x -> x == 2
                .forEach(System.out::println);

        System.out.println(Arrays.toString(args));

        String s = "aa.bb.cc";
        Pattern pattern = Pattern.compile("a+.b+\\.c+");
        Matcher matcher = pattern.matcher(s);
        System.out.println(matcher.matches());

        System.out.println(Arrays.toString(s.split("\\.")));
    }
}
