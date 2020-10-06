
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
 * Class {@code BeaconInteractionDetails}
 * Beacon details active for the POI interaction.
 *
 * XDM Property Java Object Generated 2020-10-05 14:47:08.504488 -0700 PDT m=+2.047959688 by XDMTool
 */
@SuppressWarnings("unused")
public class BeaconInteractionDetails implements com.adobe.marketing.mobile.xdm.Property {
	private double beaconMajor;
	private double beaconMinor;
	private String proximityUUID;
	private ProximityEnum proximity;

	public BeaconInteractionDetails() {}

	@Override
	public Map<String, Object> serializeToXdm() {
		Map<String, Object> map = new HashMap<>();
		map.put("beaconMajor", this.beaconMajor);
		map.put("beaconMinor", this.beaconMinor);
		if (this.proximityUUID != null) { map.put("proximityUUID", this.proximityUUID); }
		if (this.proximity != null) { map.put("proximity", this.proximity.toString()); }

		return map;
	}
	
	/**
	 * Returns the Beacon major property
	 * Major values identify and distinguish a group and unsigned integer values between 1 and 65,535.
	 * @return double value
	 */
	public double getBeaconMajor() {
		return this.beaconMajor;
	}

	/**
	 * Sets the Beacon major property
	 * Major values identify and distinguish a group and unsigned integer values between 1 and 65,535.
	 * @param newValue the new Beacon major value
	 */
	public void setBeaconMajor(final double newValue) {
		this.beaconMajor = newValue;
	} 
	/**
	 * Returns the Beacon Minor property
	 * Minor values identify and distinguish an individual and unsigned integer values between 1 and 65,535.
	 * @return double value
	 */
	public double getBeaconMinor() {
		return this.beaconMinor;
	}

	/**
	 * Sets the Beacon Minor property
	 * Minor values identify and distinguish an individual and unsigned integer values between 1 and 65,535.
	 * @param newValue the new Beacon Minor value
	 */
	public void setBeaconMinor(final double newValue) {
		this.beaconMinor = newValue;
	} 
	/**
	 * Returns the Proximity UUID property
	 * A proximity UUID (Universally Unique IDentifier) is a special type of identifier used to distinguish beacons in your network from all other beacons in networks outside your control. The proximity UUID is configured into a beacon, to be transmitted to mobile devices in range to identify an organizations beacons.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getProximityUUID() {
		return this.proximityUUID;
	}

	/**
	 * Sets the Proximity UUID property
	 * A proximity UUID (Universally Unique IDentifier) is a special type of identifier used to distinguish beacons in your network from all other beacons in networks outside your control. The proximity UUID is configured into a beacon, to be transmitted to mobile devices in range to identify an organizations beacons.
	 * @param newValue the new Proximity UUID value
	 */
	public void setProximityUUID(final String newValue) {
		this.proximityUUID = newValue;
	} 
	/**
	 * Returns the Proximity to beacon property
	 * Estimated distance from the beacon.
	 * @return {@link ProximityEnum} value or null if the property is not set
	 */
	public ProximityEnum getProximity() {
		return this.proximity;
	}

	/**
	 * Sets the Proximity to beacon property
	 * Estimated distance from the beacon.
	 * @param newValue the new Proximity to beacon value
	 */
	public void setProximity(final ProximityEnum newValue) {
		this.proximity = newValue;
	} 
}
