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

/**
 * Class {@code ProductReviews}
 * Reviews of products
 * <p/>
 * XDM Schema Java Object Generated 2020-10-28 12:57:27.575549 -0700 PDT m=+1.788038530 by XDMTool
 *
 * Title		:	Product Reviews
 * Version		:	1.6
 * Type			:	schemas
 */
@SuppressWarnings("unused")
public class ProductReviews implements com.adobe.marketing.mobile.xdm.Schema {
	private Acopprod3 Acopprod3;
	private String eventMergeId;
	private String eventType;
	private IdentityMap identityMap;
	private java.util.Date timestamp;

	public ProductReviews() {}

	/**
	 * Returns the version number of this schema.
	 *
	 * @return the schema version number
	 */
	@Override
	public String getSchemaVersion() {
		return "1.6";
	}

	/**
	 * Returns the unique schema identifier.
	 *
	 * @return the schema ID
	 */
	@Override
	public String getSchemaIdentifier() {
		return "";
	}

	/**
	 * Returns the unique dataset identifier.
	 *
	 * @return the dataset ID
	 */
	 @Override
	 public String getDatasetIdentifier() {
		 return "";
	 }

	@Override
	public Map<String, Object> serializeToXdm() {
		Map<String, Object> map = new HashMap<>();
		if (this.Acopprod3 != null) { map.put("_acopprod3", this.Acopprod3.serializeToXdm()); }
		if (this.eventMergeId != null) { map.put("eventMergeId", this.eventMergeId); }
		if (this.eventType != null) { map.put("eventType", this.eventType); }
		if (this.identityMap != null) { map.put("identityMap", this.identityMap.serializeToXdm()); }
		if (this.timestamp != null) { map.put("timestamp", com.adobe.marketing.mobile.xdm.Formatters.dateToISO8601String(this.timestamp)); }

		return map;
	}

	
	/**
	 * Returns the Acopprod3 property
	 * 
	 * @return {@link Acopprod3} value or null if the property is not set
	 */
	public Acopprod3 getAcopprod3() {
		return this.Acopprod3;
	}

	/**
	 * Sets the Acopprod3 property
	 * 
	 * @param newValue the new Acopprod3 value
	 */
	public void setAcopprod3(final Acopprod3 newValue) {
		this.Acopprod3 = newValue;
	} 

	/**
	 * Returns the ExperienceEvent merge ID property
	 * An ID to correlate or merge multiple Experience events together that are essentially the same event or should be merged. This is intended to be populated by the data producer prior to ingestion.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getEventMergeId() {
		return this.eventMergeId;
	}

	/**
	 * Sets the ExperienceEvent merge ID property
	 * An ID to correlate or merge multiple Experience events together that are essentially the same event or should be merged. This is intended to be populated by the data producer prior to ingestion.
	 * @param newValue the new ExperienceEvent merge ID value
	 */
	public void setEventMergeId(final String newValue) {
		this.eventMergeId = newValue;
	} 
	/**
	 * Returns the Event Type property
	 * The primary event type for this time-series record.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getEventType() {
		return this.eventType;
	}

	/**
	 * Sets the Event Type property
	 * The primary event type for this time-series record.
	 * @param newValue the new Event Type value
	 */
	public void setEventType(final String newValue) {
		this.eventType = newValue;
	} 
	/**
	 * Returns the IdentityMap property
	 * 
	 * @return {@link IdentityMap} value or null if the property is not set
	 */
	public IdentityMap getIdentityMap() {
		return this.identityMap;
	}

	/**
	 * Sets the IdentityMap property
	 * 
	 * @param newValue the new IdentityMap value
	 */
	public void setIdentityMap(final IdentityMap newValue) {
		this.identityMap = newValue;
	} 

	/**
	 * Returns the Timestamp property
	 * The time when an event or observation occurred.
	 * @return {@link java.util.Date} value or null if the property is not set
	 */
	public java.util.Date getTimestamp() {
		return this.timestamp;
	}

	/**
	 * Sets the Timestamp property
	 * The time when an event or observation occurred.
	 * @param newValue the new Timestamp value
	 */
	public void setTimestamp(final java.util.Date newValue) {
		this.timestamp = newValue;
	} 
}

