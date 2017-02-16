package storm_falcon.bigdata;

import storm_falcon.util.file.FileReader;

import java.util.*;

/**
 * Created by Storm_Falcon on 2016/4/27.
 *
 */
public class DataTest {

    public static Map<String, String> mSameBrand;

    public static void initSameBrand() {
        mSameBrand = new HashMap<>();
        mSameBrand.put("华为", "HUAWEI");
        mSameBrand.put("HUAWEI TECHNOLOGY", "HUAWEI");
        mSameBrand.put("中兴", "ZTE");
        mSameBrand.put("苹果", "Apple");
        mSameBrand.put("三星", "Samsung");
        mSameBrand.put("索尼", "Sony");
        mSameBrand.put("诺基亚", "Nokia");
        mSameBrand.put("海信", "Hisense");
        mSameBrand.put("金立", "Gionee");
        mSameBrand.put("摩托罗拉", "Motorola");
        mSameBrand.put("魅族", "Meizu");
        mSameBrand.put("嘉源", "Cayon");
        mSameBrand.put("小米", "Xiaomi");
        mSameBrand.put("欧珀", "OPPO");
    }

    public static String getBrand(String brand) {
        String s = mSameBrand.get(brand);
        if (s == null) {
            s = brand;
        }
        return s;
    }

    public static Map<String, Set<String>> getPhoneMap(String file) {
        Map<String, Set<String>> phoneMap = new HashMap<>();

        FileReader reader = new FileReader();
        reader.open(file);
        while (reader.hasNext()) {
            String line = reader.getLine();
            String[] item = line.split(",");
            try {
                String brand = item[6];
                String version = item[7];
                brand = getBrand(brand);
                brand = brand.toUpperCase();
                version = version.toUpperCase();

                Set<String> verSet = phoneMap.computeIfAbsent(brand, k -> new HashSet<>());
                verSet.add(version);

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(line);
            }
        }

        return phoneMap;
    }

}