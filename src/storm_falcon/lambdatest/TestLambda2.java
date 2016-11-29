package storm_falcon.lambdatest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Storm_Falcon on 2016/6/24.
 *
 */
public class TestLambda2 {
    public static void main(String[] args) {
        List<Person> list = Arrays.asList(
                new Person("Jack", Person.Gender.MALE, 20),
                new Person("Lisa", Person.Gender.FEMALE, 21),
                new Person("Tom", Person.Gender.MALE, 22)
        );

        //返回姓名年龄的列表
        list.stream()
                .flatMap(person ->
                    Stream.of(person.getName(), person.getAge()))
                .forEach(System.out::println);

        //统计年龄总数
        System.out.println(list.stream()
                .mapToInt(Person::getAge)
                .sum());

        //统计小写字符数量
        String s = "Hello World.";
        System.out.println(s.chars()
                .filter(Character::isLowerCase)
                .count());

        //找出包含最多小写字母的字符串
        System.out.println(
                Stream.of("abc", "Def", "july", "MAY", "apple")
                        .max(
                                Comparator.comparing(item ->
                                    item.chars()
                                    .filter(Character::isLowerCase)
                                    .count()
                                )
                        )
        );
    }
}
