package com.idreamsky.freemarker.bean.type;

public class BeanType {

	// *************Button*******************************
	public static String text = "text", textColor = "textColor",
			textSize = "textSize", textColorHint = "textColorHint",
			textColorHighlight = "textColorHighlight";
	public static String singleLine = "singleLine", lines = "lines",
			password = "password", minLines = "minLines";
	public static String numeric = "numeric", digits = "digits",
			phoneNumber = "phoneNumber";
	public static String max = "max", maxLength = "maxLength",
			maxHeight = "maxHeight", maxWidth = "maxWidth",
			maxLines = "maxLines", maxEms = "maxEms";
	public static String minEms = "minEms";
	public static String enabled = "enabled", ems = "ems",
			ellipsize = "ellipsize", editable = "editable";
	public static String drawable = "drawable", drawableTop = "drawableTop",
			drawableLeft = "drawableLeft", drawableRight = "drawableRight",
			drawableBottom = "drawableBottom", drawableStart = "drawableStart",
			drawableEnd = "drawableEnd", drawablePadding = "drawablePadding";

	// *************CheckBox*******************************
	public static String button = "button", checked = "checked";

	// *************ImageView*******************************
	public static String src = "src", baseline = "baseline";

	// *************ListView*******************************
	public static String dividerHeight = "dividerHeight",
			listSelector = "listSelector";

	// *************GirdView*******************************
	public static String columnWidth = "columnWidth",
			numColumns = "numColumns";

	// *************Rela*******************************
	public static String[][] rela_layout = {
			{ "layout_toRightOf", "RIGHT_OF" },
			{ "layout_toLeftOf", "LEFT_OF" }, { "layout_above", "ABOVE" },
			{ "layout_below", "BELOW" },

			{ "layout_toStartOf", "START_OF" }, { "layout_toEndOf", "END_OF" },

			{ "layout_centerInParent", "CENTER_IN_PARENT" },
			{ "layout_centerHorizontal", "CENTER_HORIZONTAL" },
			{ "layout_centerVertical", "CENTER_VERTICAL" },

			{ "layout_alignBaseline", "ALIGN_BASELINE" },
			{ "layout_alignLeft", "ALIGN_LEFT" },
			{ "layout_alignRight", "ALIGN_RIGHT" },
			{ "layout_alignTop", "ALIGN_TOP" },
			{ "layout_alignBottom", "ALIGN_BOTTOM" },

			{ "layout_alignParentLeft", "ALIGN_PARENT_LEFT" },
			{ "layout_alignParentRight", "ALIGN_PARENT_RIGHT" },
			{ "layout_alignParentTop", "ALIGN_PARENT_TOP" },
			{ "layout_alignParentBottom", "ALIGN_PARENT_BOTTOM" },

			{ "layout_alignStart", "ALIGN_START" },
			{ "layout_alignEnd", "ALIGN_END" },
			{ "layout_alignParentStart", "ALIGN_PARENT_START" },
			{ "layout_alignParentEnd", "ALIGN_PARENT_END" } };

	// ************COMMON********************************

	public static String include = "include";
	public static String style = "style";

	public static String layout = "layout";
	public static String layout_width = "layout_width";
	public static String layout_height = "layout_height";
	public static String layout_weight = "layout_weight";
	public static String layout_gravity = "layout_gravity";

	public static String id = "id";
	public static String visibility = "visibility";
	public static String background = "background";
	public static String gravity = "gravity";
	public static String orientation = "orientation";
	public static String focusable = "focusable",
			focusableInTouchMode = "focusableInTouchMode";

	public static String scroll = "scroll", scrollbars = "scrollbars",
			scrollbarSize = "scrollbarSize", fadeScrollbars = "fadeScrollbars",
			scrollbarFadeDuration = "scrollbarFadeDuration";

	public static String min = "min";
	public static String minHeight = "minHeight";
	public static String minWidth = "minWidth";

	public static String clickable = "clickable";
	public static String longClickable = "longClickable";
	public static String divider = "divider";
	public static String dividerPadding = "dividerPadding";

	public static String margin = "margin";
	public static String layout_margin = "layout_margin";
	public static String layout_marginLeft = "layout_marginLeft";
	public static String layout_marginRight = "layout_marginRight";
	public static String layout_marginTop = "layout_marginTop";
	public static String layout_marginBottom = "layout_marginBottom";
	public static String layout_marginStart = "layout_marginStart";
	public static String layout_marginEnd = "layout_marginEnd";

	public static String padding = "padding";
	public static String paddingLeft = "paddingLeft";
	public static String paddingRight = "paddingRight";
	public static String paddingButtom = "paddingButtom";
	public static String paddingTop = "paddingTop";
	public static String paddingStart = "paddingStart";
	public static String paddingEnd = "paddingEnd";

}
