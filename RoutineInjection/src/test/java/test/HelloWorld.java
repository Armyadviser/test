package test;

import storm.falcon.injection.ClassMethodLoader;
import storm.falcon.injection.annotation.MainEntry;

public class HelloWorld {

    @MainEntry
    public void run() {
        System.out.println("123");
        System.out.println(456);
    }

    public static void main(String[] args) {
        ClassMethodLoader.getInstance().load();
        new HelloWorld().run();
    }
}
