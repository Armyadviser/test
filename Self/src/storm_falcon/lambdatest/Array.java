package storm_falcon.lambdatest;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Storm_Falcon on 2016/6/30.
 *
 */
public class Array {

	public static void f1() {
		int[] numbers = new int[10];
		Arrays.setAll(numbers, i -> i * i);
		System.out.println(Arrays.toString(numbers));
	}

	//滑动平均数
	public static void movingAverage() {
		double[] numbers = new double[10];
		int windowLength = 3;//滑动窗口大小

		Random random = new Random();
		Arrays.setAll(numbers, i -> random.nextInt((i + 1) * 10));

		System.out.println("original:" + Arrays.toString(numbers));

		double[] sums = new double[10];
		System.arraycopy(numbers, 0, sums, 0, 10);
		Arrays.parallelPrefix(sums, Double::sum);//该方法会改变原值，所以先复制一份
		System.out.println("sums:" + Arrays.toString(sums));

		double[] result = IntStream.range(windowLength - 1, sums.length)
			.mapToDouble(i -> {
				double prefix = i == windowLength - 1 ? 0 : sums[i - windowLength];
				return (sums[i] - prefix) / windowLength;
			}).toArray();
		System.out.println("result:" + Arrays.toString(result));
	}

	public static void f2() {
		IntStream stream = IntStream.range(1, 10);
		int result = 5 + stream.parallel()
			.reduce(1, (acc, x) -> x + acc);
		System.out.println(result);
	}

	public static void f2withBug() {
		IntStream stream = IntStream.range(1, 10);
		int result = stream.parallel()
			.reduce(5, (acc, x) -> x + acc);
		System.out.println(result);
	}

	public static void sum() {
		int sum = Stream.of(1, 5, 6, 2)
			.parallel()
			.mapToInt(x -> x * x)
			.reduce(0, (acc, x) -> acc + x);
		System.out.println(sum);
	}

	public static void main(String[] args) {
	    f1();
		movingAverage();
		f2();
		f2withBug();
		sum();
	}
}
