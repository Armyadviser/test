package storm_falcon;

import java.net.URL;

/**
 * Created by Storm_Falcon on 2015/10/10.
 *
 * Print a string "Hello World!".
 * Or just for test.
 */
public class HelloWord {

    public static void main(String[] args) {
        URL url = Thread.currentThread().getContextClassLoader().getResource("");
        System.out.println(url);
	}
}
