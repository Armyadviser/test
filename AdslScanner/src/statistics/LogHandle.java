package statistics;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by Storm_Falcon on 2016/12/7.
 *
 */
public class LogHandle {

	public static Stream<String> filterLinesFromFile(String path, String keyword) throws Exception {
		return Files.lines(Paths.get(path))
			.filter(line -> line.contains(keyword));
	}

	public static void main(String[] args) throws Exception {
//		getLoginFromFile("D:/2016-12-06.log", "Find")
// 			.map(line -> line.substring(30, line.length)
//          .forEach(System.out::println);
//		getLoginFromFile("D:/2016-12-07.log", "Find").forEach(System.out::println);

		filterLinesFromFile("D:/201612061900-201612070830.log", "CRM response info")
			.filter(line -> line.contains("null"))
			.map(line -> {
				int index = line.indexOf("----");
				return line.substring(11, index);
			})
			.forEach(System.out::println);
	}
}
