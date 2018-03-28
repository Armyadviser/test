package storm_falcon.swing.fgo.round.action;

import java.awt.*;

public abstract class Action {

    Robot robot;

    Action() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public abstract void proceed();

}
