/*
  Copyright 2020 Adobe
  All Rights Reserved.

  NOTICE: Adobe permits you to use, modify, and distribute this file in
  accordance with the terms of the Adobe license agreement accompanying
  it.
 */
package com.adobe.marketing.mobile.xdm;

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
