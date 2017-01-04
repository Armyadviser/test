package storm_falcon.bigdata.bigfile;

import storm_falcon.util.file.FileReader;
import storm_falcon.util.file.FileWriter;

/**
 * Created by Storm_Falcon on 2016/4/28.
 *
 */
public class Split {
    public static void main(String[] args) {
        String file = "E:\\Document\\Big Data\\effective.csv";
        FileReader reader = new FileReader();
        reader.open(file);

        String file1 = "E:\\Document\\Big Data\\effective.1-6.csv";
        String file2 = "E:\\Document\\Big Data\\effective.7-8.csv";
        String file3 = "E:\\Document\\Big Data\\effective.9-10.csv";
        String file4 = "E:\\Document\\Big Data\\effective.11-12.csv";
        FileWriter writer1 = new FileWriter();
        FileWriter writer2 = new FileWriter();
        FileWriter writer3 = new FileWriter();
        FileWriter writer4 = new FileWriter();
        writer1.open(file1);
        writer2.open(file2);
        writer3.open(file3);
        writer4.open(file4);

        while (reader.hasNext()) {
            String line = reader.getLine();
            try {
                String[] item = line.split(",");
                String date = item[0];
                String sMonth = date.substring(4, 6);
                int nMonth = Integer.parseInt(sMonth);
                if (nMonth <= 6) {
                    writer1.writeLine(line);
                    continue;
                }
                if (nMonth >= 7 && nMonth <= 8) {
                    writer2.writeLine(line);
                    continue;
                }
                if (nMonth >= 9 && nMonth <= 10) {
                    writer3.writeLine(line);
                    continue;
                }
                if (nMonth >= 11 && nMonth <= 12) {
                    writer4.writeLine(line);
                }
            } catch (Exception e) {
                System.out.println(reader.getLineNumber() + line);
            }
        }

        reader.close();
        writer1.close();
        writer2.close();
        writer3.close();
        writer4.close();
    }
}
