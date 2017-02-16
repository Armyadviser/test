package storm_falcon.lambdatest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by Storm_Falcon on 2016/6/24.
 *
 */
@SuppressWarnings("UnusedReturnValue")
public class TestLambda3 {

    //利用map统计数字个数
    public static void f1() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 2, 1, 3, 4, 3);
        Map<Integer, Integer> map = new HashMap<>();
        numbers.forEach(x ->
                map.compute(x,
                        (key, value) -> value == null ? 1 : ++value
                )
        );

        System.out.println(map);
    }

    //******************计算质数个数*******************
    public int countPrimes(int range) {
        int total = 0;
        for (int i = 1; i < range; i++) {
            if (isPrime(i)) total++;
        }
        return total;
    }

    public boolean isPrime(int number) {
        for (int j = 2; j < number; j++) {
            if (number % j == 0) {
                return false;
            }
        }
        return true;
    }
    //******************计算质数个数*******************

    //重写
    public int countPrimes2(int range) {
        return (int) IntStream.range(1, range)
                .parallel()//10W次由7.9s->3.7s
                .filter(this::isPrime2)
                .count();
    }

    public boolean isPrime2(int number) {
        return IntStream.range(2, number)
                .allMatch(value -> number % value != 0);
    }

    public List<String> findHeadings(Reader reader) {
        return withLinesOf(reader,
                lines -> lines.filter(line -> line.endsWith(":"))
                .map(line -> line.substring(0, line.length() - 1))
                .collect(toList()),
                RuntimeException::new
        );
    }

    public <T> T withLinesOf(Reader input,
                             Function<Stream<String>, T> handler,
                             Function<IOException, RuntimeException> error) {
        try (BufferedReader reader = new BufferedReader(input)) {
            return handler.apply(reader.lines());
        } catch (IOException e) {
            throw error.apply(e);
        }
    }

}
