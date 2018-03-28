package storm_falcon.swing.fgo;

import net.sf.json.JSONArray;
import storm_falcon.swing.fgo.round.Round;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FateGrandOrder {

    private List<Round> saoCaoZuo;

    private FateGrandOrder() throws IOException {
        byte[] data = new byte[1024];
        new FileInputStream(getClass()
            .getResource("script.json").getPath())
            .read(data);
        String ops = new String(data);

        saoCaoZuo = Arrays.stream(JSONArray.fromObject(ops).toArray())
                .map(JSONArray::fromObject)
                .map(Round::new)
                .collect(Collectors.toList());
    }

    private void run() {
        saoCaoZuo.forEach(Round::proceed);
    }

    public static void main(String[] args) throws Exception {
        Thread.sleep(1000);
        new FateGrandOrder().run();
    }
}
