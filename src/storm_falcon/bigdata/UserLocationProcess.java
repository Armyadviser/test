package storm_falcon.bigdata;

import storm_falcon.util.file.FileReader;
import storm_falcon.util.file.FileWriter;
import storm_falcon.util.string.StringHelper;

public class UserLocationProcess {

	private FileWriter mWriter = new FileWriter();
	
	private StringBuilder sbException = new StringBuilder();
	
	public void process() throws Exception {
		FileReader reader = new FileReader();
		String mInFile = "E:\\Document\\Big Data\\shujudasai_1.csv";
		reader.open(mInFile);
		
		mWriter.open("E:\\Document\\Big Data\\out.txt");
		
		int nTotalCount = 0;
		int nDayCount = 0;
		while (reader.hasNext()) {
			if (++nTotalCount % 10000 == 0) {
				System.out.println("Process:" + nTotalCount);
			}
			
			UserData ud = new UserData();
			//每个用户mDayNum条数据
			int mDayNum = 11;
			while (nDayCount++ < mDayNum) {
				String line = reader.nextLine();
				if (line == null) {
					break;
				}
				String[] item = StringHelper.split(line, ",");
				if (item.length != 50) {
					sbException.append(line).append("\t").append(item.length).append("\r\n");
					break;
				}
				
				if (!item[1].equals(ud.id)) {
					TreatedData td = TreatedData.parseUserData(ud);
					writeUserToFile(td);
					ud = new UserData();
					ud.id = item[1];
				}
				
				loadLine(ud, item);
			}
			nDayCount = 0;
		}
		
		reader.close();
		mWriter.close();
		System.out.println(sbException.toString());
	}
	
	private void writeUserToFile(TreatedData td) {
		if (td == null) {
			return;
		}
		mWriter.writeLine(td.toString());
	}

	/**
	 * 解析一行
	 */
	private void loadLine(UserData ud, String[] item) {
		for (int i = 2; i <= 48; i += 2) {
			if (item[i].length() == 0 || item[i + 1].length() == 0) {
				continue;
			}
			ud.addTimeLocation(
					item[0], 
					i / 2,
					Double.parseDouble(item[i]),
					Double.parseDouble(item[i + 1])
				);
		}
	}
	
	public static void main(String[] args) throws Exception {
		new UserLocationProcess().process();
	}
}
