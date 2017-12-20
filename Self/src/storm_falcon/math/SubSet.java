package storm_falcon.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author gewp
 */
public class SubSet {
    private static List<List<Integer>> concat(List<List<Integer>> list1, List<List<Integer>> list2) {
        List<List<Integer>> list = new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        return list;
    }

    private static List<List<Integer>> insert(Integer first, List<List<Integer>> lists) {
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> list : lists) {
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(list);
            result.add(copyList);
        }
        return result;
    }

    public static List<List<Integer>> subSets(List<Integer> list) {
        if (list.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }

        Integer first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());

        List<List<Integer>> subAns = subSets(rest);
        List<List<Integer>> subAns2 = insert(first, subAns);

        return concat(subAns, subAns2);
    }
}
