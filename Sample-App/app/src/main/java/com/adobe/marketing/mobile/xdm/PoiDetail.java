
package com.adobe.marketing.mobile.xdm;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Class {@code PoiDetail}
 * Detail of the POI that cause the event.
 *
 * XDM Property Java Object Generated 2020-10-05 14:47:08.499096 -0700 PDT m=+2.042568117 by XDMTool
 */
@SuppressWarnings("unused")
public class PoiDetail implements com.adobe.marketing.mobile.xdm.Property {
	private BeaconInteractionDetails beaconInteractionDetails;
	private String category;
	private double distanceToPOICenter;
	private GeoInteractionDetails geoInteractionDetails;
	private String locatingType;
	private String name;
	private String poiID;
	private String type;

	public PoiDetail() {}

	@Override
	public Map<String, Object> serializeToXdm() {
		Map<String, Object> map = new HashMap<>();
		if (this.beaconInteractionDetails != null) { map.put("beaconInteractionDetails", this.beaconInteractionDetails.serializeToXdm()); }
		if (this.category != null) { map.put("category", this.category); }
		map.put("distanceToPOICenter", this.distanceToPOICenter);
		if (this.geoInteractionDetails != null) { map.put("geoInteractionDetails", this.geoInteractionDetails.serializeToXdm()); }
		if (this.locatingType != null) { map.put("locatingType", this.locatingType); }
		if (this.name != null) { map.put("name", this.name); }
		if (this.poiID != null) { map.put("poiID", this.poiID); }
		if (this.type != null) { map.put("type", this.type); }

		return map;
	}
	
	/**
	 * Returns the Beacon interaction details property
	 * Beacon details active for the POI interaction.
	 * @return {@link BeaconInteractionDetails} value or null if the property is not set
	 */
	public BeaconInteractionDetails getBeaconInteractionDetails() {
		return this.beaconInteractionDetails;
	}

	/**
	 * Sets the Beacon interaction details property
	 * Beacon details active for the POI interaction.
	 * @param newValue the new Beacon interaction details value
	 */
	public void setBeaconInteractionDetails(final BeaconInteractionDetails newValue) {
		this.beaconInteractionDetails = newValue;
	} 
	/**
	 * Returns the POI category property
	 * General category assigned for organizing the POIs by the administrator of POI definitions.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getCategory() {
		return this.category;
	}

	/**
	 * Sets the POI category property
	 * General category assigned for organizing the POIs by the administrator of POI definitions.
	 * @param newValue the new POI category value
	 */
	public void setCategory(final String newValue) {
		this.category = newValue;
	} 
	/**
	 * Returns the Distance to POI center property
	 * Estimated distance from the POI center in meters.
	 * @return double value
	 */
	public double getDistanceToPOICenter() {
		return this.distanceToPOICenter;
	}

	/**
	 * Sets the Distance to POI center property
	 * Estimated distance from the POI center in meters.
	 * @param newValue the new Distance to POI center value
	 */
	public void setDistanceToPOICenter(final double newValue) {
		this.distanceToPOICenter = newValue;
	} 
	/**
	 * Returns the Geo interaction details property
	 * Geo details active for the POI interaction.
	 * @return {@link GeoInteractionDetails} value or null if the property is not set
	 */
	public GeoInteractionDetails getGeoInteractionDetails() {
		return this.geoInteractionDetails;
	}

	/**
	 * Sets the Geo interaction details property
	 * Geo details active for the POI interaction.
	 * @param newValue the new Geo interaction details value
	 */
	public void setGeoInteractionDetails(final GeoInteractionDetails newValue) {
		this.geoInteractionDetails = newValue;
	} 
	/**
	 * Returns the Locating type property
	 * Mechanism used to determine location.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getLocatingType() {
		return this.locatingType;
	}

	/**
	 * Sets the Locating type property
	 * Mechanism used to determine location.
	 * @param newValue the new Locating type value
	 */
	public void setLocatingType(final String newValue) {
		this.locatingType = newValue;
	} 
	/**
	 * Returns the POI name property
	 * The name given to the POI.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the POI name property
	 * The name given to the POI.
	 * @param newValue the new POI name value
	 */
	public void setName(final String newValue) {
		this.name = newValue;
	} 
	/**
	 * Returns the POI Identity property
	 * The unique identifier of the POI.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getPoiID() {
		return this.poiID;
	}

	/**
	 * Sets the POI Identity property
	 * The unique identifier of the POI.
	 * @param newValue the new POI Identity value
	 */
	public void setPoiID(final String newValue) {
		this.poiID = newValue;
	} 
	/**
	 * Returns the POI type property
	 * The general type of the POI using a typing schema selected by the administrator of the POI definitions.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Sets the POI type property
	 * The general type of the POI using a typing schema selected by the administrator of the POI definitions.
	 * @param newValue the new POI type value
	 */
	public void setType(final String newValue) {
		this.type = newValue;
	} 
}
