/*
  Copyright 2020 Adobe
  All Rights Reserved.

  NOTICE: Adobe permits you to use, modify, and distribute this file in
  accordance with the terms of the Adobe license agreement accompanying
  it.
 */

package com.adobe.marketing.mobile.xdm;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Class {@code IdentityMap}
 * 
 *
 * XDM Property Java Object Generated 2020-10-06 12:25:38.079811 -0700 PDT m=+2.440019387 by XDMTool
 */
@SuppressWarnings("unused")
public class IdentityMap implements com.adobe.marketing.mobile.xdm.Property {
	private Items items;

	public IdentityMap() {}

	@Override
	public Map<String, Object> serializeToXdm() {
		Map<String, Object> map = new HashMap<>();
		if (this.items != null) { map.put("items", this.items.serializeToXdm()); }

		return map;
	}
	
	/**
	 * Returns the Items property
	 * 
	 * @return {@link Items} value or null if the property is not set
	 */
	public Items getItems() {
		return this.items;
	}

	/**
	 * Sets the Items property
	 * 
	 * @param newValue the new Items value
	 */
	public void setItems(final Items newValue) {
		this.items = newValue;
	} 
}
