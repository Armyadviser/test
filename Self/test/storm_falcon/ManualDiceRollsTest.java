package storm_falcon;

import org.junit.Test;
import storm_falcon.lambdatest.ManualDiceRolls;

/**
 * Created by falcon on 17-2-16.
 *
 */
public class ManualDiceRollsTest {
    @Test
    public void test1() throws Exception {
        long n1 = System.currentTimeMillis();
        ManualDiceRolls rolls = new ManualDiceRolls();
        rolls.simulateDiceRoles();
        long n2 = System.currentTimeMillis();
        System.out.println(n2 - n1);
    }

    public void test2() throws Exception {
        long n1 = System.currentTimeMillis();
        ManualDiceRolls.parallelDiceRolls();
        long n2 = System.currentTimeMillis();
        System.out.println(n2 - n1);
    }
}
