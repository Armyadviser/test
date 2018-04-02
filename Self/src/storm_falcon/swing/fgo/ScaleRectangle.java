package storm_falcon.swing.fgo;

import java.awt.*;

public class ScaleRectangle {

    private Rectangle rectangle;

    ScaleRectangle(int x, int y, int width, int height) {
        rectangle = new Rectangle(
                (int) (Screen.SOURCE_X + x * Screen.HORIZONTAL_RATIO),
                (int) (Screen.SOURCE_Y + y * Screen.VERTICAL_RATIO),
                (int) (width * Screen.HORIZONTAL_RATIO),
                (int) (height * Screen.VERTICAL_RATIO));
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
