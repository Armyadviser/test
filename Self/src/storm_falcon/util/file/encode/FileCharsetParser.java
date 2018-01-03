package storm_falcon.util.file.encode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author gewp
 */
public class FileCharsetParser {

    public static String getEncode(String filePath) {
        try {
            return getEncode(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "GBK";
    }

    public static String getEncode(File file) {
        try {
            return getEncode(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "GBK";
    }

    public static String getEncode(FileInputStream in) {
        String defaultCharset = Charset.defaultCharset().name();
        UnicodeInputStream uin = new UnicodeInputStream(in, defaultCharset);
        if ("UTF-8".equals(uin.getEncoding())) {
            uin.close();
            return "UTF-8";
        }

        byte[] head = new byte[3];
        try {
            in.read(head);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String code = "GBK";
        if (head[0] == -1 && head[1] == -2 )
            code = "UTF-16";
        if (head[0] == -2 && head[1] == -1 )
            code = "Unicode";

        //带BOM
        if(head[0]==-17 && head[1]==-69 && head[2] ==-65)
            code = "UTF-8";
        if("Unicode".equals(code)){
            code = "UTF-16";
        }
        return code;
    }
}
