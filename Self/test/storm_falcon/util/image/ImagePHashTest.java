package storm_falcon.util.image;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by falcon on 17-2-16.
 *
 */
public class ImagePHashTest {
    @Test
    public void test() throws Exception {
        String file1 = "E:/MyPictures/Pվ/31287512_p0.jpg";
        String file2 = "E:/MyPictures/Pվ/40639484_p0.jpg";
        String file3 = "E:/MyPictures/QQͼƬ20151105190535.jpg";

        ImagePHash p = new ImagePHash();
        String image1 = p.getHash(new FileInputStream(new File(file1)));
        String image2 = p.getHash(new FileInputStream(new File(file2)));
        String image3 = p.getHash(new FileInputStream(new File(file3)));

        System.out.println(image1);
        System.out.println(image2);
        System.out.println(image3);

        System.out.println(p.distance(image1, image2));
        System.out.println(p.distance(image1, image3));
    }
}
