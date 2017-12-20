package storm_falcon;

import java.util.Comparator;
import java.util.function.Function;

/**
 * Created by falcon on 17-1-19.
 * toString, comparator test
 */
public class Person {
    private final String firstname;
    private final String lastname;

    private int age;

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Person(int age) {
        this.age = age;
        firstname = "";
        lastname = "";
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public final static Comparator<Person> COMPARATOR =
            Comparator.comparing(Person::getFirstname)
                .thenComparing(Person::getLastname);

    public final static Function<Person, String> TO_STRING =
            person -> person.getFirstname() + " " + person.getLastname();


}
