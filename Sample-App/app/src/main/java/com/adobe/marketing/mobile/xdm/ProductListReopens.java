
package com.adobe.marketing.mobile.xdm;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Class {@code ProductListReopens}
 * A product list that was no longer accessible (abandoned) has been re-activated by the user. Example via a re-marketing activity.
 *
 * XDM Property Java Object Generated 2020-10-05 14:47:08.50169 -0700 PDT m=+2.045161918 by XDMTool
 */
@SuppressWarnings("unused")
public class ProductListReopens implements com.adobe.marketing.mobile.xdm.Property {
	private String id;
	private double value;

	public ProductListReopens() {}

	@Override
	public Map<String, Object> serializeToXdm() {
		Map<String, Object> map = new HashMap<>();
		if (this.id != null) { map.put("id", this.id); }
		map.put("value", this.value);

		return map;
	}
	
	/**
	 * Returns the Unique Identifier property
	 * Unique identifier of the measure. In cases of data collection using lossy communication channels, such as mobile apps or websites with offline functionality, where transmission of measures cannot be ensured, this property contains a client-generated, unique ID of the measure taken. It is best practice to make this sufficiently long to ensure enough entropy. Additionally, if information such as time stamp, device ID, IP, or MAC address, or other potentially user-identifying values are incorporated in the generation of the xdm:id, the result should be hashed, so that no PII is encoded in the value, as the goal is not to identify user or device, but the specific measure in time.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Sets the Unique Identifier property
	 * Unique identifier of the measure. In cases of data collection using lossy communication channels, such as mobile apps or websites with offline functionality, where transmission of measures cannot be ensured, this property contains a client-generated, unique ID of the measure taken. It is best practice to make this sufficiently long to ensure enough entropy. Additionally, if information such as time stamp, device ID, IP, or MAC address, or other potentially user-identifying values are incorporated in the generation of the xdm:id, the result should be hashed, so that no PII is encoded in the value, as the goal is not to identify user or device, but the specific measure in time.
	 * @param newValue the new Unique Identifier value
	 */
	public void setId(final String newValue) {
		this.id = newValue;
	} 
	/**
	 * Returns the Value property
	 * The quantifiable value of this measure.
	 * @return double value
	 */
	public double getValue() {
		return this.value;
	}

	/**
	 * Sets the Value property
	 * The quantifiable value of this measure.
	 * @param newValue the new Value value
	 */
	public void setValue(final double newValue) {
		this.value = newValue;
	} 
}