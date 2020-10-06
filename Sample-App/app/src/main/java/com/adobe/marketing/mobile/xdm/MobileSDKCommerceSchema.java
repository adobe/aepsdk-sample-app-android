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
 * Class {@code MobileSDKCommerceSchema}
 * 
 * <p/>
 * XDM Schema Java Object Generated 2020-10-06 12:25:38.077002 -0700 PDT m=+2.437209621 by XDMTool
 *
 * Title		:	Mobile SDK Commerce Schema
 * Version		:	1.1
 * Type			:	schemas
 */
@SuppressWarnings("unused")
public class MobileSDKCommerceSchema implements com.adobe.marketing.mobile.xdm.Schema {
	private Commerce commerce;
	private Device device;
	private Environment environment;
	private String eventMergeId;
	private String eventType;
	private IdentityMap identityMap;
	private PlaceContext placeContext;
	private List<ProductListItemsItem> productListItems;
	private java.util.Date timestamp;

	public MobileSDKCommerceSchema() {}

	/**
	 * Returns the version number of this schema.
	 *
	 * @return the schema version number
	 */
	@Override
	public String getSchemaVersion() {
		return "1.1";
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
		if (this.commerce != null) { map.put("commerce", this.commerce.serializeToXdm()); }
		if (this.device != null) { map.put("device", this.device.serializeToXdm()); }
		if (this.environment != null) { map.put("environment", this.environment.serializeToXdm()); }
		if (this.eventMergeId != null) { map.put("eventMergeId", this.eventMergeId); }
		if (this.eventType != null) { map.put("eventType", this.eventType); }
		if (this.identityMap != null) { map.put("identityMap", this.identityMap.serializeToXdm()); }
		if (this.placeContext != null) { map.put("placeContext", this.placeContext.serializeToXdm()); }
		if (this.productListItems != null) { map.put("productListItems", com.adobe.marketing.mobile.xdm.Formatters.serializeFromList(this.productListItems)); }
		if (this.timestamp != null) { map.put("timestamp", com.adobe.marketing.mobile.xdm.Formatters.dateToISO8601String(this.timestamp)); }

		return map;
	}

	
	/**
	 * Returns the Commerce property
	 * Commerce specific data related to this event.
	 * @return {@link Commerce} value or null if the property is not set
	 */
	public Commerce getCommerce() {
		return this.commerce;
	}

	/**
	 * Sets the Commerce property
	 * Commerce specific data related to this event.
	 * @param newValue the new Commerce value
	 */
	public void setCommerce(final Commerce newValue) {
		this.commerce = newValue;
	} 
	/**
	 * Returns the Device property
	 * An identified device, application or device browser instance that is trackable across sessions, normally by cookies.
	 * @return {@link Device} value or null if the property is not set
	 */
	public Device getDevice() {
		return this.device;
	}

	/**
	 * Sets the Device property
	 * An identified device, application or device browser instance that is trackable across sessions, normally by cookies.
	 * @param newValue the new Device value
	 */
	public void setDevice(final Device newValue) {
		this.device = newValue;
	} 
	/**
	 * Returns the Environment property
	 * Information about the surrounding situation the event observation occurred in, specifically detailing transitory information such as the network or software versions.
	 * @return {@link Environment} value or null if the property is not set
	 */
	public Environment getEnvironment() {
		return this.environment;
	}

	/**
	 * Sets the Environment property
	 * Information about the surrounding situation the event observation occurred in, specifically detailing transitory information such as the network or software versions.
	 * @param newValue the new Environment value
	 */
	public void setEnvironment(final Environment newValue) {
		this.environment = newValue;
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
	 * Returns the Place context property
	 * The transient circumstances related to the observation. Examples include locale specific information such as weather, local time, traffic, day of the week, workday vs. holiday, and working hours.
	 * @return {@link PlaceContext} value or null if the property is not set
	 */
	public PlaceContext getPlaceContext() {
		return this.placeContext;
	}

	/**
	 * Sets the Place context property
	 * The transient circumstances related to the observation. Examples include locale specific information such as weather, local time, traffic, day of the week, workday vs. holiday, and working hours.
	 * @param newValue the new Place context value
	 */
	public void setPlaceContext(final PlaceContext newValue) {
		this.placeContext = newValue;
	} 
	/**
	 * Returns the Product list items property
	 * A list of items representing a product selected by a customer with specific options and pricing that are for that usage context at a specific point of time and may differ from the product record.
	 * @return list of {@link ProductListItemsItem} values or null if the list is not set
	 */
	public List<ProductListItemsItem> getProductListItems() {
		return this.productListItems;
	}

	/**
	 * Sets the Product list items property
	 * A list of items representing a product selected by a customer with specific options and pricing that are for that usage context at a specific point of time and may differ from the product record.
	 * @param newValue the new Product list items value
	 */
	public void setProductListItems(final List<ProductListItemsItem> newValue) {
		this.productListItems = newValue;
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

