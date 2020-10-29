/*
  Copyright 2020 Adobe
  All Rights Reserved.

  NOTICE: Adobe permits you to use, modify, and distribute this file in
  accordance with the terms of the Adobe license agreement accompanying
  it.
 */

package com.adobe.marketing.mobile.xdm;

/**
 * XDM Java Enum Generated 2020-10-28 12:57:27.576626 -0700 PDT m=+1.789115853 by XDMTool
 */
@SuppressWarnings("unused")
public enum RatingEnum {
	RUBBISH("1"), // rubbish
	OKAY("2"), // okay
	GOOD("3"), // good
	GREAT("4"), // great
	FANTASTIC("5"); // super-fantastic
	
	private final String value;

	RatingEnum(final String enumValue) {
		this.value = enumValue;
	}

	@Override
	public String toString() {
		return value;
	}
}
