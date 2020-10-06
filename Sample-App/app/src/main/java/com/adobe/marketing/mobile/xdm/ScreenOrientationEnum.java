
package com.adobe.marketing.mobile.xdm;

/**
 * XDM Java Enum Generated 2020-10-05 14:47:08.498565 -0700 PDT m=+2.042036888 by XDMTool
 */
@SuppressWarnings("unused")
public enum ScreenOrientationEnum {
	PORTRAIT("portrait"), // Portrait
	LANDSCAPE("landscape"); // Landscape
	
	private final String value;

	ScreenOrientationEnum(final String enumValue) {
		this.value = enumValue;
	}

	public String ToString() {
		return value;
	}
}