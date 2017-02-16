package storm_falcon.lambdatest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * Created by Storm_Falcon on 2016/5/17.
 * map, reduce, groupBy
 */
public class MapReduceGroupBy {

    public static void lambda() {
        System.out.println("Hello World!");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);

        //累加1
        System.out.println(
            numbers.parallelStream()
                .mapToInt(value -> value)
                .sum()
        );

        //累加2
        System.out.println(
            numbers.parallelStream()
                .reduce(
                    (integer, integer2) -> integer + integer2
                )
        );

        //累加3
        System.out.println(
            numbers.parallelStream()
                .reduce(
                    0,
                    (sum, num) -> sum = sum + num
                )
        );

        //累加4
        System.out.println(
            numbers.parallelStream()
                .reduce(
                    0,
                    (sum, num) -> sum + num,
                    (u, t) -> u + t // TODO ?
                )
        );

        //按奇偶分组
        numbers.parallelStream()
            .collect(
                Collectors.groupingBy(
                    integer -> integer % 2 == 0 ? "偶数" : "奇数"
                )
            ).forEach((string, integers) -> System.out.println(string + "\t" + integers)
        );


        List<Person> persons = Arrays.asList(
                new Person("July", Person.Gender.MALE, 15),
                new Person("Tom", Person.Gender.MALE, 16),
                new Person("Steven", Person.Gender.FEMALE, 17));

        //按性别统计数量
        persons.parallelStream()
            .collect(
                Collectors.groupingByConcurrent(
                    Person::getGender,
                    Collectors.summingInt(person -> 1)
                )
            ).forEach((gender, num) -> System.out.println(gender + "\t" + num)
        );

        //不同性别的平均年龄
        persons.parallelStream()
            .collect(
                Collectors.groupingByConcurrent(
                    Person::getGender,
                    Collectors.averagingInt(Person::getAge)
                )
            ).forEach((gender, average) -> System.out.println(gender + "\t" + average)
        );

        //筛选姓名最长的人
        Comparator<Person> comparator = Comparator.comparingInt(o -> o.getName().length());//按姓名长度的比较器
        Optional<Person> longestPerson = persons.parallelStream()
            .reduce((accumulator, person) -> //BinaryOperator
                comparator.compare(accumulator, person) > 0 ? accumulator : person //返回通过比较器比较后较大的值
            );//返回Optional，需要get
        System.out.println(longestPerson);

        //筛选姓名最长的人2
        Optional<Person> longestPerson2 = persons.parallelStream()
            .reduce(
                BinaryOperator.maxBy(
                    Comparator.comparingInt(o -> o.getName().length())
                )
            );
        System.out.println(longestPerson2);
    }
}