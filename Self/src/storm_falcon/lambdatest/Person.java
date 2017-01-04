package storm_falcon.lambdatest;

/**
 * Created by Storm_Falcon on 2016/5/17.
 *
 */
public class Person {
    private int age;
    private String name;
    private Gender gender = Gender.MALE;
    public enum Gender {
        MALE, FEMALE
    }
    public Person(String name, Gender gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
    public Gender getGender() {
        return gender;
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
            "age=" + age +
            ", name='" + name + '\'' +
            ", gender=" + gender +
            '}';
    }
}