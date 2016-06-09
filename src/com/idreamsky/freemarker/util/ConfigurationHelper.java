package com.idreamsky.freemarker.util;

import java.io.IOException;
import java.util.Locale;

import freemarker.template.Configuration;

public class ConfigurationHelper {

	private static Configuration cfg = null;

	public static Configuration getConfiguration() throws IOException {
		if (null == cfg) {
			cfg = new Configuration();
			cfg.setLocale(Locale.CHINA);
			cfg.setDefaultEncoding("UTF-8");
		}
		return cfg;
	}
}
