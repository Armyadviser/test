package storm_falcon.util.image;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ImageRecognizer {

	public static void test() throws Exception {
		String dir = "E:/MyPictures/Pվ";
		int level = 19;

		String tempDir = dir.substring(0, dir.lastIndexOf('/')) + "temp";
		new File(tempDir).mkdir();

		ImagePHash pHash = new ImagePHash();
		
		File[] images = new File(dir).listFiles();
		List<String> data = new ArrayList<>();
		assert images != null;
		for (File newImage : images) {
			String hash = pHash.getHash(new FileInputStream(newImage));

			for (String oldData : data) {
				int distance = pHash.distance(oldData, hash);
				if (distance >= level) {
					data.add(hash);
				}
			}
		}
	}
}
