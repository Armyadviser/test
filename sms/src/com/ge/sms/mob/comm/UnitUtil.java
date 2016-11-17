package com.ge.sms.mob.comm;

import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by Storm_Falcon on 2016/11/2.
 *
 */
abstract class UnitUtil implements OrderUtil {

	/** 生产地址 */
	public static String URL = "http://132.194.41.1:8082/infserver/httpServer";

	/** 测试地址 */
	public static String URL_TEST = "http://132.194.41.2:8083/lnopserver/httpServer";

	protected static final Random RANDOM = new Random();

	/** 是否为测试模式 */
	protected boolean test = false;

	/** 是否为调试模式 */
	protected boolean debug = false;

	/** 用于日志记录，仅在调试模式下有效 */
	protected BiConsumer<String, String> logger;

	/** socket 超时时间，ms */
	protected int timeout = 5 * 1000;

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public void setLogger(BiConsumer<String, String> logger) {
		this.logger = logger;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public void setTimeout(int millisecond) {
		this.timeout = millisecond;
	}

	@Override
	public String getUrl() {
		return test ? URL_TEST : URL;
	}
}
