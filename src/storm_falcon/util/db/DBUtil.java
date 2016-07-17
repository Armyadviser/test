package storm_falcon.util.db;

import storm_falcon.util.file.FileReader;

import java.sql.*;
import java.util.*;

/**
 * Created by Storm_Falcon on 2016/1/27.
 *
 */
public class DBUtil {

    private Connection mConn = null;
    private ConnectionPool mPool = null;
    private ResultSet rs = null;
    private PreparedStatement stmt = null;

    private void prepare(String sql, Object... args) {
        mPool = ConnectionPool.getInstance();
        mConn = mPool.getConnection();
        try {
            stmt = mConn.prepareStatement(sql);
            int i = 1;
            if (args != null) {
                for (Object obj : args) {
                    stmt.setObject(i++, obj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	/**
     * ResultSet -> List<Map<String, Object>>
     * @return
     */
    private List<Map<String, Object>> parseResultSet() {
        try {
            List<Map<String, Object>> list = new ArrayList<>();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String label = metaData.getColumnLabel(i);
                    Object obj = rs.getObject(i);
                    map.put(label, obj);
                }
                list.add(map);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void freeResource() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	/**
     *
     * @param sql
     * @param args
     * @return
     */
    public int update(String sql, Object... args) {
        prepare(sql, args);
        try {
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            freeResource();
        }
        return 0;
    }

    public List<Map<String, Object>> select(String sql, Object... args) {
        prepare(sql, args);
        try {
            rs = stmt.executeQuery();
            return parseResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            freeResource();
        }
        return null;
    }

    public int[] batchQuery(String sql, List<Object[]> args) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            for (Object[] items : args) {
                int i = 1;
                for (Object arg : items) {
                	stmt.setObject(i++, arg);
                }
                stmt.addBatch();
            }

            return stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                pool.freeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new int[] {0};
    }

    private void close() {
        mPool.freeConnection(mConn);
    }

    public static void main(String[] args) {
        DBUtil action = new DBUtil();
        int column = action.update("update user set id=1");
        System.out.println(column);

        List<?> list = action.select("select id,name,pwd,delsign from user");
        System.out.println(list);

        List<Object[]> values = new ArrayList<>();
        String filePath = "E:\\Document\\Big Data\\Test_IMSI_all.csv";
        FileReader.forEach(filePath, line -> values.add(new Object[] {line}));

        int[] result = action.batchQuery("insert into news (title) values (?)", values);
        System.out.println(Arrays.toString(result));
        String s = "{\"no\":\"002\", \"name\":\"asd\", \"tel\":\"1234\", \"date\":\"2016-07-06\", \"credit\":11.0, \"point\":0, \"level\":2}";
    }
}
