package com.idreamsky.freemarker.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import com.idreamsky.freemarker.bean.business.BusinessBean;
import com.idreamsky.freemarker.prase.BeanPraser;
import com.idreamsky.freemarker.prase.DOMPraser;
import com.idreamsky.freemarker.util.ConfigurationHelper;
import com.idreamsky.freemarker.util.FileUtil;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Generator {

	@SuppressWarnings("rawtypes")
	public void generate(String templatePath, String xmlPath, String outPath) {
		Writer writer = null;
		try {
			Template template = ConfigurationHelper.getConfiguration()
					.getTemplate(templatePath);

			DOMPraser dom = new DOMPraser();
			BeanPraser praser = new BeanPraser();
			List<BusinessBean> beans = dom.getBeans(xmlPath);

			for (int i = 0; i < beans.size(); i++) {
				BusinessBean bean = beans.get(i);
				File output = FileUtil.create(outPath,
						bean.fileName.replace(".xml", ".java"));
				writer = new FileWriter(output);
				Map root = praser.getMap(bean);
				template.process(root, writer);
			}

			// java.io.StringWriter out = new StringWriter();
			// template.process(root, out);
			// System.out.println(out.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
