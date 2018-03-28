package storm_falcon.swing.fgo;

import java.util.Random;

class Screen {

    // 实际游戏窗口大小
    static final int WINDOW_WIDTH = 1024;
    static final int WINDOW_HEIGHT = 576;

    // 实际游戏窗口左上角
    static final int SOURCE_X = 30;
    static final int SOURCE_Y = 80;

    // 截图参照大小
    static final double BASE_WIDTH = 1920;
    static final double BASE_HEIGHT = 1080;

    private static final int RANDOM_FLOAT_RANGE = 10;

    private static final Random random = new Random(27);

    static int getRandomFloatPixel() {
        return random.nextInt(RANDOM_FLOAT_RANGE);
    }
}
