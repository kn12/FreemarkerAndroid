package com.idreamsky.freemarker.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DocumentHelper {

	private static DocumentBuilder builder = null;

	public static DocumentBuilder getBuilder() {
		try {
			if (builder == null) {
				DocumentBuilderFactory factory = DocumentBuilderFactory
						.newInstance();
				builder = factory.newDocumentBuilder();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return builder;
	}

}
