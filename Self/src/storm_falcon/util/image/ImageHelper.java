package storm_falcon.util.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHelper {

    /**
     * 图片二值化
     * 新图片以原文件命名
     * @param srcFile 原图片全路径
     * @param destPath 新图片保存路径
     */
    public static void binary(File srcFile, String destPath)
            throws IOException {
        File destF = new File(destPath);
        if (!destF.exists()) {
            destF.mkdirs();
        }

        BufferedImage bufferedImage = ImageIO.read(srcFile);
        int h = bufferedImage.getHeight();
        int w = bufferedImage.getWidth();

        // 灰度化
        int[][] gray = new int[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int argb = bufferedImage.getRGB(x, y);
                // 图像加亮（调整亮度识别率非常高）
                int r = (int) (((argb >> 16) & 0xFF) * 1.1 + 30);
                int g = (int) (((argb >> 8) & 0xFF) * 1.1 + 30);
                int b = (int) (((argb) & 0xFF) * 1.1 + 30);
                if (r >= 255) {
                    r = 255;
                }
                if (g >= 255) {
                    g = 255;
                }
                if (b >= 255) {
                    b = 255;
                }
                gray[x][y] = (int) Math
                        .pow((Math.pow(r, 2.2) * 0.2973 + Math.pow(g, 2.2)
                                * 0.6274 + Math.pow(b, 2.2) * 0.0753), 1 / 2.2);
            }
        }

        // 二值化
        int threshold = ostu(gray, w, h);
        BufferedImage binaryBufferedImage = new BufferedImage(w, h,
                BufferedImage.TYPE_BYTE_BINARY);
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                if (gray[x][y] > threshold) {
                    gray[x][y] |= 0x00FFFF;
                } else {
                    gray[x][y] &= 0xFF0000;
                }
                binaryBufferedImage.setRGB(x, y, gray[x][y]);
            }
        }

        // 矩阵打印
//        for (int y = 0; y < h; y++) {
//            for (int x = 0; x < w; x++) {
//                if (isBlack(binaryBufferedImage.getRGB(x, y))) {
//                    System.out.print("*");
//                } else {
//                    System.out.print(" ");
//                }
//            }
//            System.out.println();
//        }

        ImageIO.write(binaryBufferedImage, "jpg", new File(destPath, srcFile
                .getName()));
    }

//    private static boolean isBlack(int colorInt) {
//        Color color = new Color(colorInt);
//        return color.getRed() + color.getGreen() + color.getBlue() <= 300;
//    }

    private static int ostu(int[][] gray, int w, int h) {
        int[] histData = new int[w * h];
        // Calculate histogram
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int red = 0xFF & gray[x][y];
                histData[red]++;
            }
        }

        // Total number of pixels
        int total = w * h;

        float sum = 0;
        for (int t = 0; t < 256; t++)
            sum += t * histData[t];

        float sumB = 0;
        int wB = 0;
        int wF;

        float varMax = 0;
        int threshold = 0;

        for (int t = 0; t < 256; t++) {
            wB += histData[t]; // Weight Background
            if (wB == 0)
                continue;

            wF = total - wB; // Weight Foreground
            if (wF == 0)
                break;

            sumB += (float) (t * histData[t]);

            float mB = sumB / wB; // Mean Background
            float mF = (sum - sumB) / wF; // Mean Foreground

            // Calculate Between Class Variance
            float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);

            // Check if new maximum found
            if (varBetween > varMax) {
                varMax = varBetween;
                threshold = t;
            }
        }

        return threshold;
    }

    /**
     * 屏幕截图
     * @param savePath 保存路径
     * @param x 起始点X
     * @param y 起始点Y
     * @param width 长
     * @param height 宽
     * @return 文件名
     */
    public static String snapshot(String savePath, int x, int y, int width, int height) {
        try {
            if (!savePath.endsWith("\\") && !savePath.endsWith("/")) {
                savePath += "\\";
            }
            Rectangle rectangle = new Rectangle(x, y, width, height);
            BufferedImage img = new Robot().createScreenCapture(rectangle);
            String name = savePath + System.currentTimeMillis() + ".jpg";

            ImageIO.write(img, "jpg", new File(name));
            return name;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int getRed(int color) {
        return (color >> 16) & 0xFF;
    }

    private static int getGreen(int color) {
        return (color >> 8) & 0xFF;
    }

    private static int getBlue(int color) {
        return color & 0xFF;
    }

    /**
     * 判断一张图片是否为80%黑色
     * @return true 80%以上是黑色
     */
    public static boolean isBlack(String imgPath) {
        try {
            BufferedImage image = ImageIO.read(new File(imgPath));
            int w = image.getWidth();
            int h = image.getHeight();
            int count = 0;
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    int color = image.getRGB(i, j);
                    if (getRed(color) + getBlue(color) + getGreen(color) > 50) {
                        count++;
                    }
                }
            }

            return !(count > w * h * 0.2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 图片切割
     * @return 切割出的小图片的路径
     */
    public static String subImage(String srcFileName, int x, int y, int width, int height) {
        try {
            File srcFile = new File(srcFileName);
            BufferedImage srcImage = ImageIO.read(srcFile);
            BufferedImage destImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
            for (int i = x; i < width; i++) {
                for (int j = y; j < height; j++) {
                    destImage.setRGB(i, j, srcImage.getRGB(i, j));
                }
            }
            String destFileName = srcFile.getParent() + "/" + System.nanoTime() + ".jpg";
            ImageIO.write(destImage, ".jpg", new File(destFileName));
            return destFileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 图片切割
     * @return 切割出的小图片的路径
     */
    public static String subImage(String srcFileName, Rectangle rectangle) {
        return subImage(srcFileName, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
}
