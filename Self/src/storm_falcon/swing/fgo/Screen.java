package storm_falcon.swing.fgo;

import java.util.Random;

public class Screen {

    // 实际游戏窗口大小
    static final int WINDOW_WIDTH = 1024;
    static final int WINDOW_HEIGHT = 576;

    // 实际游戏窗口左上角
    static final int SOURCE_X = 0;
    static final int SOURCE_Y = 40;

    // 截图参照大小
    private static final double BASE_WIDTH = 1920;
    private static final double BASE_HEIGHT = 1080;

    static final double HORIZONTAL_RATIO = WINDOW_WIDTH / BASE_WIDTH;
    static final double VERTICAL_RATIO = WINDOW_HEIGHT / BASE_HEIGHT;

    private static final Random random = new Random(27);

    /**队伍确认，开始任务按钮*/
    static final ScaleRectangle START_RECT = new ScaleRectangle(1660, 960, 230, 100);

    /**助战选择，右上角文字*/
    static final ScaleRectangle SUPPORT_RECT = new ScaleRectangle(1550, 0, 370, 130);

    /**回合数*/
    public static final ScaleRectangle ROUND_RECT = new ScaleRectangle(1250, 120, 130, 60);

    static int getRandomFloatPixel(int range) {
        return random.nextInt(range);
    }
}
