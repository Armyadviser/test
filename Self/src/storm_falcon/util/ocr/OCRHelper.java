package storm_falcon.util.ocr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class OCRHelper {

    private static String imgPath;

    /**
     * @param imageFile 传入的图像文件路径
     * @return 识别后的字符串
     */
    public static synchronized String recognizeText(String imageFile) {
        return recognizeText(new File(imageFile));
    }

    /**
     * @param imageFile 传入的图像文件
     * @return 识别后的字符串
     */
    public static synchronized String recognizeText(File imageFile) {
        try {
            imgPath = imageFile.getParent();

            // tesseract.exe 1.jpg 1 -l chi_sim
            ProcessBuilder pb = new ProcessBuilder();
            pb.directory(imageFile.getParentFile());
            pb.command("C:\\Developer\\Tesseract-OCR\\tesseract", imageFile.getAbsolutePath(), "1", "-l", "eng");
            pb.redirectErrorStream(true);
            Process process = pb.start();

            int w = process.waitFor();
            if (w == 0) {
                String s = getFileContent();
                new File(imgPath + "\\1.txt").delete();
                return s;
            } else {
                String msg;
                switch (w) {
                    case 1:
                        msg = "Errors accessing files. There may be spaces in your image's filename.";
                        break;
                    case 29:
                        msg = "Cannot recognize the image or its selected region.";
                        break;
                    case 31:
                        msg = "Unsupported image format.";
                        break;
                    default:
                        msg = "Errors occurred.";
                }
                throw new RuntimeException(msg);
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String getFileContent() {
        try (FileInputStream in = new FileInputStream(imgPath + "\\1.txt")) {
            byte[] data = new byte[in.available()];
            in.read(data);
            return new String(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
