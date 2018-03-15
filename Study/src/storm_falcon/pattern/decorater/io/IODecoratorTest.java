package storm_falcon.pattern.decorater.io;

import java.io.*;

public class IODecoratorTest {
    public static void main(String[] args) throws Exception {
        String fileName = "D:\\IdeaProjects\\test\\Study\\src\\storm_falcon\\pattern\\decorater\\Beverage.java";
        LineNumberReader reader = new LineNumberReader(
                new BufferedReader(new FileReader(fileName))
        );

        String s = reader.readLine();
        int lineNumber = reader.getLineNumber();
        System.out.println(s);
        System.out.println(lineNumber);

        reader.close();


        int c;
        InputStream in = new LowerCaseInputStream(
                new BufferedInputStream(new FileInputStream(fileName)));
        while ((c = in.read()) >= 0) {
            System.out.print((char) c);
        }
        in.close();
    }
}
