package com.ge.scanner.radius;

import com.ge.scanner.radius.impl.CoaFactory;
import org.junit.Test;

/**
 * Created by Storm_Falcon on 2016/11/10.
 *
 */
public class CoaFactoryTest {
	@Test
	public void getCoaRequest() throws Exception {
		CoaFactory factory = CoaFactory.getInstance();
		factory.getCoaRequest("2352");
	}

}