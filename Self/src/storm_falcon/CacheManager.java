package storm_falcon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Storm_Falcon on 2016/9/1.
 * LRU
 * Least Recently Used.
 */
public class CacheManager<K, V> {

	private final Map<K, V> mCache;
	private final LinkedList<K> mList;

	private int maxSize = 500;

	private CacheManager() {
		mCache = new HashMap<>();
		mList = new LinkedList<>();
	}

	private static CacheManager instance;

	public static synchronized CacheManager getInstance() {
		if (instance == null) {
			instance = new CacheManager<>();
		}
		return instance;
	}

	public void setMaxSize(int size) {
		this.maxSize = size;
	}

	public void put(K key, V value) {
		synchronized (mCache) {
			if (mCache.size() >= maxSize) {
				K k = mList.removeFirst();
				mCache.remove(k);
			}

			mCache.put(key, value);
			mList.addLast(key);
		}
	}

	public V get(K key) {
		synchronized (mCache) {
			V value = mCache.get(key);
			if (value != null) {
				mList.remove(key);
				mList.addLast(key);
			}
			return value;
		}
	}

	public int size() {
		return mCache.size();
	}
}
