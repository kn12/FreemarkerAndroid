package com.idreamsky.freemarker.util;

import java.util.HashMap;
import java.util.Map;

public class Utils {

	public static Map<String, Object> getMap() {
		return new HashMap<String, Object>();
	}

	public static String getDrawable(String node_value) {
		String s = null;
		if (node_value.startsWith("#")) {
			s = "Color.parseColor(\"" + node_value + "\")";

		} else if (node_value.contains("@")) {
			String[] ss = node_value.split("/");
			s = "getDrawable(\"" + ss[1] + ".png)\"";
		}
		return s;
	}

	public static String getColor(String node_value) {
		String s = null;
		if (node_value.startsWith("@")) {
			String[] ss = node_value.split("/");
			s = "getColor(\"" + ss[1] + ")\"";
		} else {
			s = "Color.parseColor(\"" + node_value + "\")";
		}
		return s;
	}

	public static String getColorDrawable(String node_value) {
		String s = null;
		if (node_value.startsWith("@")) {
			if (node_value.contains("color/")) {
				String[] ss = node_value.split("/");
				s = "getColor(\"" + ss[1] + ")\"";
			} else if (node_value.contains("drawable/")) {
				String[] ss = node_value.split("/");
				s = "getDrawable(\"" + ss[1] + ".png)\"";
			}
		} else if (node_value.startsWith("#")) {
			s = "Color.parseColor(\"" + node_value + "\")";
		} else {
			s = "null";
		}

		return s;
	}

	public static String getDimen(String node_value) {
		String s = null;
		if (node_value.startsWith("@")) {

		} else if (node_value.endsWith("dp")) {
			s = node_value.replace("dp", "");
		} else if (node_value.endsWith("dip")) {
			s = node_value.replace("dip", "");
		} else if (node_value.endsWith("sp")) {
			s = node_value.replace("sp", "");
		} else {
		}

		return s;
	}

	public static String getName(String node_name) {
		String value;
		if (node_name.equals("layout") || node_name.equals("style")) {
			value = node_name;
		} else {
			String[] s = node_name.split(":");
			value = s[1];
		}
		return value;
	}

	/** 小写转大写 */
	public static String getUpperCase(String s) {
		char[] cs = s.toCharArray(); // 转换为CHAR数组
		String us = "";
		for (char cs_ : cs) {
			us += String.valueOf(cs_).toUpperCase();
		}
		return us;
	}

	public static String getId(String node_value) {
		String[] s = node_value.split("/");
		return s[1];
	}

	public static String getString(String node_value) {
		String s = null;
		if (node_value.startsWith("@")) {

		} else {
			s = "\"" + node_value + "\"";
		}
		return s;
	}

}
