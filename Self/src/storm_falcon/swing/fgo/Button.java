package storm_falcon.swing.fgo;

import java.awt.*;
import java.awt.event.InputEvent;

public class Button {
    private String name;
    private int x;
    private int y;

    private int width;
    private int height;

    Button(String name, int x, int y, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void tap(Robot robot) throws InterruptedException {
        int tapX = (int) (x * Screen.HORIZONTAL_RATIO + Screen.getRandomFloatPixel(width));
        int tapY = (int) (y * Screen.VERTICAL_RATIO + Screen.getRandomFloatPixel(height));
        System.out.println(x + "\t" + y);
        robot.mouseMove(tapX, tapY);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        Thread.sleep(50);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        Thread.sleep(50);
    }

    @Override
    public String toString() {
        return name;
    }
}
