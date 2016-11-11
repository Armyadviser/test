package com.ge.scanner.radius.impl;

import com.ge.scanner.Scanner;
import com.ge.scanner.config.ScannerConfig;
import com.ge.scanner.radius.CoaRequest;

import java.io.InputStream;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by Storm_Falcon on 2016/11/10.
 *
 */
public class CoaFactory {

	private Map<String, CoaRequest> instanceMap;

	private CoaFactory() {
		instanceMap = new ConcurrentHashMap<>();
	}

	private CoaRequest newInstance(String className) {
		try {
			Class<?> cls = Class.forName(className);
			return (CoaRequest) cls.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static CoaFactory instance;
	public static synchronized CoaFactory getInstance() {
		if (instance == null) {
			instance = new CoaFactory();
		}
		return instance;
	}

	public CoaRequest getCoaRequest(String vendorId) {
		CoaRequest inst = instanceMap.get(vendorId);
		if (inst == null) {
			ScannerConfig config = ScannerConfig.getInstance();
			String coaClassName = config.getCoaClassName(vendorId);
			inst = newInstance(coaClassName);
			if (inst == null) {
				return null;
			}
			instanceMap.put(vendorId, inst);
		}
		return inst;
	}
}