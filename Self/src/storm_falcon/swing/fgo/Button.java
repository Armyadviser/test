package storm_falcon.swing.fgo;

import java.awt.*;
import java.awt.event.InputEvent;

public class Button {
    private String name;
    private double xRadius;
    private double yRadius;

    Button(String name, double x, double y) {
        this.name = name;
        xRadius = x;
        yRadius = y;
    }

    public void tap(Robot robot) throws InterruptedException {
        int x = (int) (xRadius * Screen.WINDOW_WIDTH + Screen.SOURCE_X);
        int y = (int) (yRadius * Screen.WINDOW_HEIGHT + Screen.SOURCE_Y);
        x = x + Screen.getRandomFloatPixel();
        y = y + Screen.getRandomFloatPixel();
        System.out.println(x + "\t" + y);
        robot = RobotManager.getInstance().getRobot();
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        Thread.sleep(500);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        Thread.sleep(500);
    }

    @Override
    public String toString() {
        return name;
    }
}
