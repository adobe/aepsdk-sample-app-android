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
 * Class {@code PlaceContext}
 * The transient circumstances related to the observation. Examples include locale specific information such as weather, local time, traffic, day of the week, workday vs. holiday, and working hours.
 *
 * XDM Property Java Object Generated 2020-10-06 12:25:38.081175 -0700 PDT m=+2.441383199 by XDMTool
 */
@SuppressWarnings("unused")
public class PlaceContext implements com.adobe.marketing.mobile.xdm.Property {
	private Geo geo;
	private String ianaTimezone;
	private java.util.Date localTime;
	private int localTimezoneOffset;

	public PlaceContext() {}

	@Override
	public Map<String, Object> serializeToXdm() {
		Map<String, Object> map = new HashMap<>();
		if (this.geo != null) { map.put("geo", this.geo.serializeToXdm()); }
		if (this.ianaTimezone != null) { map.put("ianaTimezone", this.ianaTimezone); }
		if (this.localTime != null) { map.put("localTime", com.adobe.marketing.mobile.xdm.Formatters.dateToISO8601String(this.localTime)); }
		map.put("localTimezoneOffset", this.localTimezoneOffset);

		return map;
	}
	
	/**
	 * Returns the Geo property
	 * The geographic location where the experience was delivered.
	 * @return {@link Geo} value or null if the property is not set
	 */
	public Geo getGeo() {
		return this.geo;
	}

	/**
	 * Sets the Geo property
	 * The geographic location where the experience was delivered.
	 * @param newValue the new Geo value
	 */
	public void setGeo(final Geo newValue) {
		this.geo = newValue;
	} 
	/**
	 * Returns the Iana Timezone property
	 * Timezone of the device represented as a standard timezone string. https://en.wikipedia.org/wiki/List_of_tz_database_time_zones.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getIanaTimezone() {
		return this.ianaTimezone;
	}

	/**
	 * Sets the Iana Timezone property
	 * Timezone of the device represented as a standard timezone string. https://en.wikipedia.org/wiki/List_of_tz_database_time_zones.
	 * @param newValue the new Iana Timezone value
	 */
	public void setIanaTimezone(final String newValue) {
		this.ianaTimezone = newValue;
	} 
	/**
	 * Returns the Local time property
	 * The local time using RFC3339 with a stated time zone offset such as "2001-07-04T12:08:56-07:00". An example formatting pattern is "yyyy-MM-dd'T'HH:mm:ssXXX".
	 * @return {@link java.util.Date} value or null if the property is not set
	 */
	public java.util.Date getLocalTime() {
		return this.localTime;
	}

	/**
	 * Sets the Local time property
	 * The local time using RFC3339 with a stated time zone offset such as "2001-07-04T12:08:56-07:00". An example formatting pattern is "yyyy-MM-dd'T'HH:mm:ssXXX".
	 * @param newValue the new Local time value
	 */
	public void setLocalTime(final java.util.Date newValue) {
		this.localTime = newValue;
	} 
	/**
	 * Returns the Local time zone offset property
	 * The current, local time zone offset in minutes from UTC for the localTime in this object.  This will include the current DST offset if applicable.
	 * @return int value
	 */
	public int getLocalTimezoneOffset() {
		return this.localTimezoneOffset;
	}

	/**
	 * Sets the Local time zone offset property
	 * The current, local time zone offset in minutes from UTC for the localTime in this object.  This will include the current DST offset if applicable.
	 * @param newValue the new Local time zone offset value
	 */
	public void setLocalTimezoneOffset(final int newValue) {
		this.localTimezoneOffset = newValue;
	} 
}
