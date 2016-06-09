package com.idreamsky.freemarker.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {

	public static File create(String path, String fileName) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		File output = new File(path, fileName);
		output.createNewFile();
		return output;
	}

	public static String getPath(String path) {
		File file = new File(path);
		String s = null;
		if (file.isDirectory()) {
			s = file.getPath()+"\\";
		} else if (file.isFile()) {
			String name = file.getPath();
			s = name.substring(0, name.lastIndexOf("\\") + 1);
		} else {
			try {
				throw new Exception("please make sure your path is available");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	public static String[] getNames(String path) {
		File file = new File(path);
		String[] n = null;
		if (file.isDirectory()) {
			n = file.list();
		} else if (file.isFile()) {
			n = new String[] { file.getName() };
		} else {
			try {
				throw new Exception("please make sure your path is available");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return n;
	}
}
