
package com.adobe.marketing.mobile.xdm;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Class {@code GeoInteractionDetails}
 * Geo details active for the POI interaction.
 *
 * XDM Property Java Object Generated 2020-10-05 14:47:08.503624 -0700 PDT m=+2.047096052 by XDMTool
 */
@SuppressWarnings("unused")
public class GeoInteractionDetails implements com.adobe.marketing.mobile.xdm.Property {
	private double deviceGeoAccuracy;
	private double distanceToCenter;
	private GeoShape geoShape;

	public GeoInteractionDetails() {}

	@Override
	public Map<String, Object> serializeToXdm() {
		Map<String, Object> map = new HashMap<>();
		map.put("deviceGeoAccuracy", this.deviceGeoAccuracy);
		map.put("distanceToCenter", this.distanceToCenter);
		if (this.geoShape != null) { map.put("geoShape", this.geoShape.serializeToXdm()); }

		return map;
	}
	
	/**
	 * Returns the Geo device accuracy property
	 * The accuracy of the geo measuring device or mechanism, measured in meters.
	 * @return double value
	 */
	public double getDeviceGeoAccuracy() {
		return this.deviceGeoAccuracy;
	}

	/**
	 * Sets the Geo device accuracy property
	 * The accuracy of the geo measuring device or mechanism, measured in meters.
	 * @param newValue the new Geo device accuracy value
	 */
	public void setDeviceGeoAccuracy(final double newValue) {
		this.deviceGeoAccuracy = newValue;
	} 
	/**
	 * Returns the Distance to center property
	 * Distance to center of geo in, case of a geo circle, measured in meters.
	 * @return double value
	 */
	public double getDistanceToCenter() {
		return this.distanceToCenter;
	}

	/**
	 * Sets the Distance to center property
	 * Distance to center of geo in, case of a geo circle, measured in meters.
	 * @param newValue the new Distance to center value
	 */
	public void setDistanceToCenter(final double newValue) {
		this.distanceToCenter = newValue;
	} 
	/**
	 * Returns the Geo shape property
	 * Geo shape of the geo being interacted with.
	 * @return {@link GeoShape} value or null if the property is not set
	 */
	public GeoShape getGeoShape() {
		return this.geoShape;
	}

	/**
	 * Sets the Geo shape property
	 * Geo shape of the geo being interacted with.
	 * @param newValue the new Geo shape value
	 */
	public void setGeoShape(final GeoShape newValue) {
		this.geoShape = newValue;
	} 
}
