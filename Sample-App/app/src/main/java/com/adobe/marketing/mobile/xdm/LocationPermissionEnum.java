/*
  Copyright 2020 Adobe
  All Rights Reserved.

  NOTICE: Adobe permits you to use, modify, and distribute this file in
  accordance with the terms of the Adobe license agreement accompanying
  it.
 */

package com.adobe.marketing.mobile.xdm;

/**
 * XDM Java Enum Generated 2020-10-06 12:25:38.077961 -0700 PDT m=+2.438168859 by XDMTool
 */
@SuppressWarnings("unused")
public enum LocationPermissionEnum {
	SYSTEM_LOCATION_DISABLED("SYSTEM_LOCATION_DISABLED"), // 
	NOT_ALLOWED("NOT_ALLOWED"), // 
	ALWAYS_ALLOWED("ALWAYS_ALLOWED"), // 
	FOREGROUND_ALLOWED("FOREGROUND_ALLOWED"), // 
	UNPROMPTED("UNPROMPTED"); // 
	
	private final String value;

	LocationPermissionEnum(final String enumValue) {
		this.value = enumValue;
	}

	public String ToString() {
		return value;
	}
}
