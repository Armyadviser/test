package statistics;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Storm_Falcon on 2016/12/5.
 *
 */
public class Xls {

    public static void main(String[] args) throws Exception {
        Files.lines(Paths.get("C:\\Users\\Storm_Falcon\\Desktop\\家客宽带到期信息-1130-辽阳反馈(CRM反馈).xls"))
                .forEach(System.out::println);
    }
}
