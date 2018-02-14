package storm.falcon.injection.annotation;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AnnotationActionFactory {

    private Map<Class<? extends Annotation>, AnnotationAction> map;

    private AnnotationActionFactory() {
        map = new ConcurrentHashMap<>();
        map.put(MainEntry.class, new MainEntryAction());
        map.put(Recordable.class, new RecordableAction());
        map.put(Timing.class, new TimingAction());
    }

    private static AnnotationActionFactory instance;

    public static AnnotationActionFactory getInstance() {
        if (instance == null) {
            synchronized (AnnotationActionFactory.class) {
                if (instance == null) {
                    instance = new AnnotationActionFactory();
                }
            }
        }
        return instance;
    }

    public AnnotationAction getAnnotationAction(Class<? extends Annotation> cls) {
        return map.get(cls);
    }
}
