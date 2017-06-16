package storm_falcon.demo.io;

import java.io.*;

/**
 * @author gewp
 */
public class MyFileReader {

    private BufferedReader reader;

    public boolean open(String filePath) {
        try {
            reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filePath)));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
