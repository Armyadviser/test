package storm_falcon.temp;

import storm_falcon.util.StringDataErrorException;
import storm_falcon.util.date.DateHelper;
import storm_falcon.util.file.FileReader;
import storm_falcon.util.file.FileWriter;
import storm_falcon.util.string.StringHelper;

/**
 * Created by Storm_Falcon on 2015/10/10.
 *
 *  更改文件格式
 */
public class MenCpuAnalyse74_7 {

    private final static String FILE_SOURCE = "D:/test/监控统计/202.96.74.7.log";

    private final static String FILE_CPU_74 = "D:/test/监控统计/74_cpu.txt";
    private final static String FILE_MEM_74 = "D:/test/监控统计/74_mem.txt";

    public static void main(String[] args) throws StringDataErrorException {
        FileWriter mWriter1 = new FileWriter();
        mWriter1.open(FILE_CPU_74);

        FileWriter mWriter2 = new FileWriter();
        mWriter2.open(FILE_MEM_74);

        FileReader mReader = new FileReader();
        mReader.open(FILE_SOURCE);

        try {
            while(mReader.hasNext()) {
                //Wed Sep 23 11:05:00 CST 2015
                String sTime = StringHelper.cutSpace(mReader.getLine());
                sTime = DateHelper.parseDateToString(sTime, DateHelper.TYPE_SIMPLE);

                //CPU states: 99.2% idle,  0.4% user,  0.3% kernel,  0.0% iowait,  0.0% swap
                String sCpuState = StringHelper.cutSpace(mReader.nextLine());

                String idle = StringHelper.getContent(sCpuState, " ", "%", 2);
                String user = StringHelper.getContent(sCpuState, " ", "%", 4);
                String kernel = StringHelper.getContent(sCpuState, " ", "%", 6);
                String iowait = StringHelper.getContent(sCpuState, " ", "%", 8);
                String swap = StringHelper.getContent(sCpuState, " ", "%", 10);

//                System.out.println(sTime + "," + idle + "," + user + "," + kernel + "," + iowait + "," + swap);
                mWriter1.writeLine(sTime + "," + idle + "," + user + "," + kernel + "," + iowait + "," + swap);

                //PID USERNAME LWP PRI NICE  SIZE   RES STATE    TIME    CPU COMMAND
                mReader.nextLine();

                //Memory: 32G phys mem, 880M free mem, 64G total swap, 64G free swap
                String sMemState = StringHelper.cutSpace(mReader.nextLine());

                String phys = StringHelper.getContent(sMemState, " ", " ", 1);
                String freeMem = StringHelper.getContent(sMemState, " ", " ", 4);
                String total = StringHelper.getContent(sMemState, " ", " ", 7);
                String freeSwap = StringHelper.getContent(sMemState, " ", " ", 10);

//                System.out.println(sTime + "," + phys + "," + freeMem + "," + total + "," + freeSwap);
                mWriter2.writeLine(sTime + "," + phys + "," + freeMem + "," + total + "," + freeSwap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mReader.close();
        mWriter1.close();
        mWriter2.close();
    }
}
