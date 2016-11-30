package storm_falcon;

import storm_falcon.util.file.FileHelper;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Storm_Falcon on 2015/10/10.
 *
 * Print a string "Hello World!".
 * Or just for test.
 */
public class HelloWorld {
    public static void main(String[] args) throws Exception {
    	long n1, n2;

		n1 = System.currentTimeMillis();
		List<File> l2 = FileHelper.findFiles("e:/", "class").collect(Collectors.toList());
		System.out.println("new:" + l2.size());
		n2 = System.currentTimeMillis();
		System.out.println("new:" + (n2 - n1));

		n1 = System.currentTimeMillis();
		List<String> l1 = FileHelper.traversalDir("e:/", false, "class");
		System.out.println("old:" + l1.size());
		n2 = System.currentTimeMillis();
		System.out.println("old:" + (n2 - n1));

	}
}

