package storm_falcon.test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {
    public static void main(String[] args) throws Exception {
        FileWriter out = new FileWriter("D:\\IdeaProjects\\test\\WebDemo\\web\\data\\base_station.json");

        Files.lines(Paths.get("D:\\IdeaProjects\\test\\WebDemo\\web\\data\\1.txt"))
                .map(line -> line.split(","))
                .map(items -> "{\"id\":\"" + items[0] + "\",\"lon\":" + items[1] + ",\"lat\":" + items[2] + "},")
                .forEach(line -> {
                    try {
                        out.write(line + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        out.close();
    }
}
