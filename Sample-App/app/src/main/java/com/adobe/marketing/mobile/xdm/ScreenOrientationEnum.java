/*
  Copyright 2020 Adobe
  All Rights Reserved.

  NOTICE: Adobe permits you to use, modify, and distribute this file in
  accordance with the terms of the Adobe license agreement accompanying
  it.
 */
package com.adobe.marketing.mobile.xdm;

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
