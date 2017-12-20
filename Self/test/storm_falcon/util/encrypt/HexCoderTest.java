package storm_falcon.util.encrypt;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author gewp
 */
public class HexCoderTest {
    @Test
    public void decode() throws Exception {
        String s = "e6b58be8af95";
        s = HexCoder.decode(s);
        System.out.println(s);

        String url = "http://124.95.132.251:8080/orderSearch?adslId=";
        s = "ln_boss_gd_test_11&isShowCode=1";
        System.out.println(url + HexCoder.encode(s));
    }

    @Test
    public void encode() throws Exception {
        String s = "测试";
        s = HexCoder.encode(s);
        System.out.println(s);
    }

}