
package com.adobe.marketing.mobile.xdm;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Class {@code Circle}
 * A circular region with a specific radius centered on a geographic coordinate.
 *
 * XDM Property Java Object Generated 2020-10-05 14:47:08.506105 -0700 PDT m=+2.049577128 by XDMTool
 */
@SuppressWarnings("unused")
public class Circle implements com.adobe.marketing.mobile.xdm.Property {
	private Coordinates coordinates;
	private String description;
	private double radius;

	public Circle() {}

	@Override
	public Map<String, Object> serializeToXdm() {
		Map<String, Object> map = new HashMap<>();
		if (this.coordinates != null) { map.put("coordinates", this.coordinates.serializeToXdm()); }
		if (this.description != null) { map.put("description", this.description); }
		map.put("radius", this.radius);

		return map;
	}
	
	/**
	 * Returns the Coordinates property
	 * 
	 * @return {@link Coordinates} value or null if the property is not set
	 */
	public Coordinates getCoordinates() {
		return this.coordinates;
	}

	/**
	 * Sets the Coordinates property
	 * 
	 * @param newValue the new Coordinates value
	 */
	public void setCoordinates(final Coordinates newValue) {
		this.coordinates = newValue;
	} 
	/**
	 * Returns the Description property
	 * A description of what the circle contains.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the Description property
	 * A description of what the circle contains.
	 * @param newValue the new Description value
	 */
	public void setDescription(final String newValue) {
		this.description = newValue;
	} 
	/**
	 * Returns the Radius property
	 * The length of the radius of the circle. This value conforms to the WGS84 datum and is measured in meters.
	 * @return double value
	 */
	public double getRadius() {
		return this.radius;
	}

	/**
	 * Sets the Radius property
	 * The length of the radius of the circle. This value conforms to the WGS84 datum and is measured in meters.
	 * @param newValue the new Radius value
	 */
	public void setRadius(final double newValue) {
		this.radius = newValue;
	} 
}
