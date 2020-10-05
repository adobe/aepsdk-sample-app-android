
package com.adobe.marketing.mobile.xdm;

/**
 * XDM Java Enum Generated 2020-10-05 14:47:08.498436 -0700 PDT m=+2.041908342 by XDMTool
 */
@SuppressWarnings("unused")
public enum AuthenticatedStateEnum {
	AMBIGUOUS("ambiguous"), // Ambiguous
	AUTHENTICATED("authenticated"), // User identified by a login or similar action that was valid at the time of the event observation.
	LOGGEDOUT("loggedOut"); // User was identified by a login action at some point of time previously, but is not currently logged in.
	
	private final String value;

	AuthenticatedStateEnum(final String enumValue) {
		this.value = enumValue;
	}

	public String ToString() {
		return value;
	}
}
