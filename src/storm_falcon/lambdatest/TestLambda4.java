package storm_falcon.lambdatest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Storm_Falcon on 2016/6/6.
 *
 */
public class TestLambda4 {

    //map reduce, groupBy,
    public static void main(String[] args) {
        final String separator = "--------------------------------";
        System.out.println("Hello World!");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);

        //累加1
        System.out.println(
                numbers.parallelStream()
                        .mapToInt(value -> value)
                        .sum()
        );
        System.out.println(separator);

        //累加2
        System.out.println(
                numbers.parallelStream()
                        .reduce(
                                (integer, integer2) -> integer + integer2
                        ).get()
        );
        System.out.println(separator);

        //累加3
        System.out.println(
                numbers.parallelStream()
                        .reduce(
                                0,
                                (sum, num) -> sum = sum + num
                        )
        );
        System.out.println(separator);

        //累加4
        System.out.println(
                numbers.parallelStream()
                        .reduce(
                                0,
                                (sum, num) -> sum + num,
                                (u, t) -> u + t // return merge u and t
                        )
        );
        System.out.println(separator);

        //按奇偶分组
        numbers.parallelStream()
                .collect(
                        Collectors.groupingBy(
                                integer -> integer % 2 == 0 ? "偶数" : "奇数"
                        )
                ).forEach((string, integers) ->
                System.out.println(string + "\t" + integers)
        );
        System.out.println(separator);

        //按奇偶分组2
        Map<Boolean, List<Integer>> map = numbers.stream()
                .collect(Collectors.partitioningBy(x -> x % 2 == 0));
        System.out.println(map);

        System.out.println(separator);
        List<Person> persons = Arrays.asList(
                new Person("abc", Person.Gender.MALE, 15),
                new Person("qwe", Person.Gender.MALE, 16),
                new Person("jkl", Person.Gender.MALE, 17),
                new Person("uio", Person.Gender.MALE, 15),
                new Person("yui", Person.Gender.MALE, 16),
                new Person("asd", Person.Gender.FEMALE, 17));

        //按性别统计数量
        persons.parallelStream()
                .collect(
                        Collectors.groupingByConcurrent(
                                Person::getGender,
                                Collectors.summingInt(person -> 1)
                        )
                ).forEach((gender, num) ->
                System.out.println(gender + "\t" + num)
        );
        System.out.println(separator);

        //不同性别的平均年龄
        persons.parallelStream()
                .collect(
                        Collectors.groupingByConcurrent(
                                Person::getGender,
                                Collectors.averagingInt(Person::getAge)
                        )
                ).forEach((gender, average) ->
                System.out.println(gender + "\t" + average)
        );
        System.out.println(separator);

        //按年龄分组，显示每组的人名
        persons.stream()
                .collect(Collectors.groupingBy(
                        Person::getAge
                )).forEach((age, pList) ->
                        {
                            String nameString = pList.stream()
                                    .map(Person::getName)
                                    .collect(Collectors.toList())
                                    .toString();
                            String nameString2 = pList.stream()
                                    .map(Person::getName)
                                    .collect(Collectors.joining(", ", "[", "]"));
                            System.out.println(age + "\t" + nameString2);
                        }
                );
        System.out.println(separator);

        //按年龄分组，显示每组的人名2
        persons.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getAge,
                                Collectors.mapping(Person::getName, Collectors.toList())
                        )
                ).forEach((age, personList) ->
                System.out.println(age + "\t" + personList));
        System.out.println(separator);

    }
}
