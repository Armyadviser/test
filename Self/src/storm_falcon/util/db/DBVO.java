package storm_falcon.util.db;

/**
 * Created by Storm_Falcon on 2016/1/29.
 *
 */
@SuppressWarnings("CanBeFinal")
class DBVO {
    public String name;
    public String driver;
    public String url;
    public String usr;
    public String pwd;
    public int minConn;
    public int maxConn;
    public int waitDelay;

    public String toString() {
        return "name------>" + name + "\r\n" +
            "driver---->" + driver + "\r\n" +
            "url------->" + url + "\r\n" +
            "usr------->" + usr + "\r\n" +
            "pwd------->" + pwd + "\r\n" +
            "minConn--->" + minConn + "\r\n" +
            "maxConn--->" + maxConn + "\r\n" +
            "waitDelay->" + waitDelay + "\r\n";
    }
}
