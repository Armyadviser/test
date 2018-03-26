package storm_falcon.swing.fgo;

import java.awt.*;
import java.awt.event.InputEvent;

public class FateGrandOrder {

    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        robot.mouseMove(100, 100);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);

        robot.mouseMove(700, 600);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}
