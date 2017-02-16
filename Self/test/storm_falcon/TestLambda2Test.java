package storm_falcon;

import org.junit.Test;
import storm_falcon.lambdatest.Person;
import storm_falcon.lambdatest.TestLambda2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by falcon on 17-2-16.
 *
 */
public class TestLambda2Test {

    private final List<storm_falcon.lambdatest.Person> list = Arrays.asList(
            new Person("Jack", Person.Gender.MALE, 20),
            new Person("Lisa", Person.Gender.FEMALE, 21),
            new Person("Tom", Person.Gender.MALE, 22)
    );

    @Test
    public void getNameAgeTest() throws Exception {
        TestLambda2.getNameAge(list)
            .forEach(System.out::println);
    }

    @Test
    public void ageTotalTest() {
        System.out.println(TestLambda2.ageTotal(list));
    }

    @Test
    public void getLowerCaseNumTest() {
        System.out.println(TestLambda2.getLowerCaseNum("Hello World."));
    }

    public void getMaxLowerCaseStringTest() {
        Stream<String> stream = Stream.of("abc", "Def", "july", "MAY", "apple");
        String s = TestLambda2.getMaxLowerCaseString(stream);
        System.out.println(s);
    }
}
