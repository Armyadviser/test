package storm_falcon.bigdata.bigfile;

import org.junit.Test;

/**
 * Created by falcon on 17-2-16.
 *
 */
public class MergeSortTest {
    @Test
    public void mergeSortTest() throws Exception {
        MergeSort ms = new MergeSort(
                "E:\\Document\\Big Data\\test_sort_data.txt",
                "E:\\Document\\Big Data\\test_sort_data.txt_sorted.csv",
                10,
                String::compareTo);
        ms.sort();
    }
}
