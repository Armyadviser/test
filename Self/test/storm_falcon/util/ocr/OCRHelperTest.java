package storm_falcon.util.ocr;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class OCRHelperTest {

    @Test
    public void recognizeText() {
        String s = OCRHelper.recognizeText(new File("d:/test/1522381670964.jpg"));
        System.out.println(s);
    }
}