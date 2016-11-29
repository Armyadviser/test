package storm_falcon;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Storm_Falcon on 2016/8/18.
 *
 */
public class JToolsTest {
	@Test
	public void MD5() throws Exception {
		String s = JTools.MD5("qwert");
		assertEquals("bnm", s);
	}

	@Test
	public void ip2Bytes() throws Exception {
		String ip = "192.168.1.1";
		byte[] data = JTools.ip2Bytes(ip);
		System.out.println(Arrays.toString(data));
	}

	@Test
	public void bytesToHexString() throws Exception {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		LocalDateTime ldt = LocalDateTime.parse("2016-09-13 16:04:59.227", dtf);
		System.out.println(ldt.format(dtf));

	}

}