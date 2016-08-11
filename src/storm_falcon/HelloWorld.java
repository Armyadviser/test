package storm_falcon;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Storm_Falcon on 2015/10/10.
 *
 * Print a string "Hello World!".
 * Or just for test.
 */
public class HelloWorld {

    public static void main(String[] args) {
		Logger logger = Logger.getLogger("a");
		logger.setLevel(Level.WARNING);
		logger = Logger.getLogger("a");
		logger.log(Level.INFO, "123");
		logger.log(Level.FINE, "000");
		logger.log(Level.WARNING, "456");
		logger.log(Level.SEVERE, () -> "789");
	}
}
