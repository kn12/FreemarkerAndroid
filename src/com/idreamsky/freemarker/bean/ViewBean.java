package com.idreamsky.freemarker.bean;

import java.util.Map;

import com.idreamsky.freemarker.util.Utils;

public class ViewBean {

	private String layout_width;
	private String layout_height;
	private String id;

	private String scrollbars;

	private Map<String, Object> mapRelaRules = Utils.getMap();
	private Map<String, Object> mapValues = Utils.getMap();
	private Map<String, Object> mapParamsValues = Utils.getMap();

	/** 未解析集 */
	private Map<String, Object> mapUnPrased = Utils.getMap();

	public Map<String, Object> getMapRelaRules() {
		return mapRelaRules;
	}

	public void addMapRelaRules(String key, String value) {
		this.mapRelaRules.put(key, value);
	}

	public String getLayout_width() {
		return layout_width;
	}

	public void setLayout_width(String layout_width) {
		this.layout_width = layout_width;
	}

	public String getLayout_height() {
		return layout_height;
	}

	public void setLayout_height(String layout_height) {
		this.layout_height = layout_height;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getMapValues() {
		return mapValues;
	}

	public void addMapValues(String key, String value) {
		this.mapValues.put(key, value);
	}

	public Map<String, Object> getMapParamsValues() {
		return mapParamsValues;
	}

	public void addMapParamsValues(String key, String value) {
		this.mapParamsValues.put(key, value);
	}

	public String getScrollbars() {
		return scrollbars;
	}

	public void setScrollbars(String scrollbars) {
		this.scrollbars = scrollbars;
	}

	public Map<String, Object> getMapUnPrased() {
		return mapUnPrased;
	}

	public void addMapUnPrased(String key,String value) {
		this.mapUnPrased.put(key, value);
	}

}
