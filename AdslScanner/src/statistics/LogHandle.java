package statistics;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
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
		Stream<String> stream1 = filterLinesFromFile("D:/2016-12-30.log", "Kick off succ:true");
		Stream<String> stream2 = filterLinesFromFile("D:/2016-12-31.log", "Kick off succ:true");
		Stream<String> stream3 = filterLinesFromFile("D:/2017-01-01.log", "Kick off succ:true");
		Stream<String> stream4 = filterLinesFromFile("D:/2017-01-03.log", "Kick off succ:true");

		Stream<String> stream = Stream.concat(stream1, stream2);
		stream = Stream.concat(stream, stream3);
		stream = Stream.concat(stream, stream4);

		Map<String, List<String[]>> result = stream.distinct()
			.map(line -> line.substring(31, line.length())).filter(Objects::nonNull)
			.map(line -> line.split(","))
			.collect(Collectors.groupingBy(items -> items[1]));

		result.entrySet()
			.forEach(entry -> System.out.println(entry.getKey() + "\t" + entry.getValue().size()));
	}
}
