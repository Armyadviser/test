package storm_falcon.bigdata.bigfile;

import storm_falcon.util.file.FileHelper;
import storm_falcon.util.file.FileReader;
import storm_falcon.util.file.FileWriter;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Storm_Falcon on 2016/4/27.
 *
 */
public class MergeSort {
    private final File inFile;

    private final String outFile;

    private final String TEMP_FILE_PREFIX = "temp_";

    private LinkedList<String> mTempFiles;

    private final Comparator<? super String> comparator;

    private static int nTempFileNum = 0;

    /**
     * 输入文件所在目录
     */
    private final String mParentDir;

    /**
     * 每个文件数据量
     */
    private int nSubNum = 100;

    /**
     *
     * @param sInFile
     * @param sOutFile
     * @param subNum
     * @param comparator
     */
    public MergeSort(String sInFile, String sOutFile, int subNum, Comparator<? super String> comparator) {
        inFile = new File(sInFile);
        outFile = sOutFile;
        mParentDir = inFile.getParent();
        nSubNum = subNum;
        mTempFiles = new LinkedList<>();
        this.comparator = comparator;
    }

    public void sort() {
        split();
        sortSingleFile();
        while (mTempFiles.size() != 1) {
            merge();
        }
        FileHelper.rename(mTempFiles.get(0), outFile);
    }

    private void sortSingleFile() {
        FileWriter writer = new FileWriter();
        for (String fileName : mTempFiles) {
            List<String> list = FileReader.mapForEach(fileName).collect(Collectors.toList());
            list.sort(comparator);
            writer.writeAll(fileName, list);
        }
    }

    private void merge() {
        //进行一次归并后新文件列表
        LinkedList<String> newTempFiles = new LinkedList<>();

        FileReader reader1 = new FileReader();
        FileReader reader2 = new FileReader();
        //两两归并
        for (int i = 1; i < mTempFiles.size(); i += 2) {
            String file1 = mTempFiles.get(i - 1);
            String file2 = mTempFiles.get(i);
            reader1.open(file1);
            reader2.open(file2);

            FileWriter writer = new FileWriter();
            String tempFileName = mParentDir + File.separatorChar + TEMP_FILE_PREFIX + (++nTempFileNum);
            newTempFiles.add(tempFileName);
            writer.open(tempFileName);

            String data1 = reader1.nextLine();
            String data2 = reader2.nextLine();
            while (data1 != null && data2 != null) {
                int differ = comparator.compare(data1, data2);
                if (differ < 0) {
                    writer.writeLine(data1);
                    data1 = reader1.nextLine();
                } else {
                    writer.writeLine(data2);
                    data2 = reader2.nextLine();
                }
            }
            //将剩余内容添加到文件尾
            while (data1 != null) {
                writer.writeLine(data1);
                data1 = reader1.nextLine();
            }
            while (data2 != null) {
                writer.writeLine(data2);
                data2 = reader2.nextLine();
            }

            writer.close();
            reader1.close();
            reader2.close();

            //删除上一次归并生成的文件
            new File(file1).delete();
            new File(file2).delete();
        }
        if (mTempFiles.size() % 2 != 0) {
            newTempFiles.add(mTempFiles.get(mTempFiles.size() - 1));
        }
        mTempFiles = newTempFiles;
    }

    private void split() {
        FileReader reader = new FileReader();
        reader.open(inFile.getAbsolutePath());


        String tempFileName = mParentDir + File.separatorChar + TEMP_FILE_PREFIX + nTempFileNum;
        FileWriter writer = new FileWriter();
        writer.open(tempFileName);
        mTempFiles.add(tempFileName);

        while (reader.hasNext()) {
            String line = reader.getLine();
            if (reader.getLineNumber() % nSubNum == 0) {
                writer.close();
                tempFileName = mParentDir + File.separatorChar + TEMP_FILE_PREFIX + (++nTempFileNum);
                writer.open(tempFileName);
                mTempFiles.add(tempFileName);
            }
            writer.writeLine(line);
        }
        writer.close();
        reader.close();
    }

    public static void main(String[] args) {
        MergeSort ms = new MergeSort(
                "E:\\Document\\Big Data\\test_sort_data.txt",
                "E:\\Document\\Big Data\\test_sort_data.txt_sorted.csv",
                10,
                String::compareTo);
        ms.sort();

    }
}
