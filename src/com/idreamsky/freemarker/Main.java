package com.idreamsky.freemarker;

import java.io.File;

import com.idreamsky.freemarker.generator.Generator;

public class Main {

	public static String OUTPUT_PATH = "F:" + File.separator + "freemarker"
			+ File.separator + "test";

	public static void main(String[] args) {
		String templatePath = "src/template/test.ftl";
//		String xmlPath = "src/xml/activity_main.xml";
		String xmlPath = "src/xml/";
		String outPath = OUTPUT_PATH;
		
		Generator c = new Generator();
		c.generate(templatePath, xmlPath, outPath);
		
	}

}
