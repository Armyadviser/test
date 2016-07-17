package storm_falcon.temp;

import storm_falcon.util.StringDataErrorException;
import storm_falcon.util.file.FileReader;
import storm_falcon.util.file.FileWriter;
import storm_falcon.util.string.StringHelper;

import java.io.IOException;

/**
 * Created by Storm_Falcon on 2015/10/10.
 * 统计文件中bras报文数量
 */
public class PackageNumAnalyse {

    private final static String FILE_SOURCE = "D:/test/监控统计/LogCollectAll.log";

    private final static String FILE_NEW = "D:/test/监控统计/LogCollectAll_new.log";

    public static void main(String[] args) throws IOException, StringDataErrorException {
        FileWriter mWriter = new FileWriter();
        mWriter.open(FILE_NEW);

        FileReader mReader = new FileReader();
        mReader.open(FILE_SOURCE);

        while (mReader.hasNext()) {
            String line = mReader.getLine();
            String sTime = "";
            if (line.contains("2015") && line.length() == 18) {
                sTime = line.substring(0, line.indexOf("."));
                long lTime = Long.parseLong(sTime);
                if (lTime < 20150928000000l || lTime > 20151010230000l) {
                    continue;
                }

                //跳过不需要的行
                for (int i = 0; i < 10; i++) {
                    line = mReader.nextLine();
                    if (line.contains("Ip")) {
                        break;
                    }
                }

                int total = 0, auth = 0, start = 0, stop = 0;
                line = mReader.getLine();
                while (line.contains("Ip")) {
                    String sTemp = StringHelper.getContent(line, "Total=", ",", 1);
                    int nTemp = Integer.parseInt(sTemp);
                    total += nTemp;

                    sTemp = StringHelper.getContent(line, "Auth=", ",", 1);
                    nTemp = Integer.parseInt(sTemp);
                    auth += nTemp;

                    sTemp = StringHelper.getContent(line, "Start=", ",", 1);
                    nTemp = Integer.parseInt(sTemp);
                    start += nTemp;

                    sTemp = line.substring(line.indexOf("Stop=") + 5, line.length());
                    nTemp = Integer.parseInt(sTemp);
                    stop += nTemp;

                    line = mReader.nextLine();
                }

//                System.out.println(sTime + "," + total + "," + auth + "," + start + "," + stop);
                mWriter.writeLine(sTime + "," + total + "," + auth + "," + start + "," + stop);
            }
        }

        mReader.close();
        mWriter.close();
    }
}
