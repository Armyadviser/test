package storm_falcon.java9;

import java.io.InputStreamReader;

public class TryWithResource {
    public static void main(String[] args) throws Exception {
        InputStreamReader reader = new InputStreamReader(System.in);
        try (reader) {
            int read = reader.read();
            System.out.println((char) read);
        }
    }
}
