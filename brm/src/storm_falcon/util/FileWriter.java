package storm_falcon.util;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FileWriter implements Closeable {

	private BufferedWriter mWriter = null;

	private String lineSeparator = System.getProperty("line.separator");

	private AtomicInteger lineNum;

	private boolean opened;

	private boolean closed;

	private final Object lock = new Object();

	public void open(String filePath, String encode, boolean append) {
		synchronized (lock) {
			File file = createFile(filePath);

			mWriter = newBufferedWriter(file, encode, append);
			opened = mWriter != null;

			lineNum = new AtomicInteger(0);
		}
	}

	public void open(String filePath, String encode) {
		open(filePath, encode, true);
	}

	public void open(String filePath) {
		open(filePath, "gbk", true);
	}

	private File createFile(String strFilePath) {
		File file = new File(strFilePath);
		if (file.exists()) {
			return file;
		}

		File parentFile = file.getParentFile();

		if (!parentFile.exists()) {
			try {
				parentFile.mkdirs();
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	private BufferedWriter newBufferedWriter(File file, String encode, boolean append) {
		try {
			return new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, append), encode));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void setSeparator(String separator) {
		lineSeparator = separator;
	}
	
	public void writeLine(String line){
		synchronized (lock) {
			if (!opened) {
				return;
			}

			try {
				mWriter.write(line);
				mWriter.write(lineSeparator);

				lineNum.incrementAndGet();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void close() {
		synchronized (lock) {
			if (closed) {
				return;
			}

			try {
				mWriter.flush();
				mWriter.close();
				closed = true;
				opened = false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
