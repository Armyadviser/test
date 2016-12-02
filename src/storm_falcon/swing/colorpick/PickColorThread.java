package storm_falcon.swing.colorpick;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Storm_Falcon on 2016/12/2.
 *
 */
public class PickColorThread extends Thread {

    private JTextField octField;
    private JTextField hexField;
    private JPanel panel;

    private boolean pause = false;

    public PickColorThread(JTextField octField, JTextField hexField, JPanel panel) {
        this.octField = octField;
        this.hexField = hexField;
        this.panel = panel;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (pause) continue;

            String text;
            Color c = pick();
            panel.setBackground(c);

            text = Integer.toHexString(c.getRGB());
            text = "#" + text.substring(2, text.length());
            hexField.setText(text);

            text = "r:" + c.getRed() + " g:" + c.getGreen() + " b:" + c.getBlue();
            octField.setText(text);
        }
    }

    public Color pick() {
        Color pixel = new Color(0, 0, 0);
        try {
            Robot robot = new Robot();
            Point mPoint = MouseInfo.getPointerInfo().getLocation();
            pixel = robot.getPixelColor(mPoint.x, mPoint.y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return pixel;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean getPause() {
        return pause;
    }
}
