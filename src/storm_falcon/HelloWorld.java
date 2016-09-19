package storm_falcon;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Storm_Falcon on 2015/10/10.
 *
 * Print a string "Hello World!".
 * Or just for test.
 */
public class HelloWorld {

	public static String parse(String[] array) {
		return Arrays.stream(array)
				.reduce(
						new StringJoiner(",", "insert into sms_jjj_t values(", ")"),
						StringJoiner::add,
						StringJoiner::merge
				).toString();
	}

    public static void main(String[] args) throws Exception {
		Files.lines(Paths.get("D:/test.txt"))
				.map(line -> line.split("\\|"))
				.filter(array -> array.length == 4)
				.map(HelloWorld::parse)
				.forEach(System.out::println);

	}
}
