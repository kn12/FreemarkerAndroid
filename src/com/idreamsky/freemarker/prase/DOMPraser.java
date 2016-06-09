package com.idreamsky.freemarker.prase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.idreamsky.freemarker.bean.business.BusinessBean;
import com.idreamsky.freemarker.util.DocumentHelper;
import com.idreamsky.freemarker.util.FileUtil;

public class DOMPraser {

	public List<BusinessBean> getBeans(String xmlPath) {
		String[] names = FileUtil.getNames(xmlPath);
		String path = FileUtil.getPath(xmlPath);
		List<BusinessBean> list = new ArrayList<BusinessBean>();
		try {
			DocumentBuilder builder = DocumentHelper.getBuilder();
			for (String name : names) {
				String s = path + name;
				Document dom = builder.parse(s);
				Element root = dom.getDocumentElement();
				BusinessBean parentBean = new BusinessBean(name);
				prase(root, parentBean);
				list.add(parentBean);
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private BusinessBean prase(Node parentNode, BusinessBean parentBean) {
		short type = parentNode.getNodeType();
		switch (type) {
		case Node.ELEMENT_NODE:
			if (parentNode.hasChildNodes()) {
				parentBean.name = parentNode.getNodeName();
				parentBean.attrData = parentNode.getAttributes();
				NodeList nlist = parentNode.getChildNodes();
				for (int i = 0; i < nlist.getLength(); i++) {
					Node childNode = nlist.item(i);
					BusinessBean childBean = new BusinessBean();
					if (childNode.getNodeType() == Node.ELEMENT_NODE) {
						NamedNodeMap nodeMap = childNode.getAttributes();
						childBean.name = childNode.getNodeName();
						childBean.attrData = nodeMap;
						parentBean.beanList.add(childBean);
					}
					prase(nlist.item(i), childBean);
				}
			}
			break;
		}
		return parentBean;
	}

}
