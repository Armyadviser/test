package storm_falcon.bigdata.bigfile;

import storm_falcon.util.file.FileReader;
import storm_falcon.util.file.FileWriter;

/**
 * Created by Storm_Falcon on 2016/10/14.
 *
 */
public class Duplicate {

    public static String getNumber(String line) {
        int start = line.indexOf(',');
        int end = line.indexOf(',', start + 1);
        return line.substring(start + 1, end);
    }

    public static void main(String[] args) {
        FileReader reader = new FileReader();
        reader.open("F:\\Tencent\\874062225\\FileRecv\\201609_1.txt");
//        reader.open("F:\\Tencent\\874062225\\FileRecv\\test.txt");
        FileWriter writer = new FileWriter();
        writer.open("F:\\Tencent\\874062225\\FileRecv\\201609_2.txt");
//        writer.open("F:\\Tencent\\874062225\\FileRecv\\test_1.txt");

        String line = reader.nextLine();
        String number = getNumber(line);
        while (true) {
            String nextLine;
            while (true) {
                nextLine = reader.nextLine();
                if (nextLine == null) {
                    break;
                }
                String nextNumber = getNumber(nextLine);

                if (!number.equals(nextNumber)) {
                    line = nextLine;
                    number = nextNumber;
                    break;
                }
//                System.out.println(number);
            }
            if (line == null) {
                break;
            }
            if (nextLine == null) {
                break;
            }
            writer.writeLine(line);
        }

        reader.close();
        writer.close();
    }
}
