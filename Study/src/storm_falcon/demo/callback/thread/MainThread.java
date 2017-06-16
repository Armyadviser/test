package storm_falcon.demo.callback.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author gewp
 */
public class MainThread extends Thread {

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public void run() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyyMMdd HHmmss]");

        while (true) {
            try {
                String time = formatter.format(LocalDateTime.now());
                System.out.println(time + ", " + count);

                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
