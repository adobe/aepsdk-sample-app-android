
package com.adobe.marketing.mobile.xdm;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Class {@code Coordinates}
 * 
 *
 * XDM Property Java Object Generated 2020-10-05 14:47:08.504019 -0700 PDT m=+2.047491300 by XDMTool
 */
@SuppressWarnings("unused")
public class Coordinates implements com.adobe.marketing.mobile.xdm.Property {
	private String description;
	private double elevation;
	private double latitude;
	private double longitude;

	public Coordinates() {}

	@Override
	public Map<String, Object> serializeToXdm() {
		Map<String, Object> map = new HashMap<>();
		if (this.description != null) { map.put("description", this.description); }
		map.put("elevation", this.elevation);
		map.put("latitude", this.latitude);
		map.put("longitude", this.longitude);

		return map;
	}
	
	/**
	 * Returns the Description property
	 * A description of what the coordinates identify.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the Description property
	 * A description of what the coordinates identify.
	 * @param newValue the new Description value
	 */
	public void setDescription(final String newValue) {
		this.description = newValue;
	} 
	/**
	 * Returns the Elevation property
	 * The specific elevation of the defined coordinate. The value conforms to the [WGS84](http://gisgeography.com/wgs84-world-geodetic-system/) datum and is measured in meters.
	 * @return double value
	 */
	public double getElevation() {
		return this.elevation;
	}

	/**
	 * Sets the Elevation property
	 * The specific elevation of the defined coordinate. The value conforms to the [WGS84](http://gisgeography.com/wgs84-world-geodetic-system/) datum and is measured in meters.
	 * @param newValue the new Elevation value
	 */
	public void setElevation(final double newValue) {
		this.elevation = newValue;
	} 
	/**
	 * Returns the Latitude property
	 * The signed vertical coordinate of a geographic point.
	 * @return double value
	 */
	public double getLatitude() {
		return this.latitude;
	}

	/**
	 * Sets the Latitude property
	 * The signed vertical coordinate of a geographic point.
	 * @param newValue the new Latitude value
	 */
	public void setLatitude(final double newValue) {
		this.latitude = newValue;
	} 
	/**
	 * Returns the Longitude property
	 * The signed horizontal coordinate of a geographic point.
	 * @return double value
	 */
	public double getLongitude() {
		return this.longitude;
	}

	/**
	 * Sets the Longitude property
	 * The signed horizontal coordinate of a geographic point.
	 * @param newValue the new Longitude value
	 */
	public void setLongitude(final double newValue) {
		this.longitude = newValue;
	} 
}
