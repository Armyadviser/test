package storm_falcon.swing.mathpic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;

public class MainMathPic extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 1024;
	private static final int HEIGHT = 1024;
	
	private BufferedImage mImage = null;
	
	public MainMathPic() {
		super();
		initImage();
		
		setVisible(true);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g) {
		g.drawImage(mImage, 0, 0, WIDTH, HEIGHT, getBackground(), this);
	}

	private int convert(int c, float rad) {
	    int r = (int) (c * rad);
        return r > 255 ? 255 : r;
    }
	
	private void initImage() {
		mImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		ColorGenerator cg = new Pic3();
		for (int j = 0; j < WIDTH; j++) {
			for (int i = 0; i < HEIGHT; i++) {
				int r = cg.R(i, j);
				int g = cg.G(i, j);
				int b = cg.B(i, j);

                r = convert(r, 1.1f);
                g = convert(g, 1.4f);
                b = convert(b, 1.6f);

				int rgb = (b << 16) | (r << 8) | (g);
				mImage.setRGB(i, j, rgb);
			}
		}
		saveToFile();
	}
	
	public void saveToFile() {
		try {
			Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("png");
			ImageWriter writer = iter.next();
			
			File file = new File("D:/MathPic3.png");
			ImageOutputStream ios = ImageIO.createImageOutputStream(file);
			writer.setOutput(ios);
			
			writer.write(mImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MainMathPic();
	}
}
