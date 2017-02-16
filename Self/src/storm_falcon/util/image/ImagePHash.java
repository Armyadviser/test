package storm_falcon.util.image;

import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.InputStream;

public class ImagePHash {

	private int size = 32;
	private int smallerSize = 8;
	
	private double[] c;
	
	@NotNull
	private final ColorConvertOp colorConvert = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
	
	public ImagePHash() {
		initCoefficients();
	}
	
	public ImagePHash(int size, int smallerSize) {
		this.size = size;
		this.smallerSize = smallerSize;
		
		initCoefficients();
	}
	
	private void initCoefficients() {
		c = new double[size];
		
		for (int i = 1; i < size; i++) {
			c[i] = 1;
		}
		c[0] = 1 / Math.sqrt(2.0);
	}
	
	public int distance(@NotNull String s1, @NotNull String s2) {
		int counter = 0;
		for (int k = 0; k < s1.length(); k++) {
			if (s1.charAt(k) != s2.charAt(k)) {
				counter++;
			}
		}
		return counter;
	}
	
	@NotNull
	private BufferedImage resize(BufferedImage image, int width, int height) {
		BufferedImage resizeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizeImage.createGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
		return resizeImage;
	}
	
	@NotNull
	private BufferedImage grayScale(@NotNull BufferedImage img) {
		colorConvert.filter(img, img);
		return img;
	}
	
	private int getBlue(@NotNull BufferedImage img, int x, int y) {
		return img.getRGB(x, y) & 0xff;
	}
	
	@NotNull
	private double[][] applyDCT(double[][] f) {
		int N = size;
		double[][] F = new double[N][N];
		for (int u = 0; u < N; u++) {
			for (int v = 0; v < N; v++) {
				double sum = 0.0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						sum += Math.cos(((2 * i + 1) / (2.0 * N)) * u * Math.PI)
								* Math.cos(((2 * j + 1) / (2.0 * N)) * v * Math.PI)
								* f[i][j];
					}
				}
				sum *= (c[u] * c[v]) / 4.0;
				F[u][v] = sum;
			}
		}
		return F;
	}
	
	@NotNull
	public String getHash(@NotNull InputStream is) throws Exception {
		BufferedImage img = ImageIO.read(is);
		
		img = resize(img, size, size);
		img = grayScale(img);
		
		int width = img.getWidth();
		int height = img.getHeight();
		double[][] vals = new double[size][size];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				vals[x][y] = getBlue(img, x, y);
			}
		}
		
		double[][] dctVals = applyDCT(vals);
		
		double total = 0;
		for (int x = 0; x < smallerSize; x++) {
			for (int y = 0; y < smallerSize; y++) {
				total += dctVals[x][y];
			}
		}
		total -= dctVals[0][0];
		double avg = total / (double) (smallerSize * smallerSize - 1);
		
		String hash = "";
		for (int x = 0; x < smallerSize; x++) {
			for (int y = 0; y < smallerSize; y++) {
				if (x != 0 && y != 0) {
					hash += (dctVals[x][y] > avg ? "1" : "0");
				}
			}
		}
		return hash;
	}
	
}
