
/*
  Copyright 2020 Adobe
  All Rights Reserved.

  NOTICE: Adobe permits you to use, modify, and distribute this file in
  accordance with the terms of the Adobe license agreement accompanying
  it.
 */

package com.adobe.marketing.mobile.xdm;

/**
 * XDM Java Enum Generated 2020-10-05 14:47:08.498481 -0700 PDT m=+2.041952896 by XDMTool
 */
@SuppressWarnings("unused")
public enum ProximityEnum {
	IMMEDIATE("immediate"), // Within a few centimeter.
	NEAR("near"), // Within a couple of meters.
	FAR("far"), // Greater than 10 meters away.
	UNKNOWN("unknown"); // Not able to ascertain distance, signal likely very weak.
	
	private final String value;

	ProximityEnum(final String enumValue) {
		this.value = enumValue;
	}

	public String ToString() {
		return value;
	}
}
