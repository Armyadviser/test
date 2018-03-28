package storm_falcon.swing.fgo.round.action;

import storm_falcon.swing.fgo.Button;

public class ButtonAction extends Action {

    private Button button;

    private long delay = 2 * 1000;

    public ButtonAction(Button button) {
        this.button = button;
    }

    @Override
    public void proceed() {
        try {
            button.tap(robot);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "ButtonAction{" +
                "button=" + button +
                ", delay=" + delay +
                '}';
    }
}
