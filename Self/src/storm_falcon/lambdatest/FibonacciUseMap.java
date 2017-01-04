package storm_falcon.lambdatest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Storm_Falcon on 2016/6/28.
 *
 * Use a java.util.Map to calculate the fibonacci sequence.
 */
public class FibonacciUseMap {
	private static final Map<Integer, Long> cache;

	static {
		cache = new HashMap<>();
		cache.put(0, 0L);
		cache.put(1, 1L);
	}

	public static long get(int index) {
		return cache.computeIfAbsent(index, integer -> get(integer - 1) + get(integer - 2));
	}

	//用Map.computeIfAbsent计算斐波那契数列
	public static void f3() {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 0);
		map.put(1, 1);

		for (int i = 1; i <= 5; i++) {
			int result = map.computeIfAbsent(i, key -> {
				Integer value1 = map.get(key - 1);
				Integer value2 = map.get(key - 2);

				return value1 + value2;
			});
			System.out.println(result);
		}
	}

	public static void main(String[] args) {
	    for (int i = 1; i < 10; i++) {
			System.out.print(get(i) + " ");
		}

		System.out.println();

		f3();
	}
}
