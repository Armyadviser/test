package storm_falcon.lambdatest.pattern.observer;

/**
 * Created by Storm_Falcon on 2016/7/4.
 *
 */
public class Main {
    public static void main(String[] args) {
        Moon moon = new Moon();
        moon.startSpying(new Nasa());
        moon.startSpying(new Aliens());

        moon.land("An asteroid");
        moon.land("Apollo 11");

        //使用Lambda表达式
        Moon moon1 = new Moon();
        moon1.startSpying(name -> {
            if (name.contains("Apollo")) {
                System.out.println("They are distracted, lets invade earth!");
            }
        });
        moon1.startSpying(name -> {
            if (name.contains("Apollo")) {
                System.out.println("We made it!");
            }
        });

        moon.land("An asteroid");
        moon.land("Apollo 11");
    }
}
