package storm_falcon.lambdatest;

import java.util.function.Supplier;

/**
 * Created by Storm_Falcon on 2016/6/30.
 *
 */
public class Log {

	private Level level = Level.DEBUG;

	private enum Level {
		ERROR, DEBUG, WARNING
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public boolean isDebug() {
		return level == Level.DEBUG;
	}

	public void debug(String s) {
		System.out.println(s);
	}

	public void debug(Supplier<String> supplier) {
		if (isDebug()) {
			System.out.println(supplier.get());
		}
	}

	public static void main(String[] args) {
	    Log log = new Log();
		if (log.isDebug()) {
			log.debug("123");
		}

		log.debug(() -> "456");

	}
}
