package storm_falcon.java9;

public class Others {

    void varName() {
        // this will error.
//        String _ = "";
    }

    void string() {
        // String 内部数据结构由char[]变成了byte[]
        // 节省空间，提高性能
    }
}
