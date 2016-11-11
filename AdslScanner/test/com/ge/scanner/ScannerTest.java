package com.ge.scanner;

import com.ge.scanner.vo.CoaInfo;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Storm_Falcon on 2016/11/10.
 *
 */
public class ScannerTest {
	@Test
	public void getAccountList() throws Exception {

	}

	@Test
	public void getAccountByPoid() throws Exception {

	}

	@Test
	public void getSessionsByAccount() throws Exception {

	}

	@Test
	public void getCoaInfoBySession() throws Exception {

	}

	@Test
	public void getCoaInfos() throws Exception {
		long n1 = System.currentTimeMillis();

		Scanner scanner = new Scanner(null);
		List<CoaInfo> coaInfos = scanner.getCoaInfos();
		System.out.println("Coa size:" + coaInfos.size());

		long n2 = System.currentTimeMillis();

		System.out.println("time:" + (n2 - n1) + "ms");
	}

}