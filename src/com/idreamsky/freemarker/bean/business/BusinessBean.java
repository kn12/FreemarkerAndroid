package com.idreamsky.freemarker.bean.business;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;

public class BusinessBean {

	public String fileName;

	public BusinessBean(String filename) {
		this.fileName = filename;
	}

	public BusinessBean() {

	}

	public String name;
	public NamedNodeMap attrData;
	public List<BusinessBean> beanList = new ArrayList<BusinessBean>();

}
