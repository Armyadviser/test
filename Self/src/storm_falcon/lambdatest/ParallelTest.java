package storm_falcon.lambdatest;

import storm_falcon.util.file.FileReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Storm_Falcon on 2016/5/17.
 *
 */
public class ParallelTest {

	public static void main(String[] args) {
		List<String> dataList = new ArrayList<>();
		FileReader.forEach("E:\\Document\\Big Data\\effective.csv", dataList::add);
		System.out.println("load file complete.");

		long l1 = System.currentTimeMillis();
		System.out.println(
				dataList.parallelStream()
						.mapToInt(String::length).sum()
		);
		long l2 = System.currentTimeMillis();
		System.out.println(l2 - l1);

		l1 = System.currentTimeMillis();
		System.out.println(
				dataList.stream()
						.mapToInt(String::length).sum()
		);
		l2 = System.currentTimeMillis();
		System.out.println(l2 - l1);

	}
}
