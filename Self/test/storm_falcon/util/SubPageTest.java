package storm_falcon.util;

import org.junit.Test;

import java.util.Arrays;

import static storm_falcon.util.SubPage.calcPage;

/**
 * Created by falcon on 17-2-16.
 *
 */
public class SubPageTest {
    @Test
    public void calcTest() throws Exception {
        System.out.println(Arrays.toString(calcPage(3, 5, 50)));
    }
}
