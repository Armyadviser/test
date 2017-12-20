package storm_falcon.util.db.queue;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.internal.OracleTypes;

public class AdvancedQueueOperator {

	private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@218.25.253.70:1521:gebssdb";
	private String usr = "test";
	private String pwd = "test";
	
	private Connection conn = null;
	private CallableStatement inputCs = null;
	private CallableStatement outputCs = null;
	private CallableStatement outputCsByGiven = null;
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public AdvancedQueueOperator() {
		try {
			conn = DriverManager.getConnection(url, usr, pwd);
			inputCs = conn.prepareCall("{call inqueue(?, ?)}");
			outputCs = conn.prepareCall("{call outqueue(?, ?)}");
			outputCsByGiven = conn.prepareCall("{call outqueuebyip(?, ?, ?)}");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean createQueue(String tableName, String queueName) {
		try {
			CallableStatement cs = conn.prepareCall("{call createqueue(?, ?)}");
			cs.setString(1, tableName);
			cs.setString(2, queueName);
			cs.registerOutParameter(1, OracleTypes.VARCHAR);
			cs.registerOutParameter(2, OracleTypes.VARCHAR);
			return cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean dropQueue(String tableName, String queueName) {
		return false;
	}
	
	public boolean addLast(String ip, String queueName) {
		try {
			inputCs.setString(1, ip);
			inputCs.setString(2, queueName);
			inputCs.registerOutParameter(1, OracleTypes.VARCHAR);
			inputCs.registerOutParameter(2, OracleTypes.VARCHAR);
			inputCs.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String removeFirst(String queueName) {
		try {
			outputCs.setString(1, "");
			outputCs.setString(2, queueName);
			outputCs.registerOutParameter(1, OracleTypes.VARCHAR);
			outputCs.registerOutParameter(2, OracleTypes.VARCHAR);
			outputCs.execute();
			String res = outputCs.getString(1);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public String removeItem(String queueName, String ip) {
		try {
			outputCsByGiven.setString(1, "");
			outputCsByGiven.setString(2, queueName);
			outputCsByGiven.setString(3, ip);
			outputCsByGiven.registerOutParameter(1, OracleTypes.VARCHAR);
			outputCsByGiven.registerOutParameter(2, OracleTypes.VARCHAR);
			outputCsByGiven.registerOutParameter(3, OracleTypes.VARCHAR);
			outputCsByGiven.execute();
			String res = outputCsByGiven.getString(1);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public void close() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (inputCs != null) {
				inputCs.close();
			}
			if (outputCs != null) {
				outputCs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		dbqueue.AdvancedQueueOperator conn = new dbqueue.AdvancedQueueOperator();
//		conn.createQueue("queue3", "queue3");
//		conn.addLast("127.0.0.2", "testqueue03");
//		String ip = conn.removeFirst("testqueue03");
//		String ip = conn.removeItem("testqueue03", "127.0.0.2");
//		System.out.println(ip);
//		conn.close();
	}
}
