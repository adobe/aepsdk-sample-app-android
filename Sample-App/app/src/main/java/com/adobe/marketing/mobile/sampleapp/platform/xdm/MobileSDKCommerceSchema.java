/*
 Copyright 2020 Adobe. All rights reserved.

 This file is licensed to you under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License. You may obtain a copy
 of the License at http://www.apache.org/licenses/LICENSE-2.0
 Unless required by applicable law or agreed to in writing, software distributed under
 the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR REPRESENTATIONS
 OF ANY KIND, either express or implied. See the License for the specific language
 governing permissions and limitations under the License.
*/
package com.adobe.marketing.mobile.sampleapp.platform.xdm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class {@code MobileSDKCommerceSchema}
 *
 * <p/>
 * XDM Schema Java Object Generated 2020-07-13 17:09:40.732724 -0700 PDT m=+1.949680949 by XDMTool
 *
 * Title		:	Mobile SDK Commerce Schema
 * Version		:	1.0
 * ID			:	https://ns.adobe.com/acopprod3/schemas/95f430276fa51f45c81234aadc64f4e00dad6753d659345a
 * Alt ID		:	_acopprod3.schemas.95f430276fa51f45c81234aadc64f4e00dad6753d659345a
 * Type			:	schemas
 * IMS Org		:	FAF554945B90342F0A495E2C@AdobeOrg
 */
@SuppressWarnings("unused")
public class MobileSDKCommerceSchema implements com.adobe.marketing.mobile.xdm.Schema {
	private Commerce commerce;
	private String eventMergeId;
	private String eventType;
	private IdentityMap identityMap;
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
		return "1.0";
	}

	/**
	 * Returns the unique schema identifier.
	 *
	 * @return the schema ID
	 */
	@Override
	public String getSchemaIdentifier() {
		return "https://ns.adobe.com/acopprod3/schemas/95f430276fa51f45c81234aadc64f4e00dad6753d659345a";
	}

	/**
	 * Returns the unique dataset identifier.
	 *
	 * @return the dataset ID
	 */
	@Override
	public String getDatasetIdentifier() {
		return "5f05095dba13bf191536d178";
	}

	@Override
	public Map<String, Object> serializeToXdm() {
		Map<String, Object> map = new HashMap<>();

		if (this.commerce != null) {
			map.put("commerce", this.commerce.serializeToXdm());
		}

		if (this.eventMergeId != null) {
			map.put("eventMergeId", this.eventMergeId);
		}

		if (this.eventType != null) {
			map.put("eventType", this.eventType);
		}

		if (this.identityMap != null) {
			map.put("identityMap", this.identityMap.serializeToXdm());
		}

		if (this.productListItems != null) {
			map.put("productListItems", com.adobe.marketing.mobile.xdm.Serializer.serializeFromList(this.productListItems));
		}

		if (this.timestamp != null) {
			map.put("timestamp", com.adobe.marketing.mobile.xdm.Serializer.serializeToISO8601String(this.timestamp));
		}

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

