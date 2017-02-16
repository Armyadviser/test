package storm_falcon.util.db;

import storm_falcon.util.myxml.XmlHelper;

import java.io.Closeable;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Storm_Falcon on 2016/1/28.
 *
 */
class ConnectionPool implements Closeable {

    private final LinkedList<Connection> mConnList = new LinkedList<>();

    private String driver = null;
    private String url = null;
    private String usr = null;
    private String pwd = null;
    private int nMinConn = 0;
    private int nMaxConn = 0;
    private int nWaitDelay = 0;

    /**
     * 正在使用的连接数量
     */
    private int nUsage = 0;

    private ConnectionPool() {
        init();

        //加载驱动
        try {
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //连接初始化
        for (int i = 0; i < nMinConn; i++) {
            mConnList.addLast(newConn());
        }
    }

    private void init() {
		String fileName = System.getProperty("user.dir") + File.separator + "DBConfig.xml";
		System.out.println("Load:" + fileName);
        XmlHelper xmlHelper = new XmlHelper();
        xmlHelper.parse(fileName);

		driver = xmlHelper.get("Driver");
		url = xmlHelper.get("URL");
		usr = xmlHelper.get("Username");
		pwd = xmlHelper.get("Password");
		nMinConn = Integer.parseInt(xmlHelper.get("MinConn"));
		nMaxConn = Integer.parseInt(xmlHelper.get("MaxConn"));
		nWaitDelay = Integer.parseInt(xmlHelper.get("MaxWaitDelay"));

		System.out.println(this);
	}

    private Connection newConn() {
        try {
            return DriverManager.getConnection(url, usr, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ConnectionPool mInstance = null;
    public static synchronized ConnectionPool getInstance() {
        if (mInstance == null) {
            mInstance = new ConnectionPool();
        }
        return mInstance;
    }

    /**
     * 获取连接
     * @return
     */
    public Connection getConnection() {
        Connection conn;
        synchronized (mConnList) {
            if (mConnList.size() == 0) {
                if (nUsage < nMaxConn) {
                    mConnList.add(newConn());
                }
            } else {
                try {
                    mConnList.wait(nWaitDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            conn = mConnList.removeFirst();
            nUsage++;
        }
        return conn;
    }

    /**
     * 释放连接
     * @param conn
     */
    public void freeConnection(Connection conn) {
        synchronized (mConnList) {
            mConnList.addLast(conn);
            nUsage--;
            mConnList.notify();
        }
    }

	public void close() {
		synchronized (mConnList) {
			for (Connection conn : mConnList) {
				try {
					conn.close();
				} catch (SQLException ignored) {}
			}
			mConnList.clear();
		}
		nUsage = 0;
	}

}
