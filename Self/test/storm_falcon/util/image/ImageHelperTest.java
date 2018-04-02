package storm_falcon.util.image;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImageHelperTest {

    @Test
    public void snapshot() {
        String name = ImageHelper.snapshot("D:\\test", 100, 200, 300, 400);
        System.out.println(name);
    }

    @Test
    public void isBlack() {
        boolean black = ImageHelper.isBlack("D:/test/QQ截图20180330160305.png");
        System.out.println(black);
    }
}