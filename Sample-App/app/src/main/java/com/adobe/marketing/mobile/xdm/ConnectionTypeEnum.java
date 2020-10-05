
package com.adobe.marketing.mobile.xdm;

/**
 * XDM Java Enum Generated 2020-10-05 14:47:08.498596 -0700 PDT m=+2.042068142 by XDMTool
 */
@SuppressWarnings("unused")
public enum ConnectionTypeEnum {
	DIALUP("dialup"), // Dial-up
	ISDN("isdn"), // ISDN
	BISDN("bisdn"), // BISDN
	DSL("dsl"), // DSL
	CABLE("cable"), // Cable
	WIRELESS_WIFI("wireless_wifi"), // Wireless wifi
	MOBILE("mobile"), // Mobile
	MOBILE_EDGE("mobile_edge"), // Mobile Edge
	MOBILE_2G("mobile_2g"), // Mobile 2G
	MOBILE_3G("mobile_3g"), // Mobile 3G
	MOBILE_LTE("mobile_lte"), // Mobile LTE
	T1("t1"), // T1
	T3("t3"), // T3
	OC3("oc3"), // OC3
	LAN("lan"), // LAN
	MODEM("modem"); // Modem
	
	private final String value;

	ConnectionTypeEnum(final String enumValue) {
		this.value = enumValue;
	}

	public String ToString() {
		return value;
	}
}
