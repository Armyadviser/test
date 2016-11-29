package storm_falcon.util.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnusedReturnValue")
public class FileHelper {

	/**
	 * 下载文件
	 * @param strUrl 网络url
	 * @param pathToSave 本地保存路径
	 * @return
	 */
	@SuppressWarnings({"UnusedDeclaration"})
	public static void download(String strUrl, String pathToSave) {
		try {
			URL url = new URL(strUrl);
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			is.close();
			
			File file = createFile(pathToSave);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(buffer);
			
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 按字节读取文件
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static byte[] readFile(File file) throws IOException {
		long len = file.length();
		byte data[] = new byte[(int) len];
		FileInputStream fin = new FileInputStream(file);
		int r = fin.read(data);
		if (r != len)
			throw new IOException("Only read " + r + " of " + len + " for " + file);
		fin.close();
		return data;
	}
	
	/**
	 * 按字节读取文件
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({"UnusedDeclaration"})
	static public byte[] readFile(String filename) throws IOException {
		File file = new File(filename);
		return readFile(file);
	}

	/**
	 * 将byte写入文件
	 * @param file
	 * @param data
	 * @throws IOException
	 */
	@SuppressWarnings({"UnusedDeclaration"})
	public static void writeFile(File file, byte[] data) throws IOException {
		FileOutputStream fOut = new FileOutputStream(file);
		fOut.write(data);
		fOut.close();
	}
	
	/**
	 * 将byte写入文件
	 * @param filename
	 * @param data
	 * @throws IOException
	 */
	@SuppressWarnings({"UnusedDeclaration"})
	public static void writeFile(String filename, byte data[]) throws IOException {
		FileOutputStream fout = new FileOutputStream(filename);
		fout.write(data);
		fout.close();
	}

	/**
	 * 遍历目录
	 * @param dirPath 目标目录
	 * @param isFullName 是否为完整路径
	 * @param suffixes 筛选后缀名，即文件格式
	 * @return 所有符合格式的文件名
	 */
	@SuppressWarnings({"UnusedDeclaration"})
	public static List<String> traversalDir(String dirPath, boolean isFullName, String... suffixes) {
		List<String> fileList = new ArrayList<>();
		File root = new File(dirPath);
		addFileToList(root, fileList, isFullName, suffixes);
		return fileList;
	}

	private static void addFileToList(File dirFile, List<String> fileList, boolean isFullName, String... suffixes) {
		File[] fs = dirFile.listFiles();
		
		for (File file : fs != null ? fs : new File[0]) {
			boolean flag = false;
			String path = file.getAbsolutePath();
			
			//判断文件格式
			if (suffixes.length == 0) {
				flag = true;
			}
			for (String suffix : suffixes) {
				if (path.endsWith(suffix)) {
					flag = true;
					break;
				}
			}
			
			if (flag && !file.isDirectory()) {
				if (isFullName) {
					fileList.add(file.getAbsolutePath());
				} else {
					fileList.add(file.getName());
				}
			} else if (file.isDirectory()) {
				addFileToList(file, fileList, isFullName, suffixes);
			}
		}
	}
	
	/**
	 * 文件不存在则创建新文件
	 * @param strFilePath
	 * @return 返回创建的文件对象
	 */
	public static File createFile(String strFilePath) {
		int endIndex = strFilePath.lastIndexOf("/");
		if (endIndex == -1) {
			endIndex = strFilePath.lastIndexOf("\\");
		}
		String path = strFilePath.substring(0, endIndex);
		File dir = new File(path);
		
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		File file = new File(strFilePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	/**
	 * 将dir目录下多有文件内容整合成一个文件
	 * @param dir
	 * @param out
	 * @return
	 */
	public static File unite(Path dir, Path out) {
		try (final ByteChannel channel = Files.newByteChannel(
				out, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)) {

			Files.walk(dir, FileVisitOption.FOLLOW_LINKS)
					.map(path -> {
						try {
							return Files.newByteChannel(path, StandardOpenOption.READ);
						} catch (IOException e) {
							e.printStackTrace();
						}
						return null;
					})
					.forEach(chnl -> {
						append(chnl, channel);
					});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return out.toFile();
	}

	private static void append(ByteChannel in, ByteChannel out) {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		try {
			while (in.read(buffer) != -1) {
				out.write(buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 打开写文件的BufferedWriter输出流
	 * @param file 目标文件
	 * @param isAppend 是否以追加方式打开
	 * @param encoding 编码
	 * @return
	 */
	@SuppressWarnings({"UnusedDeclaration"})
	public static BufferedWriter openFileBufferedWriterStream(File file, boolean isAppend, String encoding) {
		try {
			return new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(file, isAppend), encoding));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * 文件复制
	 * @param source 源文件绝对路径
	 * @param dest 目标文件绝对路径
     */
	@SuppressWarnings({"UnusedDeclaration"})
	public static void copyTo(String source, String dest) {
		File sourceFile = new File(source);
		File destFile = new File(dest);
		copyTo(sourceFile, destFile);
	}

	/**
	 * 文件复制
	 * @param in 源文件
	 * @param out 目标文件
     */
	public static void copyTo(File in, File out) {
		try {
			if (!out.exists()) {
				assert out.createNewFile();
			}
			FileInputStream fIn = new FileInputStream(in);
			FileOutputStream fOut = new FileOutputStream(out);
			FileChannel fcIn = fIn.getChannel();
			FileChannel fcOut = fOut.getChannel();
			fcIn.transferTo(0, fcIn.size(), fcOut);
			fIn.close();
			fcIn.close();
			fOut.close();
			fcOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean rename(String file1, String file2) {
		File f1 = new File(file1);
		return f1.renameTo(new File(file2));
	}

	public static void delEmptyDir(String path) {
		File root = new File(path);
		File[] files = root.listFiles();
		if (files == null) {
			return;
		}
		for (File file : files) {
			if (!file.isDirectory()) {
				continue;
			}
			File[] subFiles = file.listFiles();
			if (subFiles == null || subFiles.length == 0) {
				file.delete();
				System.out.println(file.getAbsolutePath() + "--deleted.");
			}
		}
	}

	public static void main(String[] args) {
		String path = "计算机\\OPPO 1107\\SD 卡\\shortvideo";
		delEmptyDir(path);
	}
}
