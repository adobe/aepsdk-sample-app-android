
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
 * XDM Property Java Object Generated 2020-10-05 14:47:08.505714 -0700 PDT m=+2.049186239 by XDMTool
 */
@SuppressWarnings("unused")
public class PlaceContext implements com.adobe.marketing.mobile.xdm.Property {
	private List<ActivePOIsItem> activePOIs;
	private Geo geo;
	private String ianaTimezone;
	private int localTimezoneOffset;
	private java.util.Date localTime;
	private POIinteraction pOIinteraction;

	public PlaceContext() {}

	@Override
	public Map<String, Object> serializeToXdm() {
		Map<String, Object> map = new HashMap<>();
		if (this.activePOIs != null) { map.put("activePOIs", com.adobe.marketing.mobile.xdm.Formatters.serializeFromList(this.activePOIs)); }
		if (this.geo != null) { map.put("geo", this.geo.serializeToXdm()); }
		if (this.ianaTimezone != null) { map.put("ianaTimezone", this.ianaTimezone); }
		map.put("localTimezoneOffset", this.localTimezoneOffset);
		if (this.localTime != null) { map.put("localTime", com.adobe.marketing.mobile.xdm.Formatters.dateToISO8601String(this.localTime)); }
		if (this.pOIinteraction != null) { map.put("POIinteraction", this.pOIinteraction.serializeToXdm()); }

		return map;
	}
	
	/**
	 * Returns the POI detail property
	 * Details of the points of interest (POIs) that cause the event.
	 * @return list of {@link ActivePOIsItem} values or null if the list is not set
	 */
	public List<ActivePOIsItem> getActivePOIs() {
		return this.activePOIs;
	}

	/**
	 * Sets the POI detail property
	 * Details of the points of interest (POIs) that cause the event.
	 * @param newValue the new POI detail value
	 */
	public void setActivePOIs(final List<ActivePOIsItem> newValue) {
		this.activePOIs = newValue;
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
	 * Returns the POI interaction property
	 * The point of interest (POI) interaction details.
	 * @return {@link POIinteraction} value or null if the property is not set
	 */
	public POIinteraction getPOIinteraction() {
		return this.pOIinteraction;
	}

	/**
	 * Sets the POI interaction property
	 * The point of interest (POI) interaction details.
	 * @param newValue the new POI interaction value
	 */
	public void setPOIinteraction(final POIinteraction newValue) {
		this.pOIinteraction = newValue;
	} 
}
