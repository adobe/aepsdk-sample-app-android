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
 * Class {@code Items}
 * 
 *
 * XDM Property Java Object Generated 2020-10-06 12:25:38.079882 -0700 PDT m=+2.440089981 by XDMTool
 */
@SuppressWarnings("unused")
public class Items implements com.adobe.marketing.mobile.xdm.Property {
	private AuthenticatedStateEnum authenticatedState;
	private String id;
	private boolean primary;

	public Items() {}

	@Override
	public Map<String, Object> serializeToXdm() {
		Map<String, Object> map = new HashMap<>();
		if (this.authenticatedState != null) { map.put("authenticatedState", this.authenticatedState.toString()); }
		if (this.id != null) { map.put("id", this.id); }
		map.put("primary", this.primary);

		return map;
	}
	
	/**
	 * Returns the AuthenticatedState property
	 * The state this identity is authenticated as for this observed ExperienceEvent.
	 * @return {@link AuthenticatedStateEnum} value or null if the property is not set
	 */
	public AuthenticatedStateEnum getAuthenticatedState() {
		return this.authenticatedState;
	}

	/**
	 * Sets the AuthenticatedState property
	 * The state this identity is authenticated as for this observed ExperienceEvent.
	 * @param newValue the new AuthenticatedState value
	 */
	public void setAuthenticatedState(final AuthenticatedStateEnum newValue) {
		this.authenticatedState = newValue;
	} 
	/**
	 * Returns the Identifier property
	 * Identity of the consumer in the related namespace.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Sets the Identifier property
	 * Identity of the consumer in the related namespace.
	 * @param newValue the new Identifier value
	 */
	public void setId(final String newValue) {
		this.id = newValue;
	} 
	/**
	 * Returns the Primary property
	 * Indicates this identity is the preferred identity. Is used as a hint to help systems better organize how identities are queried.
	 * @return boolean value
	 */
	public boolean getPrimary() {
		return this.primary;
	}

	/**
	 * Sets the Primary property
	 * Indicates this identity is the preferred identity. Is used as a hint to help systems better organize how identities are queried.
	 * @param newValue the new Primary value
	 */
	public void setPrimary(final boolean newValue) {
		this.primary = newValue;
	} 
}
