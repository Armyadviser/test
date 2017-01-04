package storm_falcon.lambdatest.pattern.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Storm_Falcon on 2016/7/4.
 * 压缩器，供客户端调用
 * 实例化时提供压缩策略
 */
public class Compressor {
    private final CompressionStrategy strategy;

    public Compressor(CompressionStrategy strategy) {
        this.strategy = strategy;
    }

    public void compress(Path inFile, File outFile) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(outFile)) {
            Files.copy(inFile, strategy.compress(outputStream));
        }
    }
}
