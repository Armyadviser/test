package storm_falcon;

import org.junit.Test;
import storm_falcon.lambdatest.TestLambda;

/**
 * Created by falcon on 17-2-16.
 *
 */
public class TestLambdaTest {
    @Test
    public void f1Test() throws Exception {
        TestLambda.f1();
    }

    @Test
    public void f2Test() throws Exception {
        TestLambda.f2();
    }
}
