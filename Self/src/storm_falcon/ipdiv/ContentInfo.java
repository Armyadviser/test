package storm_falcon.ipdiv;

/**
 * Created by Storm_Falcon on 2016/11/17.
 *
 */
public class ContentInfo {
	public String content;
	public String ip;
	public String city;

	/**
	 * @param line ip,info1,info2
	 * @param num 0
	 * @param split ,
	 * @return
	 */
	public static ContentInfo parse(String line, int num, String split) {
		String[] item = line.split(split);
		if (item.length < num + 1) {
			return null;
		}

		ContentInfo info = new ContentInfo();
		info.ip = item[num];
		info.content = line;
		return info;
	}

	public String toString() {
		return city + "," + content;
	}
}
