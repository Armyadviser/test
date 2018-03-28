package storm_falcon.swing.fgo;

import java.awt.*;

public class RobotManager {

    private Robot robot;

    private RobotManager() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private static RobotManager instance;

    public static RobotManager getInstance() {
        if (instance == null) {
            synchronized (RobotManager.class) {
                if (instance == null) {
                    instance = new RobotManager();
                }
            }
        }
        return instance;
    }

    Robot getRobot() {
        return robot;
    }
}
