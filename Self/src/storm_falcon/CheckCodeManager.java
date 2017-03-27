package storm_falcon;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gewp
 */
public class CheckCodeManager {

    private final Map<String, DatePair> mCache;

    private CheckCodeManager() {
        mCache = new ConcurrentHashMap<>();

        Thread thread = new Thread(() -> {
            while (true) {
                for (Map.Entry<String, DatePair> entry : mCache.entrySet()) {
                    DatePair pair = entry.getValue();
                    if (!pair.isValidate()) {
                        mCache.remove(entry.getKey());
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
        mCache.put(mobileNo, new DatePair(code));
    }

    public String get(String mobileNo) {
        DatePair pair = mCache.get(mobileNo);
        return pair == null ? null : pair.getCode();
    }

    public void remove(String mobileNo) {
        mCache.remove(mobileNo);
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
