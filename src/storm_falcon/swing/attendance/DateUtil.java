package storm_falcon.swing.attendance;

import storm_falcon.util.file.FileReader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Storm_Falcon on 2016/1/15.
 *
 */
public class DateUtil {

    private static Set<String> getHolidays() {
        String path = DateUtil.class.getResource("").getPath() + "holiday";
        FileReader reader = new FileReader();
        reader.open(path);

        Set<String> set = new HashSet<>();
        while (reader.hasNext()) {
            set.add(reader.getLine());
        }

        reader.close();
        return set;
    }

    public static void getWorkDays(int year, int month) {
        Set<String> set = getHolidays();

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DATE, 1);

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

        int i = 1;
        while (true) {
            c.set(Calendar.DATE, i++);
            if (month != c.get(Calendar.MONTH) + 1) {
                break;
            }

            Date date = c.getTime();
            String s = df.format(date);

            int day = c.get(Calendar.DAY_OF_WEEK);
            if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
                continue;
            }
            if (set.contains(s)) {
                continue;
            }
            System.out.println(s);
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        getWorkDays(2016, 11);
    }
}
