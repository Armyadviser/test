package storm.falcon.injection.manager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WholeRoutineManager {

    private final static int THREAD_NUMBER = 10;

    /**
     * Thread pool to run multy thread method.
     */
    private ExecutorService pool;

    /**
     * The main entry function,
     * declare as list means may be multy threads.
     * These methods will run in parallel.
     */
    private List<Method> mainEntry;

    /**
     * Load all method in cache, in order to create proxy to execute.
     */
    private Map<String, Method> cacheMethod;

    private WholeRoutineManager() {
        mainEntry = new ArrayList<>(10);
        pool = Executors.newFixedThreadPool(THREAD_NUMBER);
    }

    private static WholeRoutineManager instance;

    public static WholeRoutineManager getInstance() {
        if (instance == null) {
            synchronized (WholeRoutineManager.class) {
                if (instance == null) {
                    instance = new WholeRoutineManager();
                }
            }
        }
        return instance;
    }

    public void addMainEntry(Method method) {
        mainEntry.add(method);
    }
}
