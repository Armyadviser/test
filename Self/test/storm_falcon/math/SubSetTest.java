package storm_falcon.math;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author gewp
 */
public class SubSetTest {
    @Test
    public void subSets() throws Exception {
        List<Integer> list = Arrays.asList(1, 4, 9);
        List<List<Integer>> subSets = SubSet.subSets(list);
        System.out.println(subSets);
    }

}