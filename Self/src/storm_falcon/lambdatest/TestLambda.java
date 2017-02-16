package storm_falcon.lambdatest;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by Storm_Falcon on 2016/6/28.
 *
 */
public class TestLambda {

	//统计单词个数
	public static void f1() {
		Stream.of("abc", "def", "abc", "def", "def")
			.collect(groupingBy(x -> x, counting()))
			.forEach((key, value) ->
				System.out.println(key + "\t" + value)
		);
	}

	//自定义收集器实现GroupingBy
	public static void f2() {
		//按单词长度分组
		Map<Integer, List<String>> resList = Stream.of("abc", "defg", "abcgg", "defr", "abc")
			.collect(new GroupingBy<>(String::length));
		System.out.println(resList);

		//按单词首字母分组
		Map<Character, List<String>> resList2 = Stream.of("abc", "defg", "abcgg", "defr", "abc")
			.collect(new GroupingBy<>(s -> s.charAt(0)));
		System.out.println(resList2);
	}

}

class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {

	private final Function<T, K> function;

	public GroupingBy(Function<T, K> function) {
		this.function = function;
	}

	//提供一个新的容器
	@Override
	public Supplier<Map<K, List<T>>> supplier() {
		return HashMap::new;
	}

	//将新元素加入到结果中
	@Override
	public BiConsumer<Map<K, List<T>>, T> accumulator() {
		return (kListMap, t) -> {
			K key = function.apply(t);
			List<T> ts = kListMap.computeIfAbsent(key, k -> new ArrayList<>());
			ts.add(t);
		};
	}

	//将多次计算的结果两两合并
	@Override
	public BinaryOperator<Map<K, List<T>>> combiner() {
		return (kListMap, kListMap2) -> {
			kListMap.putAll(kListMap2);
			return kListMap;
		};
	}

	//最终结果转换
	@Override
	public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
		return kListMap -> kListMap;
	}

	@Override
	public Set<Characteristics> characteristics() {
		Set<Characteristics> set = new HashSet<>();
		set.add(Characteristics.IDENTITY_FINISH);
		return set;
	}
}