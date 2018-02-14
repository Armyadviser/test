package storm.falcon.injection;

import storm.falcon.injection.annotation.AnnotationAction;
import storm.falcon.injection.annotation.AnnotationActionFactory;
import storm.falcon.utils.ReflectUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ClassMethodLoader {

    private ClassMethodLoader() {
    }

    private static ClassMethodLoader instance;

    public static ClassMethodLoader getInstance() {
        if (instance == null) {
            synchronized (ClassMethodLoader.class) {
                if (instance == null) {
                    instance = new ClassMethodLoader();
                }
            }
        }
        return instance;
    }

    public void load() {
        System.out.println("Load start");
        List<Class<?>> classList = new ArrayList<>();
        List<String> methodSignatures = new ArrayList<>();
        try {
            String classPath = getClass().getResource("/").getPath();
            load(classPath, classList, methodSignatures);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Load over.");
        System.out.println(classList);
        System.out.println(methodSignatures);
    }

    private void load(String pkg, List<Class<?>> classList, List<String> methodSignatures) throws ClassNotFoundException {
        for (File file : Objects.requireNonNull(new File(pkg).listFiles())) {
            if (ReflectUtils.isClassFile(file)) {
                String classPath = ReflectUtils.filePath2ClassPath(file.getPath());
                Class<?> aClass = Class.forName(classPath);
                classList.add(aClass);
                loadClass(aClass, methodSignatures);
            } else if (file.isDirectory()) {
                load(file.getAbsolutePath(), classList, methodSignatures);
            }
        }

    }

    private void loadClass(Class<?> cls, List<String> methodSignatures) {
        System.out.println("Load class " + cls);
        AnnotationActionFactory factory = AnnotationActionFactory.getInstance();

        for (Method method : cls.getDeclaredMethods()) {
            System.out.println("Load method " + method);
            methodSignatures.add(ReflectUtils.getMethodSignature(method));
            for (Annotation annotation : method.getDeclaredAnnotations()) {
                System.out.println(Arrays.toString(method.getDeclaredAnnotations()));
                AnnotationAction annotationAction = factory.getAnnotationAction(annotation.getClass());
                if (annotationAction == null) {
                    continue;
                }

                annotationAction.doAction(method, null, null);
            }

        }
    }
}
