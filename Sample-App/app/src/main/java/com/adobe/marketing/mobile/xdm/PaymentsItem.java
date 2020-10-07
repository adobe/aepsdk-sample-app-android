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
 * Class {@code PaymentsItem}
 * 
 *
 * XDM Property Java Object Generated 2020-10-06 12:25:38.080441 -0700 PDT m=+2.440648825 by XDMTool
 */
@SuppressWarnings("unused")
public class PaymentsItem implements com.adobe.marketing.mobile.xdm.Property {
	private String currencyCode;
	private double paymentAmount;
	private String paymentType;
	private String transactionID;

	public PaymentsItem() {}

	@Override
	public Map<String, Object> serializeToXdm() {
		Map<String, Object> map = new HashMap<>();
		if (this.currencyCode != null) { map.put("currencyCode", this.currencyCode); }
		map.put("paymentAmount", this.paymentAmount);
		if (this.paymentType != null) { map.put("paymentType", this.paymentType); }
		if (this.transactionID != null) { map.put("transactionID", this.transactionID); }

		return map;
	}
	
	/**
	 * Returns the Currency Code property
	 * The ISO 4217 currency code used for this payment item.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getCurrencyCode() {
		return this.currencyCode;
	}

	/**
	 * Sets the Currency Code property
	 * The ISO 4217 currency code used for this payment item.
	 * @param newValue the new Currency Code value
	 */
	public void setCurrencyCode(final String newValue) {
		this.currencyCode = newValue;
	} 
	/**
	 * Returns the Payment Amount property
	 * The value of the payment.
	 * @return double value
	 */
	public double getPaymentAmount() {
		return this.paymentAmount;
	}

	/**
	 * Sets the Payment Amount property
	 * The value of the payment.
	 * @param newValue the new Payment Amount value
	 */
	public void setPaymentAmount(final double newValue) {
		this.paymentAmount = newValue;
	} 
	/**
	 * Returns the Payment Type property
	 * The method of payment for this order. Enumerated, custom values allowed.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getPaymentType() {
		return this.paymentType;
	}

	/**
	 * Sets the Payment Type property
	 * The method of payment for this order. Enumerated, custom values allowed.
	 * @param newValue the new Payment Type value
	 */
	public void setPaymentType(final String newValue) {
		this.paymentType = newValue;
	} 
	/**
	 * Returns the Transaction ID property
	 * The unique transaction identifier for this payment item.
	 * @return {@link String} value or null if the property is not set
	 */
	public String getTransactionID() {
		return this.transactionID;
	}

	/**
	 * Sets the Transaction ID property
	 * The unique transaction identifier for this payment item.
	 * @param newValue the new Transaction ID value
	 */
	public void setTransactionID(final String newValue) {
		this.transactionID = newValue;
	} 
}
