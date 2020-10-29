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

/**
 * Class {@code Acopprod3}
 * 
 *
 * XDM Property Java Object Generated 2020-10-28 12:57:27.576854 -0700 PDT m=+1.789343655 by XDMTool
 */
@SuppressWarnings("unused")
public class Acopprod3 implements com.adobe.marketing.mobile.xdm.Property {
	private String productId;
	private RatingEnum rating;
	private String reviewerId;
	private String reviewText;
	private String summary;

	public Acopprod3() {}

	@Override
	public Map<String, Object> serializeToXdm() {
		Map<String, Object> map = new HashMap<>();
		if (this.productId != null) { map.put("productId", this.productId); }
		if (this.rating != null) { map.put("rating", this.rating.toString()); }
		if (this.reviewerId != null) { map.put("reviewerId", this.reviewerId); }
		if (this.reviewText != null) { map.put("reviewText", this.reviewText); }
		if (this.summary != null) { map.put("summary", this.summary); }

		return map;
	}
	
	/**
	 * Returns the Product ID property
	 * 
	 * @return {@link String} value or null if the property is not set
	 */
	public String getProductId() {
		return this.productId;
	}

	/**
	 * Sets the Product ID property
	 * 
	 * @param newValue the new Product ID value
	 */
	public void setProductId(final String newValue) {
		this.productId = newValue;
	} 
	/**
	 * Returns the Rating property
	 * Product rating
	 * @return {@link RatingEnum} value or null if the property is not set
	 */
	public RatingEnum getRating() {
		return this.rating;
	}

	/**
	 * Sets the Rating property
	 * Product rating
	 * @param newValue the new Rating value
	 */
	public void setRating(final RatingEnum newValue) {
		this.rating = newValue;
	} 
	/**
	 * Returns the Reviewer ID property
	 * 
	 * @return {@link String} value or null if the property is not set
	 */
	public String getReviewerId() {
		return this.reviewerId;
	}

	/**
	 * Sets the Reviewer ID property
	 * 
	 * @param newValue the new Reviewer ID value
	 */
	public void setReviewerId(final String newValue) {
		this.reviewerId = newValue;
	} 
	/**
	 * Returns the Review Text property
	 * Product review.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getReviewText() {
		return this.reviewText;
	}

	/**
	 * Sets the Review Text property
	 * Product review.
	 * @param newValue the new Review Text value
	 */
	public void setReviewText(final String newValue) {
		this.reviewText = newValue;
	} 
	/**
	 * Returns the Review Summary property
	 * Title or summary of review.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getSummary() {
		return this.summary;
	}

	/**
	 * Sets the Review Summary property
	 * Title or summary of review.
	 * @param newValue the new Review Summary value
	 */
	public void setSummary(final String newValue) {
		this.summary = newValue;
	} 
}
