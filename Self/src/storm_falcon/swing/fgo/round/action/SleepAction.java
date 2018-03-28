package storm_falcon.swing.fgo.round.action;

public class SleepAction extends Action {

    private long n;

    public SleepAction(long n) {
        this.n = n;
    }

    @Override
    public void proceed() {
        System.out.println("sleep" + n);
        try {
            Thread.sleep(n * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "SleepAction{" +
                "n=" + n +
                '}';
    }
}
