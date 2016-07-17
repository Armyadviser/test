package storm_falcon.bigdata;

import storm_falcon.util.file.FileReader;

import java.util.*;

/**
 * Created by Storm_Falcon on 2016/5/12.
 * 现在有十类人群，每一行表示一类人群
 * 把每类中所有人出现的位置总和按频次从高到低排序
 */
public class EveryLocationCounts {

    public static Map<String, TreatedData> parseFile2Map() {
        final Map<String, TreatedData> map = new HashMap<>();
//        FileReader.iterator(
//            "E:\\Document\\Big Data\\数据1分析v0.2.txt",
//            "utf-8",
//            (reader, line) -> {
//                TreatedData td = TreatedData.parseString(line);
//                if (td != null) {
//                    map.put(td.userId, td);
//                }
//                return null;
//            }
//        );
        return map;
    }

    public static TreatedData getDataById(String id) {
        TreatedData td = null;

        FileReader reader = new FileReader();
        String filePath = "E:\\Document\\Big Data\\数据1分析v0.2.txt";
        reader.open(filePath, "utf-8");
        reader.hasNext();
        while (reader.hasNext()) {
            String line = reader.getLine();
            td = TreatedData.parseString(line);
            if (td == null) {
                continue;
            }
            if (id.equals(td.userId)) {
                break;
            }
        }
        reader.close();

        return td;
    }

    private static void analyse() {
        Map<String, TreatedData> map = parseFile2Map();
        System.out.println("Map size:" + map.size());

        List<int[]> list = new ArrayList<>();
//        FileReader.iterator("E:\\Document\\Big Data\\10类人群.txt",
//            (reader, persons) -> {
//                int mCompanyCounter = 0;
//                int nBusinessCounter = 0;
//                String[] person = persons.split("\\t");
//                for (String p : person) {
//                    TreatedData td = map.get(p);
//                    if (td == null) continue;
//                    mCompanyCounter += td.companyNum;
//                    nBusinessCounter += td.businessNum;
//                }
//                int[] res = new int[] {reader.getLineNumber(), mCompanyCounter, nBusinessCounter};
//                list.add(res);
//                return res;
//            }
//        );

        Collections.sort(list, (o1, o2) -> o2[1] - o1[1]);
        list.forEach(array -> System.out.println(Arrays.toString(array)));
    }

    public static void main(String[] args) {
        analyse();

    }
}
