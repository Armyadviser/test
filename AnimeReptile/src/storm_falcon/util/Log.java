package storm_falcon.util;

public final class Log {

	public static void debug(String tag, String info) {
		System.out.println(tag + "\t" + info);
	}
	
	public static void debug(String info) {
		System.out.println(info);
	}
	
	public static void err(String info) {
		System.err.println(info);
	}
	
	public static void err(String tag, String info) {
		System.err.println(tag + "\t" + info);
	}
}
