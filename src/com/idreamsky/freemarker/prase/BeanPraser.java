package com.idreamsky.freemarker.prase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.idreamsky.freemarker.bean.ViewBean;
import com.idreamsky.freemarker.bean.business.BusinessBean;
import com.idreamsky.freemarker.bean.type.BeanType;
import com.idreamsky.freemarker.bean.type.ViewType;
import com.idreamsky.freemarker.util.Utils;

public class BeanPraser {

	private final static String Key_Root = "root";
	private final static String Key_NodeName = "nodeName";
	private final static String Key_List = "Key_List";
	private final static String Key_ViewBean = "viewBean";
	private final static String Key_Param = "paramName";
	private Map<String, Object> rootMap;

	public Map<String, Object> getMap(BusinessBean bean) {
		rootMap = Utils.getMap();
		Map<String, Object> fatherMap = Utils.getMap();
		prase(bean, fatherMap);
		rootMap.put(Key_Root, fatherMap);
		return rootMap;
	}

	private final static String[] layoutStrings = { "RelativeLayout",
			"TableLayout", "AbsoluteLayout", "LinearLayout", "FrameLayout" };

	private String getLayoutParamName(String name) {
		for (String s : layoutStrings) {
			if (s.equals(name)) {
				return name;
			}
		}
		return "LinearLayout";
	}

	private void prase(BusinessBean bean, Map<String, Object> fatherMap) {
		fatherMap.put(Key_NodeName, bean.name);
		fatherMap.put(Key_Param, getLayoutParamName(bean.name));

		NamedNodeMap attr = bean.attrData;
		ViewBean viewBean = getViewBean(bean.name, attr);
		fatherMap.put(Key_ViewBean, viewBean);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < bean.beanList.size(); i++) {
			Map<String, Object> childMap = Utils.getMap();
			BusinessBean childBean = bean.beanList.get(i);

			childMap.put(Key_NodeName, childBean.name);
			ViewBean childViewBean = getViewBean(bean.name, attr);
			clearStrings();
			childMap.put(Key_ViewBean, childViewBean);
			list.add(childMap);
			prase(childBean, childMap);
		}
		fatherMap.put(Key_List, list);
	}

	private String[] margins = new String[] { "0", "0", "0", "0" };
	private String[] paddings = new String[] { "0", "0", "0", "0" };
	private String[] drawables = new String[] { "0", "0", "0", "0" };

	private void clearStrings() {
		margins = new String[] { "0", "0", "0", "0" };
		paddings = new String[] { "0", "0", "0", "0" };
		drawables = new String[] { "0", "0", "0", "0" };
	}

	private ViewBean getViewBean(String name, NamedNodeMap attr) {
		ViewBean bean = new ViewBean();
		for (int i = 0; i < attr.getLength(); i++) {
			Node node = attr.item(i);
			String node_name = Utils.getName(node.getNodeName());
			String node_value = node.getNodeValue();
			if (name.equals(BeanType.include)) {
				setUnPrasedAttr(bean, node_name, node_value);

			} else if (node_name.equals(BeanType.style)) {
				setUnPrasedAttr(bean, node_name, node_value);

			} else if (node_name.startsWith(BeanType.layout)) {
				setLayout(bean, node_name, node_value);

			} else if (node_name.contains(BeanType.padding)) {
				setPadding(bean, node_name, node_value);

			} else if (node_name.contains(BeanType.min)) {
				setMin(bean, node_name, node_value);

			} else if (node_name.contains(BeanType.scroll)) {
				setScroll(bean, node_name, node_value);

			} else if (node_name.equals(BeanType.id)) {
				String s = Utils.getId(node_value);
				bean.setId(s);
			} else if (node_name.equals(BeanType.visibility)) {
				String s = ".setVisibility(" + Utils.getUpperCase(node_value)
						+ ")";
				bean.addMapValues(BeanType.visibility, s);
			} else if (node_name.equals(BeanType.background)) {
				String value = null;
				if (node_value.startsWith("#")) {
					value = ".setBackgroundColor(" + Utils.getColor(node_value)
							+ ")";

				} else if (node_value.contains("@")) {
					String s = node_value.replace("@", "");
					String[] ss = s.split("/");
					if (ss[0].contains("drawable")) {
						value = ".setBackgroundDrawable(getDrawable(\"" + ss[1]
								+ ".png\"))";
					} else if (ss[0].contains("color")) {
						value = ".setBackgroundColor(getColor(\"" + ss[1]
								+ ".xml)\")";
					} else if (node_value.contains("null")) {
						value = ".setBackgroundDrawable(null)";
					}
				}
				bean.addMapValues(BeanType.background, value);

			} else if (node_name.equals(BeanType.gravity)) {
				String s = ".setGravity(Gravity."
						+ Utils.getUpperCase(node_value) + ")";
				bean.addMapValues(BeanType.gravity, s);

			} else if (node_name.equals(BeanType.orientation)) {
				String s = ".setOrientation(LinearLayout."
						+ Utils.getUpperCase(node_value) + ")";
				bean.addMapValues(BeanType.orientation, s);
			} else if (node_name.equals(BeanType.focusable)) {
				String s = ".setFocusable(" + node_value + ")";
				bean.addMapValues(BeanType.focusable, s);

			} else if (node_name.equals(BeanType.focusableInTouchMode)) {
				String s = ".setFocusableInTouchMode(" + node_value + ")";
				bean.addMapValues(BeanType.focusableInTouchMode, s);

			} else if (node_name.contains(BeanType.divider)) {
				if (node_name.equals(BeanType.divider)) {
					String value = ".setDividerDrawable("
							+ Utils.getColorDrawable(node_value) + ")";
					bean.addMapValues(BeanType.divider, value);

				} else if (node_name.equals(BeanType.dividerPadding)) {
					String s = ".setDividerPadding("
							+ Utils.getDimen(node_value) + ")";
					bean.addMapValues(BeanType.dividerPadding, s);
				} else if (node_name.equals(BeanType.dividerHeight)) {
					String s = ".setDividerHeight("
							+ Utils.getDimen(node_value) + ")";
					bean.addMapValues(BeanType.dividerHeight, s);

				}
			} else if (node_name.equals(BeanType.clickable)) {
				String s = ".setClickable(" + node_value + ")";
				bean.addMapValues(BeanType.clickable, s);

			} else if (node_name.equals(BeanType.longClickable)) {
				String s = ".setLongClickable(" + node_value + ")";
				bean.addMapValues(BeanType.longClickable, s);

			} else if (name.equals(ViewType.Button)
					|| name.equals(ViewType.EditText)
					|| name.equals(ViewType.TextView)) {
				setWidget(bean, node_name, node_value);
			} else {
				if (node_name.equals(BeanType.button)) {
					String s = ".setButtonDrawable("
							+ Utils.getColorDrawable(node_value) + ")";
					bean.addMapValues(BeanType.button, s);

				} else if (node_name.equals(BeanType.checked)) {
					String s = ".setChecked(" + node_value + ")";
					bean.addMapValues(BeanType.checked, s);

				} else if (node_name.equals(BeanType.src)) {
					String s = ".setImageDrawable("
							+ Utils.getDrawable(node_value) + ")";
					bean.addMapValues(BeanType.src, s);

				} else if (node_name.equals(BeanType.baseline)) {
					String value = Utils.getDimen(node_value);
					String s = ".setBaseline(" + value + ")";
					bean.addMapValues(BeanType.baseline, s);
				} else if (node_name.equals(BeanType.listSelector)) {
					String s = ".setSelector("
							+ Utils.getColorDrawable(node_value) + ")";
					bean.addMapValues(BeanType.listSelector, s);

				} else if (node_name.equals(BeanType.columnWidth)) {
					String s = ".setColumnWidth(" + Utils.getDimen(node_value)
							+ ")";
					bean.addMapValues(BeanType.columnWidth, s);

				} else if (node_name.equals(BeanType.numColumns)) {
					String s = ".setNumColumns(" + node_value + ")";
					bean.addMapValues(BeanType.numColumns, s);

				} else {
					setUnPrasedAttr(bean, node_name, node_value);
				}
			}
		}
		return bean;
	}

	private void setLayout(ViewBean bean, String node_name, String node_value) {
		if (node_name.equals(BeanType.layout)) {
			setUnPrasedAttr(bean, node_name, node_value);

		} else if (node_name.equals(BeanType.layout_width)) {
			if (node_value.contains("_")) {
				bean.setLayout_width(Utils.getUpperCase(node_value));
			} else {
				bean.setLayout_width(Utils.getDimen(node_value));
			}

		} else if (node_name.equals(BeanType.layout_height)) {
			if (node_value.contains("_")) {
				bean.setLayout_height(Utils.getUpperCase(node_value));
			} else {
				bean.setLayout_height(Utils.getDimen(node_value));
			}

		} else if (node_name.contains(BeanType.margin)) {
			setMargin(bean, node_name, node_value);

		} else if (node_name.equals(BeanType.layout_gravity)) {
			String s = ".gravity = Gravity." + Utils.getUpperCase(node_value);
			bean.addMapParamsValues(BeanType.layout_gravity, s);

		} else if (node_name.equals(BeanType.layout_weight)) {
			String s = ".weight = " + node_value;
			bean.addMapParamsValues(BeanType.layout_weight, s);
		} else {// ---RelativeLayout.addRule-----
			String[][] relas = BeanType.rela_layout;
			for (int i = 0; i < relas.length; i++) {
				if (node_name.equals(relas[i][0])) {
					if (node_value.contains("@")) {
						String s = Utils.getId(node_value);
						bean.addMapRelaRules(relas[i][1], s);
						break;
					} else if (node_value.contains("true")) {
						String s = "RelativeLayout.TRUE";
						bean.addMapRelaRules(relas[i][1], s);
						break;
					}
				}
			}
		}
	}

	private void setMargin(ViewBean bean, String node_name, String node_value) {
		String s = Utils.getDimen(node_value);
		String marginEnd = null;
		String marginStart = null;
		if (node_name.equals(BeanType.layout_margin)) {
			margins = new String[] { s, s, s, s };
		} else if (node_name.equals(BeanType.layout_marginLeft)) {
			margins[0] = s;
		} else if (node_name.equals(BeanType.layout_marginTop)) {
			margins[1] = s;
		} else if (node_name.equals(BeanType.layout_marginRight)) {
			margins[2] = s;
		} else if (node_name.equals(BeanType.layout_marginBottom)) {
			margins[3] = s;
		} else if (node_name.equals(BeanType.layout_marginStart)) {
			marginStart = ".setMarginStart(" + s + ")";
		} else if (node_name.equals(BeanType.layout_marginEnd)) {
			marginEnd = ".setMarginEnd(" + s + ")";
		}
		StringBuffer value = new StringBuffer();
		value.append(".setMargins(");
		for (int i = 0; i < margins.length; i++) {
			value.append(margins[i]);
			if (i != 3)
				value.append(",");
		}
		value.append(")");
		bean.addMapParamsValues(BeanType.layout_margin, value.toString());

		if (marginStart != null) {
			bean.addMapParamsValues(BeanType.layout_marginStart, marginStart);
		}
		if (marginEnd != null) {
			bean.addMapParamsValues(BeanType.layout_marginEnd, marginEnd);
		}
	}

	private void setPadding(ViewBean bean, String node_name, String node_value) {
		String s = Utils.getDimen(node_value);
		if (node_name.equals(BeanType.padding)) {
			paddings = new String[] { s, s, s, s };
		} else if (node_name.equals(BeanType.paddingLeft)) {
			paddings[0] = s;
		} else if (node_name.equals(BeanType.paddingTop)) {
			paddings[1] = s;
		} else if (node_name.equals(BeanType.paddingRight)) {
			paddings[2] = s;
		} else if (node_name.equals(BeanType.paddingButtom)) {
			paddings[3] = s;
		} else if (node_name.equals(BeanType.paddingStart)) {
			if (!paddings[0].equals("0")) {
				paddings[0] = s;
			}
		} else if (node_name.equals(BeanType.paddingEnd)) {
			if (!paddings[2].equals("0")) {
				paddings[2] = s;
			}
		}
		StringBuffer value = new StringBuffer();
		value.append(".setPadding(");
		for (int i = 0; i < paddings.length; i++) {
			value.append(paddings[i]);
			if (i != 3)
				value.append(",");
		}
		value.append(")");
		bean.addMapValues(BeanType.padding, value.toString());
	}

	private void setWidget(ViewBean bean, String node_name, String node_value) {
		if (node_name.contains(BeanType.text)) {
			setText(bean, node_name, node_value);

		} else if (node_name.contains(BeanType.max)) {
			setMax(bean, node_name, node_value);

		} else if (node_name.contains(BeanType.drawable)) {
			setDrawable(bean, node_name, node_value);

		} else if (node_name.equals(BeanType.singleLine)) {
			String s = ".setSingleLine(" + node_value + ")";
			bean.addMapValues(BeanType.singleLine, s);

		} else if (node_name.equals(BeanType.lines)) {
			String s = ".setLines(" + node_value + ")";
			bean.addMapValues(BeanType.lines, s);

		} else if (node_name.equals(BeanType.password)) {
			String s;
			String head = ".setInputType(";
			if (Boolean.valueOf(node_value)) {
				s = head
						+ "InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD"
						+ ")";
			} else {
				s = head + "InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD"
						+ ")";
			}
			bean.addMapValues(BeanType.password, s);
		} else if (node_name.equals(BeanType.numeric)) {
			String s;
			if (node_value.equals("decimal")) {
				s = ".setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL)";

			} else if (node_value.equals("signed")) {
				s = ".setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED)";
			} else {
				s = ".setInputType(InputType.TYPE_CLASS_NUMBER)";
			}
			bean.addMapValues(BeanType.numeric, s);

		} else if (node_name.equals(BeanType.digits)) {
			String s = ".setKeyListener(DigitsKeyListener.getInstance(\""
					+ node_value + "\"))";
			bean.addMapValues(BeanType.digits, s);

		} else if (node_name.equals(BeanType.phoneNumber)) {
			if (Boolean.valueOf(node_value)) {
				String s = ".setInputType(InputType.TYPE_CLASS_PHONE)";
				bean.addMapValues(BeanType.phoneNumber, s);
			}

		} else if (node_name.equals(BeanType.enabled)) {
			String s = ".setEnabled(" + node_value + ")";
			bean.addMapValues(BeanType.enabled, s);

		} else if (node_name.equals(BeanType.editable)) {
			String s = ".setEnabled(" + node_value + ")";
			bean.addMapValues(BeanType.editable, s);

		} else if (node_name.equals(BeanType.ems)) {
			String s = ".setEms(" + node_value + ")";
			bean.addMapValues(BeanType.ems, s);

		} else if (node_name.equals(BeanType.ellipsize)) {
			String head = ".setEllipsize(TruncateAt.";
			String s = head + Utils.getUpperCase(node_value) + ")";
			bean.addMapValues(BeanType.ellipsize, s);
		} else {
			setUnPrasedAttr(bean, node_name, node_value);
		}
	}

	private void setText(ViewBean bean, String node_name, String node_value) {
		if (node_name.equals(BeanType.text)) {
			String s = ".setText(" + Utils.getString(node_value) + ")";
			bean.addMapValues(BeanType.text, s);

		} else if (node_name.equals(BeanType.textColor)) {
			String s = ".setTextColor(" + Utils.getColor(node_value) + ")";
			bean.addMapValues(BeanType.textColor, s);

		} else if (node_name.equals(BeanType.textSize)) {
			String s = ".setTextSize(" + Utils.getDimen(node_value) + ")";
			bean.addMapValues(BeanType.textSize, s);

		} else if (node_name.equals(BeanType.textColorHint)) {
			String s = ".setHintTextColor(" + Utils.getColor(node_value) + ")";
			bean.addMapValues(BeanType.textColorHint, s);

		} else if (node_name.equals(BeanType.textColorHighlight)) {
			String s = ".setHighlightColor(" + Utils.getColor(node_value) + ")";
			bean.addMapValues(BeanType.textColorHighlight, s);
		} else {
			setUnPrasedAttr(bean, node_name, node_value);
		}
	}

	private void setMin(ViewBean bean, String node_name, String node_value) {
		String s = Utils.getDimen(node_value);
		if (node_name.equals(BeanType.minWidth)) {
			String value = ".setMinimumWidth(" + s + ")";
			bean.addMapValues(BeanType.minWidth, value);

		} else if (node_name.equals(BeanType.minHeight)) {
			String value = ".setMinimumHeight(" + s + ")";
			bean.addMapValues(BeanType.minHeight, value);

		} else if (node_name.equals(BeanType.minLines)) {
			String value = ".setMinLines(" + node_value + ")";
			bean.addMapValues(BeanType.minLines, value);

		} else if (node_name.equals(BeanType.minEms)) {
			String value = ".setMinEms(" + node_value + ")";
			bean.addMapValues(BeanType.minEms, value);
		} else {
			setUnPrasedAttr(bean, node_name, node_value);
		}
	}

	private void setMax(ViewBean bean, String node_name, String node_value) {
		if (node_name.equals(BeanType.maxLength)) {
			String s = ".setMaxLength(" + node_value + ")";
			bean.addMapValues(BeanType.maxLength, s);

		} else if (node_name.equals(BeanType.maxHeight)) {
			String s = ".setMaxHeight(" + Utils.getDimen(node_value) + ")";
			bean.addMapValues(BeanType.maxHeight, s);

		} else if (node_name.equals(BeanType.maxWidth)) {
			String s = ".setMaxWidth(" + Utils.getDimen(node_value) + ")";
			bean.addMapValues(BeanType.maxWidth, s);

		} else if (node_name.equals(BeanType.maxLines)) {
			String s = ".setMaxLines(" + node_value + ")";
			bean.addMapValues(BeanType.maxLines, s);
		} else if (node_name.equals(BeanType.maxEms)) {
			String s = ".setMaxEms(" + node_value + ")";
			bean.addMapValues(BeanType.maxEms, s);
		} else {
			setUnPrasedAttr(bean, node_name, node_value);
		}
	}

	private void setScroll(ViewBean bean, String node_name, String node_value) {
		if (node_name.equals(BeanType.scrollbars)) {
			// 这个要特殊处理
			bean.setScrollbars(node_value);

		} else if (node_name.equals(BeanType.scrollbarSize)) {
			String s = ".setScrollBarSize(" + Utils.getDimen(node_value) + ")";
			bean.addMapValues(BeanType.scrollbarSize, s);
		} else if (node_name.equals(BeanType.fadeScrollbars)) {
			String s = ".setScrollbarFadingEnabled(" + node_value + ")";
			bean.addMapValues(BeanType.fadeScrollbars, s);
		} else if (node_name.equals(BeanType.scrollbarFadeDuration)) {
			String s = ".setScrollBarFadeDuration(" + node_value + ")";
			bean.addMapValues(BeanType.scrollbarFadeDuration, s);
		} else {
			setUnPrasedAttr(bean, node_name, node_value);
		}

	}

	private void setDrawable(ViewBean bean, String node_name, String node_value) {
		String[] ss = null;
		String drawablePadding = null;
		if (node_value.contains("/")) {
			ss = node_value.split("/");
		}
		if (node_name.equals(BeanType.drawableLeft)) {
			drawables[0] = ss[1];
		} else if (node_name.equals(BeanType.drawableTop)) {
			drawables[1] = ss[1];
		} else if (node_name.equals(BeanType.drawableRight)) {
			drawables[2] = ss[1];
		} else if (node_name.equals(BeanType.drawableBottom)) {
			drawables[3] = ss[1];
		} else if (node_name.equals(BeanType.drawablePadding)) {
			drawablePadding = Utils.getDimen(node_value);

		} else if (node_name.equals(BeanType.drawableStart)) {

			// setCompoundDrawablesRelativeWithIntrinsicBounds
		} else if (node_name.equals(BeanType.drawableEnd)) {

		}
		StringBuffer value = new StringBuffer();
		value.append(".setCompoundDrawablesWithIntrinsicBounds(");
		for (int i = 0; i < drawables.length; i++) {
			if (drawables[i].equals("0")) {
				value.append("null");
			} else {
				value.append("getDrawable(\"").append(drawables[i])
						.append(".png\")");
			}
			if (i != 3)
				value.append(",");
		}
		value.append(")");
		bean.addMapValues(BeanType.drawable, value.toString());
		if (drawablePadding != null) {
			String s = ".setCompoundDrawablePadding(" + drawablePadding + ")";
			bean.addMapValues(BeanType.drawablePadding, s);
		}
	}

	/**
	 * 未解析属性
	 */
	private void setUnPrasedAttr(ViewBean bean, String node_name,
			String node_value) {
		bean.addMapUnPrased(node_name, node_value);
	}

	private void print(String s) {
		System.out.println(s);
	}

}
