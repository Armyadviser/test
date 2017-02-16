package storm_falcon.lambdatest;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Storm_Falcon on 2016/6/24.
 *
 */
public class TestLambda2 {

    //返回姓名年龄的列表
    public static Stream<Object> getNameAge(List<Person> list) {
        return list.stream()
                .flatMap(person ->
                        Stream.of(person.getName(), person.getAge()));
    }

    //统计年龄总数
    public static int ageTotal(List<Person> list) {
        return list.stream()
                .mapToInt(Person::getAge)
                .sum();
    }

    //统计小写字符数量
    public static long getLowerCaseNum(String s) {
        return s.chars()
                .filter(Character::isLowerCase)
                .count();
    }

    //找出包含最多小写字母的字符串
    public static String getMaxLowerCaseString(Stream<String> stream) {
        Optional<String> optional = stream.max(
                                Comparator.comparing(item ->
                                    item.chars()
                                    .filter(Character::isLowerCase)
                                    .count()
                                )
                        );
        return optional.orElse(null);
    }
}
