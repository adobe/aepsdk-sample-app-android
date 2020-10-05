
package com.adobe.marketing.mobile.xdm;

/**
 * XDM Java Enum Generated 2020-10-05 14:47:08.491951 -0700 PDT m=+2.035423350 by XDMTool
 */
@SuppressWarnings("unused")
public enum TypeEnum {
	BROWSER("browser"), // Browser
	APPLICATION("application"), // Application
	IOT("iot"), // Internet of things
	EXTERNAL("external"), // External system
	WIDGET("widget"); // Application extension
	
	private final String value;

	TypeEnum(final String enumValue) {
		this.value = enumValue;
	}

	public String ToString() {
		return value;
	}
}
