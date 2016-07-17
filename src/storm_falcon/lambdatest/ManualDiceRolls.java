package storm_falcon.lambdatest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

/**
 * Created by Storm_Falcon on 2016/6/29.
 *
 * 手动使用线程模拟掷骰子事件
 */
public class ManualDiceRolls {
	private static final int N = 10000;
//	private static final int N = 10;

	private final double fraction;
	private final Map<Integer, Double> results;
	private final int numberOfThreads;
	private final ExecutorService executor;
	private final int workPerThread;

	public ManualDiceRolls() {
		fraction = 1.0 / N;
		results = new ConcurrentHashMap<>();
		numberOfThreads = Runtime.getRuntime().availableProcessors();
		executor = Executors.newFixedThreadPool(numberOfThreads);
		workPerThread = N / numberOfThreads;
	}

	private List<Future<?>> submitJobs() {
		List<Future<?>> futures = new ArrayList<>();
		for (int i = 0; i < numberOfThreads; i++) {
			futures.add(executor.submit(makeJob()));
		}
		return futures;
	}

	private Runnable makeJob() {
		return () -> {
			ThreadLocalRandom random = ThreadLocalRandom.current();
			for (int i = 0; i < workPerThread; i++) {
				int entry = twoDiceThrows(random);
				accumulateResult(entry);
			}
		};
	}

	private void accumulateResult(int entry) {
		results.compute(entry, (key, previous) ->
			previous == null ? fraction : previous + fraction
		);
	}

	private int twoDiceThrows(ThreadLocalRandom random) {
		int firstThrow = random.nextInt(1, 7);
		int secondThrow = random.nextInt(1, 7);
		return firstThrow + secondThrow;
	}

	private void awaitCompletion(List<Future<?>> futures) {
		futures.forEach(future -> {
			try {
				future.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
		executor.shutdown();
	}

	private void printResults() {
		results.entrySet().forEach(System.out::println);
	}

	public void simulateDiceRoles() {
		List<Future<?>> futures = submitJobs();
		awaitCompletion(futures);
		printResults();
	}

	//--------------------------------------------------------------------------------

	private final static Random random = new Random();

	public static void parallelDiceRolls() {
		double fraction = 1.0 / N;
		Map<Integer, Double> map = IntStream.range(0, N)
//			.parallel()
			.mapToObj(twoDiceThrows())
			.collect(groupingBy(side -> side,
				summingDouble(n -> fraction)));
		map.entrySet()
				.forEach(System.out::println);
	}

	private static IntFunction<Integer> twoDiceThrows() {
		return time -> {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return random.nextInt(6) + random.nextInt(6) + 2;
		};
	}

	public static void main(String[] args) {
		long n1, n2;

//		n1 = System.currentTimeMillis();
//	    ManualDiceRolls rolls = new ManualDiceRolls();
//		rolls.simulateDiceRoles();
//		n2 = System.currentTimeMillis();
//		System.out.println(n2 - n1);

		System.out.println("--------------");

		n1 = System.currentTimeMillis();
	    ManualDiceRolls.parallelDiceRolls();
		n2 = System.currentTimeMillis();
		System.out.println(n2 - n1);
	}

}
