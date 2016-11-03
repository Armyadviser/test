package storm_falcon;

/**
 * Created by Storm_Falcon on 2015/10/10.
 *
 * Print a string "Hello World!".
 * Or just for test.
 */
public class HelloWorld {

    public static void main(String[] args) throws Exception {
		String sMiddleName = "杨少数";
		if (sMiddleName.length() >= 2) {
			sMiddleName = sMiddleName.substring(0, 1) + "X";
		}

		System.out.println(sMiddleName);
	}
}
