package storm_falcon.util.xls;

import jxl.Sheet;
import jxl.Workbook;
import storm_falcon.util.file.FileReader;
import storm_falcon.util.file.FileWriter;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Storm_Falcon on 2016/12/5.
 *
 */
public class Test {

    public static Map<String, String> getLoginMap() throws Exception {
        String filePath = "C:\\Users\\Storm_Falcon\\Desktop\\家客宽带到期信息-1130-辽阳反馈(CRM反馈).xls";
        Workbook workbook = Workbook.getWorkbook(new FileInputStream(filePath));

        Sheet sheet = workbook.getSheet(0);
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i < sheet.getRows(); i++) {
            String tel = sheet.getCell(12, i).getContents();
            String login = sheet.getCell(26, i).getContents();
            map.put(tel, login);
        }
        return map;
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> loginMap = getLoginMap();

        FileReader reader = new FileReader();
        reader.open("C:\\Users\\Storm_Falcon\\Desktop\\crmFiltered.log");

        Set<String> set = new HashSet<>();
        while (reader.hasNext()) {
            String line = reader.getLine();
            if (!line.startsWith("MobileNo")) {
                continue;
            }

            String tel = line.substring(11, line.length());
            line = reader.nextLine();//servid
            line = reader.nextLine();//rpinstid
            line = reader.nextLine();//sign
            if (
                    line.contains("key1=1") ||
                            line.contains("null")) {
                String login = loginMap.get(tel);
                if (login != null) {
                    set.add(login);
                } else {
                    System.out.println(tel);
                }
            }
        }
        reader.close();

        FileWriter writer = new FileWriter();
        writer.open("C:\\Users\\Storm_Falcon\\Desktop\\不推.txt");
        for (String s : set) {
            writer.writeLine(s);
        }

        writer.close();
    }
}
