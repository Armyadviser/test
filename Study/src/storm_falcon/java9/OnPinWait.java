package storm_falcon.java9;

/**
 * @author gewp
 */
public class OnPinWait {

    static volatile boolean notify = false;

    public static void main(String[] args) throws Exception {
//        JsonParser parser = Json.createParser("[]");

        new Thread(() -> {
            notify = false;
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notify = true;
        }).start();

        for (int i = 0; i < 100; i++) {
            System.out.println(i);

            while (i == 50) {
                if (notify) {
                    break;
                }
                Thread.onSpinWait();
            }

        }

    }
}
