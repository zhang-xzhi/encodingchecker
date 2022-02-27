package allen.util;

import static java.lang.System.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import allen.encoding.Encoding;
import allen.encoding.ExtEncodingUtil;

public class FileConvertor {
	public static void main(String[] args) throws Exception {
		String path = "/Users/zhangxinzhi/allen_code/memoryutil";
		convert(path);
	}

	private static List<String> ignores = new ArrayList<String>();
	static {
		ignores.add(".git");
		ignores.add("target");
	}

	public static void convert(String filePath) throws Exception {
		convert(new File(filePath));
	}

	public static void convert(File file) throws Exception {
		if (file.isDirectory()) {
			if (ignores.contains(file.getName())) {
				out.println("ok. ignore dir = " + file.getAbsolutePath());
				return;
			}

			File[] files = file.listFiles();
			if (files != null) {
				for (File f : files) {

					convert(f);
				}
			}
		} else {
			convertGBK2UTF8(file.getAbsolutePath());
		}
	}

	/**
	 * 转化文件编码格式为UTF-8.
	 */
	public static void convertGBK2UTF8(String filePath) throws Exception {
		File file = new File(filePath);
		String oldFilePath = file.getAbsolutePath();
		File newFile = new File(oldFilePath + ".allen.new");
		File bakFile = new File(oldFilePath + ".allen.bak");

		if (!oldFilePath.endsWith(".java")) {
			out.println("ok. ignore file [not java file] = " + oldFilePath);
			return;
		}

		byte[] data = FileReadUtil.readBytes(file);

		if (ExtEncodingUtil.isRightEncoding(data, Encoding.UTF_8)) {
			out.println("ok. ignore file [UTF-8 file] = " + oldFilePath);
			return;
		}

		if (ExtEncodingUtil.isRightEncoding(data, Encoding.GBK)) {
			byte[] newData = ExtEncodingUtil.convertBytes(data, Encoding.GBK, Encoding.UTF_8);

			FileWriteUtil.save2File(newFile, newData);
			file.renameTo(bakFile);

			newFile.renameTo(file);

			bakFile.delete();
			out.println("ok. [ GBK to UTF-8 ] = " + oldFilePath);
			return;
		} else {
			out.println("error. [ not GBK file ] = " + oldFilePath);
			return;
		}
	}

}
