package statistics;

import com.ge.scanner.conn.cm.CmUtils;
import com.ge.scanner.conn.crm.CrmModule;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Created by Storm_Falcon on 2016/12/5.
 *
 */
public class CrmProxyLog {
	public static void main(String[] args) throws Exception {
		System.setProperty("user.dir", "E:\\Code\\IDEAworkspace\\test\\AdslScanner");

	    String path = "C:\\Users\\Storm_Falcon\\Desktop\\";
//	    String fileName1 = "辽阳公司增加集客线需要推送数据 (1).txt";
//	    String fileName2 = "辽阳公司增加集客线需要推送数据 (done).txt";
		String fileName1 = "辽阳宽带到期信息(12.1-12.31).txt";
		String fileName2 = "辽阳宽带到期信息(12.1-12.31)(done).txt";

	    FileWriter writer = new FileWriter();
	    writer.open(path + fileName2);

		Files.lines(Paths.get(path + fileName1))
			.map(CmUtils::getAccountByLogin)
			.filter(Objects::nonNull)
			.map(account -> {
				String res = CrmModule.queryCrm(account);
				return account.login + ";" + res;
			}).peek(System.out::println)
			.forEach(writer::writeLine);

		writer.close();
	}
}
