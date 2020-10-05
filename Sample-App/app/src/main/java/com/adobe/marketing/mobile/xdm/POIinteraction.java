
package com.adobe.marketing.mobile.xdm;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Class {@code POIinteraction}
 * The point of interest (POI) interaction details.
 *
 * XDM Property Java Object Generated 2020-10-05 14:47:08.504713 -0700 PDT m=+2.048184545 by XDMTool
 */
@SuppressWarnings("unused")
public class POIinteraction implements com.adobe.marketing.mobile.xdm.Property {
	private PoiDetail poiDetail;
	private PoiEntries poiEntries;
	private PoiExits poiExits;

	public POIinteraction() {}

	@Override
	public Map<String, Object> serializeToXdm() {
		Map<String, Object> map = new HashMap<>();
		if (this.poiDetail != null) { map.put("poiDetail", this.poiDetail.serializeToXdm()); }
		if (this.poiEntries != null) { map.put("poiEntries", this.poiEntries.serializeToXdm()); }
		if (this.poiExits != null) { map.put("poiExits", this.poiExits.serializeToXdm()); }

		return map;
	}
	
	/**
	 * Returns the POI detail property
	 * Detail of the POI that cause the event.
	 * @return {@link PoiDetail} value or null if the property is not set
	 */
	public PoiDetail getPoiDetail() {
		return this.poiDetail;
	}

	/**
	 * Sets the POI detail property
	 * Detail of the POI that cause the event.
	 * @param newValue the new POI detail value
	 */
	public void setPoiDetail(final PoiDetail newValue) {
		this.poiDetail = newValue;
	} 
	/**
	 * Returns the PoiEntries property
	 * The number of times a person has entered the point of interest (POI).
	 * @return {@link PoiEntries} value or null if the property is not set
	 */
	public PoiEntries getPoiEntries() {
		return this.poiEntries;
	}

	/**
	 * Sets the PoiEntries property
	 * The number of times a person has entered the point of interest (POI).
	 * @param newValue the new PoiEntries value
	 */
	public void setPoiEntries(final PoiEntries newValue) {
		this.poiEntries = newValue;
	} 
	/**
	 * Returns the PoiExits property
	 * The number of times a person has exited the point of interest (POI).
	 * @return {@link PoiExits} value or null if the property is not set
	 */
	public PoiExits getPoiExits() {
		return this.poiExits;
	}

	/**
	 * Sets the PoiExits property
	 * The number of times a person has exited the point of interest (POI).
	 * @param newValue the new PoiExits value
	 */
	public void setPoiExits(final PoiExits newValue) {
		this.poiExits = newValue;
	} 
}
