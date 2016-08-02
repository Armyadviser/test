package storm_falcon.lambdatest.pattern.strategy;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Storm_Falcon on 2016/7/4.
 * 压缩文件的统一API
 */
@FunctionalInterface
public interface CompressionStrategy {
    OutputStream compress(OutputStream data) throws IOException;
}
