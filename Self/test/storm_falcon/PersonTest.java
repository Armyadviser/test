package storm_falcon;

import org.junit.Test;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by falcon on 17-1-19.
 *
 */
public class PersonTest {
    @Test
    public void compare() throws Exception {
        Set<Person> set = new TreeSet<>(Person.COMPARATOR);
        set.add(new Person("A", "B"));
        set.add(new Person("A", "B"));

        set.stream()
                .map(Person.TO_STRING)
                .forEachOrdered(System.out::println);

        assertEquals(set.size(), 1);
    }

    @Test
    public void sumInt() {
//        List<Person> people = List.of(new Person(18), new Person(19), new Person(20));
//        IntSummaryStatistics summaryStatistics = people.stream()
//                .collect(Collectors.summarizingInt(Person::getAge));
//        System.out.println(summaryStatistics);
    }

    @Test
    public void joining() {
//        List<Person> people = List.of(new Person("A", "B"), new Person("C", "D"), new Person("E", "F"));
//        String s = people.stream()
//                .map(Person::getFirstname)
//                .collect(Collectors.joining(" delimiter ", "prefix ", " suffix"));
//        System.out.println(s);
    }
}