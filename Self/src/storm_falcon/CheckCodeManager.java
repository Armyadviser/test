package storm_falcon;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author gewp
 */
public class CheckCodeManager {

    private final Map<String, DatePair> mCache;

    private CheckCodeManager() {
        mCache = new HashMap<>();

        Thread thread = new Thread(() -> {
            while (true) {
                Set<Map.Entry<String, DatePair>> entrySet;
                synchronized (mCache) {
                    entrySet = mCache.entrySet();
                }
                for (Map.Entry<String, DatePair> entry : entrySet) {
                    DatePair pair = entry.getValue();
                    if (!pair.isValidate()) {
                        synchronized (mCache) {
                            mCache.remove(entry.getKey());
                        }
                    }
                }

                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private static CheckCodeManager instance;

    public static synchronized CheckCodeManager getInstance() {
        if (instance == null) {
            instance = new CheckCodeManager();
        }
        return instance;
    }

    public void put(String mobileNo, String code) {
        synchronized (mCache) {
            mCache.put(mobileNo, new DatePair(code));
        }
    }

    public String get(String mobileNo) {
        DatePair pair;
        synchronized (mCache) {
            pair = mCache.get(mobileNo);
        }
        if (pair == null) {
            return null;
        }
        return pair.getCode();
    }

    public void remove(String mobileNo) {
        synchronized (mCache) {
            mCache.remove(mobileNo);
        }
    }

    private class DatePair {
        private String code;
        private long birthday;

        DatePair(String code) {
            this.code = code;
            birthday = System.currentTimeMillis();
        }

        /**
         * 5分钟有效
         */
        boolean isValidate() {
            long passed = System.currentTimeMillis() - birthday;
            return passed <= 5 * 60 * 1000;
        }

        public String getCode() {
            return code;
        }
    }
}
