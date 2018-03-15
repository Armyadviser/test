package storm_falcon.pattern.decorater;

import storm_falcon.pattern.decorater.coffee.DarkRoast;
import storm_falcon.pattern.decorater.coffee.Espresso;
import storm_falcon.pattern.decorater.coffee.HouseBlend;
import storm_falcon.pattern.decorater.condiment.Mocha;
import storm_falcon.pattern.decorater.condiment.Soy;
import storm_falcon.pattern.decorater.condiment.Whip;

public class Main {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription()
            + " $" + beverage.cost());

        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription()
            + " $" + beverage2.cost());

        Beverage beverage3 = new HouseBlend();
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription()
            + " $" + beverage3.cost());

    }
}
