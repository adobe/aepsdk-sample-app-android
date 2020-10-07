/*
  Copyright 2020 Adobe
  All Rights Reserved.

  NOTICE: Adobe permits you to use, modify, and distribute this file in
  accordance with the terms of the Adobe license agreement accompanying
  it.
 */

package com.adobe.marketing.mobile.xdm;

/**
 * XDM Java Enum Generated 2020-10-06 12:25:38.077894 -0700 PDT m=+2.438102237 by XDMTool
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
