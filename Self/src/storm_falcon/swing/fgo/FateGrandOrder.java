package storm_falcon.swing.fgo;

import storm_falcon.swing.fgo.state.State;
import storm_falcon.swing.fgo.state.SupportSelectState;
import storm_falcon.swing.fgo.state.TeamConfirmState;
import storm_falcon.swing.fgo.state.WaitState;
import storm_falcon.util.image.ImageHelper;
import storm_falcon.util.ocr.OCRHelper;

public class FateGrandOrder {

    private void run() {
        while (true) {
            String currentPage = ImageHelper.snapshot(
                    "D:/test/", Screen.SOURCE_X, Screen.SOURCE_Y,
                    Screen.WINDOW_WIDTH, Screen.WINDOW_HEIGHT);

            State state = getState(currentPage);
            state.proceed();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private State getState(String filePath) {
        try {
            if (ImageHelper.isBlack(filePath)) {
                return new WaitState();
            }

            String subImage = ImageHelper.subImage(filePath, Screen.START_RECT.getRectangle());
            String text = OCRHelper.recognizeText(subImage);
            if (text.contains("开始任务")) {
                return new TeamConfirmState();
            }

            subImage =  ImageHelper.subImage(filePath, Screen.SUPPORT_RECT.getRectangle());
            text = OCRHelper.recognizeText(subImage);
            if (text.contains("助战选择")) {
                return new SupportSelectState();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new WaitState();
    }

    public static void main(String[] args) throws Exception {
        Thread.sleep(1000);
        new FateGrandOrder().run();
    }
}
