package storm_falcon.swing.fgo;

import net.sf.json.JSONArray;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FateGrandOrder {

    private Robot robot;

    private final static int WINDOW_WIDTH = 800;
    private final static int WINDOW_HEIGHT = 600;

    private List<List<ButtonManager.Button>> saoCaoZuo;

    private FateGrandOrder() {
        String ops = null;
        try {
            robot = new Robot();
            byte[] data = new byte[1024];
            new FileInputStream(getClass()
                    .getResource("script.json").getPath())
                .read(data);
            ops = new String(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        saoCaoZuo = Arrays.stream(JSONArray.fromObject(ops).toArray())
                .map(array ->
                    Arrays.stream(JSONArray.fromObject(array).toArray())
                            .map(String::valueOf)
                            .map(ButtonManager::getButton)
                            .collect(Collectors.toList())
                 )
                .collect(Collectors.toList());
    }

    private void run() {
        saoCaoZuo.forEach(round -> {
            round.forEach(this::tap);
            sleep(10 * 1000);
        });
    }

    private void tap(ButtonManager.Button button) {
        int x = (int) (button.getXRadius() * WINDOW_WIDTH);
        int y = (int) (button.getYRadius() * WINDOW_HEIGHT);
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        System.out.println(x + "\t" + y);
        sleep(2000);
    }

    private void sleep(long n) {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FateGrandOrder().run();
    }
}
