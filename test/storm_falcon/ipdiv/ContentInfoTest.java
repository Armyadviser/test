package storm_falcon.ipdiv;

import org.junit.Test;

/**
 * Created by Storm_Falcon on 2016/11/17.
 *
 */
public class ContentInfoTest {
	@Test
	public void parse() throws Exception {
		String line = "abc-123.3.2.1";
		ContentInfo info = ContentInfo.parse(line, 1, "-");
		System.out.println(info);
	}

}