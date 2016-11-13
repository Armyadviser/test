package storm_falcon;

import storm_falcon.util.file.FileWriter;

import java.time.LocalDateTime;

/**
 * Created by Storm_Falcon on 2015/10/10.
 *
 * Print a string "Hello World!".
 * Or just for test.
 */
public class HelloWorld {

	public static String replace(String content) {
		char x = 0x01;
		String split = String.valueOf(x);
		return content.replaceAll(split, "\n");
	}

    public static void main(String[] args) throws Exception {
		LocalDateTime time1 = LocalDateTime.now();
		LocalDateTime time2 = time1.plusMinutes(-5L);

		int n = time2.compareTo(time1);
		System.out.println(n);
	}
}
