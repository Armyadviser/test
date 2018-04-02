package storm_falcon.swing.fgo.state;

public class WaitState extends State {
    @Override
    public void proceed() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
