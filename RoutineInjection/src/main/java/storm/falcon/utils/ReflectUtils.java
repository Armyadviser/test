package storm.falcon.utils;

import storm.falcon.spring.Student;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.StringJoiner;

public final class ReflectUtils {

    private ReflectUtils() {}

    /**
     * e.g.
     * For function:
     * <pre>{@code
     *  package com.pkg;
     *  class A {
     *      public String fun(String a) throws Exception {}
     *  }
     * }</pre>
     *
     * return: com.pkg.A.fun(java.lang.String)
     */
    public static String getMethodSignature(Method method) {
        Class<?> declaringClass = method.getDeclaringClass();
        String pkg = declaringClass.getPackage().getName();
        String name = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();

        StringBuilder signature = new StringBuilder(pkg + '.' +
                declaringClass.getSimpleName() + '.' +
                name +
                '(');
        if (parameterTypes.length != 0) {
            signature.append(Arrays.toString(parameterTypes));
        }
        signature.append(')');

        return signature.toString()
                .replace("[", "")
                .replace("]", "")
                .replace("class ", "");
    }

    /**
     * D:\\project_name\\src\\
     */
    private final static String CLASSPATH_ROOT;

    static {
        CLASSPATH_ROOT =
                // /D:/project_name/src/
                ReflectUtils.class.getResource("/").getPath()

                        // \\D:\\project_name\\src\\
                        .replace("/","\\")

                        // D:\\project_name\\src\\
                        .replaceFirst("\\\\","");
    }

    /**
     * input: D:/project/com/a/b/C.class
     * output: com.a.b.C
     */
    public static String filePath2ClassPath(String filePath) {
        return filePath.replace(CLASSPATH_ROOT, "")
                .replace("/","\\")
                .replace("\\",".")
                .replace(".class","");
    }

    public static boolean isClassFile(File file) {
        return file.getName().endsWith(".class");
    }

    public static void main(String[] args) throws Exception {
        Method method = Student.class.getDeclaredMethod("setAge", int.class);
        String signature = getMethodSignature(method);
        System.out.println(signature);
    }
}
