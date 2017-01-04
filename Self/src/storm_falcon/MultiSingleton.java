package storm_falcon;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Storm_Falcon on 2016/9/1.
 *
 */
public class MultiSingleton {
	private final static Map<String, MultiSingleton> mMap = new ConcurrentHashMap<>();

	private MultiSingleton() {}

	public synchronized static MultiSingleton getInstance(String id) {
		return mMap.computeIfAbsent(id, k -> new MultiSingleton());
	}

	public static MultiSingleton getInstance2(String id) {
		MultiSingleton instance;
		synchronized (mMap) {
			instance = mMap.computeIfAbsent(id, k -> new MultiSingleton());
		}
		return instance;
	}

	public static void main(String[] args) {
	    MultiSingleton singleton1 = MultiSingleton.getInstance("1");
		MultiSingleton singleton2 = MultiSingleton.getInstance2("2");
		System.out.println(singleton1 == singleton2);
	}
}