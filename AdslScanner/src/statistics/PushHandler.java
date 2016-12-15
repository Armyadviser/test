package statistics;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Storm_Falcon on 2016/12/15.
 *
 */
public class PushHandler {
	public static void sub() throws Exception {
		String file1 = "C:\\Users\\Storm_Falcon\\Desktop\\辽阳宽带到期信息(12.1-12.31)(done).txt";
		String file2 = "C:\\Users\\Storm_Falcon\\Desktop\\已推送清单.txt";

		Set<String> set = Files.lines(Paths.get(file2)).collect(Collectors.toSet());

		Files.lines(Paths.get(file1))
			.filter(line -> line.contains("key1=0"))
			.map(line -> line.substring(0, line.indexOf(';')))
			.filter(line -> !set.contains(line))
			.forEach(System.out::println);
	}

	public static void main(String[] args) throws Exception {
	    sub();
	}
}
