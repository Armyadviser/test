package storm_falcon;

/**
 * Created by Storm_Falcon on 2015/10/10.
 *
 * Print a string "Hello World!".
 * Or just for test.
 */
public class HelloWord {

    public static void main(String[] args) {
		String url = "http://share.dmhy.org";
		String response = JTools.httpGet(url);
		System.out.println(response);
	}
}
