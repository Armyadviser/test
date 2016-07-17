package storm_falcon.util.db;

/**
 * Created by Storm_Falcon on 2016/1/29.
 *
 */
class DBVO {
    public String name = null;
    public String driver = null;
    public String url = null;
    public String usr = null;
    public String pwd = null;
    public int minConn = 0;
    public int maxConn = 0;
    public int waitDelay = 0;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name------>").append(name).append("\r\n")
                .append("driver---->").append(driver).append("\r\n")
                .append("url------->").append(url).append("\r\n")
                .append("usr------->").append(usr).append("\r\n")
                .append("pwd------->").append(pwd).append("\r\n")
                .append("minConn--->").append(minConn).append("\r\n")
                .append("maxConn--->").append(maxConn).append("\r\n")
                .append("waitDelay->").append(waitDelay).append("\r\n");
        return sb.toString();
    }
}
