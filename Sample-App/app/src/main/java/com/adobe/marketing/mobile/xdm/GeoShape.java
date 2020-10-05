
package com.adobe.marketing.mobile.xdm;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Class {@code GeoShape}
 * Geo shape of the geo being interacted with.
 *
 * XDM Property Java Object Generated 2020-10-05 14:47:08.503775 -0700 PDT m=+2.047246590 by XDMTool
 */
@SuppressWarnings("unused")
public class GeoShape implements com.adobe.marketing.mobile.xdm.Property {
	private List<BoxItem> box;
	private double ceiling;
	private Circle circle;
	private String description;
	private double elevation;
	private List<PolygonItem> polygon;

	public GeoShape() {}

	@Override
	public Map<String, Object> serializeToXdm() {
		Map<String, Object> map = new HashMap<>();
		if (this.box != null) { map.put("box", com.adobe.marketing.mobile.xdm.Formatters.serializeFromList(this.box)); }
		map.put("ceiling", this.ceiling);
		if (this.circle != null) { map.put("circle", this.circle.serializeToXdm()); }
		if (this.description != null) { map.put("description", this.description); }
		map.put("elevation", this.elevation);
		if (this.polygon != null) { map.put("polygon", com.adobe.marketing.mobile.xdm.Formatters.serializeFromList(this.polygon)); }

		return map;
	}
	
	/**
	 * Returns the Box property
	 * The area enclosed by the rectangle formed by two coordinates. The first coordinate is the lower corner and the second coordinate is the upper corner of a rectangle.
	 * @return list of {@link BoxItem} values or null if the list is not set
	 */
	public List<BoxItem> getBox() {
		return this.box;
	}

	/**
	 * Sets the Box property
	 * The area enclosed by the rectangle formed by two coordinates. The first coordinate is the lower corner and the second coordinate is the upper corner of a rectangle.
	 * @param newValue the new Box value
	 */
	public void setBox(final List<BoxItem> newValue) {
		this.box = newValue;
	} 
	/**
	 * Returns the Ceiling property
	 * The maximum elevation of the shape. Only valid when used in combination with `elevation`. This value conforms to the [WGS84](http://gisgeography.com/wgs84-world-geodetic-system/) datum and is measured in meters. This value is not part of the schema.org spec. In combination with `elevation`, this property can be used to express a three-dimensional bounding box for a location.
	 * @return double value
	 */
	public double getCeiling() {
		return this.ceiling;
	}

	/**
	 * Sets the Ceiling property
	 * The maximum elevation of the shape. Only valid when used in combination with `elevation`. This value conforms to the [WGS84](http://gisgeography.com/wgs84-world-geodetic-system/) datum and is measured in meters. This value is not part of the schema.org spec. In combination with `elevation`, this property can be used to express a three-dimensional bounding box for a location.
	 * @param newValue the new Ceiling value
	 */
	public void setCeiling(final double newValue) {
		this.ceiling = newValue;
	} 
	/**
	 * Returns the Circle property
	 * A circular region with a specific radius centered on a geographic coordinate.
	 * @return {@link Circle} value or null if the property is not set
	 */
	public Circle getCircle() {
		return this.circle;
	}

	/**
	 * Sets the Circle property
	 * A circular region with a specific radius centered on a geographic coordinate.
	 * @param newValue the new Circle value
	 */
	public void setCircle(final Circle newValue) {
		this.circle = newValue;
	} 
	/**
	 * Returns the Description property
	 * A description of what the shape is defining.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the Description property
	 * A description of what the shape is defining.
	 * @param newValue the new Description value
	 */
	public void setDescription(final String newValue) {
		this.description = newValue;
	} 
	/**
	 * Returns the Elevation property
	 * The specific or minimum elevation of the shape. This value conforms to the [WGS84](http://gisgeography.com/wgs84-world-geodetic-system/) datum and is measured in meters. In combination with `ceiling`, this property can be used to express a three-dimensional bounding box for a location.
	 * @return double value
	 */
	public double getElevation() {
		return this.elevation;
	}

	/**
	 * Sets the Elevation property
	 * The specific or minimum elevation of the shape. This value conforms to the [WGS84](http://gisgeography.com/wgs84-world-geodetic-system/) datum and is measured in meters. In combination with `ceiling`, this property can be used to express a three-dimensional bounding box for a location.
	 * @param newValue the new Elevation value
	 */
	public void setElevation(final double newValue) {
		this.elevation = newValue;
	} 
	/**
	 * Returns the Polygon property
	 * A series of four or more coordinates where the first and final coordinates are identical. In schema.org, this is a plain text. In XDM, it is a structured array instead.
	 * @return list of {@link PolygonItem} values or null if the list is not set
	 */
	public List<PolygonItem> getPolygon() {
		return this.polygon;
	}

	/**
	 * Sets the Polygon property
	 * A series of four or more coordinates where the first and final coordinates are identical. In schema.org, this is a plain text. In XDM, it is a structured array instead.
	 * @param newValue the new Polygon value
	 */
	public void setPolygon(final List<PolygonItem> newValue) {
		this.polygon = newValue;
	} 
}
