package storm_falcon;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

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

}