package storm_falcon.temp;

import storm_falcon.util.date.DateHelper;
import storm_falcon.util.file.FileReader;
import storm_falcon.util.file.FileWriter;

import java.io.IOException;

/**
 * Created by Storm_Falcon on 2015/10/10.
 * 文件格式转换
 */
public class FileTimeConvert {

    private final static String FILE_SOURCE = "D:/test/监控统计/sy_log.log";

    private final static String FILE_NEW = "D:/test/监控统计/sy_log_new.txt";

    public static void main(String[] args) throws IOException {
        FileWriter mWriter = new FileWriter();
        mWriter.open(FILE_NEW);

        FileReader mReader = new FileReader();
        mReader.open(FILE_SOURCE);

        while (mReader.hasNext()) {
            String line = mReader.getLine();
            String[] item = line.split(",");

            String sBeginTime = DateHelper.parseCurrentTimeMillis(item[0], DateHelper.TYPE_SIMPLE);
            String sEndTime = DateHelper.parseCurrentTimeMillis(item[1], DateHelper.TYPE_SIMPLE);

            mWriter.writeLine(sBeginTime + "," + sEndTime + "," + item[2] + "ms");
        }

        mReader.close();
        mWriter.close();
    }
}
