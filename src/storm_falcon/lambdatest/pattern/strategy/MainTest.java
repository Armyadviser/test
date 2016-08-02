package storm_falcon.lambdatest.pattern.strategy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Storm_Falcon on 2016/7/4.
 *
 */
public class MainTest {
    public static void main(String[] args) throws IOException {
        Path inFile = Paths.get("D:\\test\\Test.dat");
        File outFile1 = new File("D:\\test\\Test1.gz");
        File outFile2 = new File("D:\\test\\Test2.zip");

        Compressor compressor = new Compressor(new GzipCompressionStrategy());
        compressor.compress(inFile, outFile1);

        Compressor compressor1 = new Compressor(new ZipCompressionStrategy());
        compressor1.compress(inFile, outFile2);

        //使用Lambda表达式
        compressor = new Compressor(GZIPOutputStream::new);
        compressor.compress(inFile, outFile1);
        //可省去两个具体的压缩策略实现类
    }
}
