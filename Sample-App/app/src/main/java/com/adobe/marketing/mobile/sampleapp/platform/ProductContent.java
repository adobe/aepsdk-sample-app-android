/*
 Copyright 2020 Adobe
 All Rights Reserved.

 NOTICE: Adobe permits you to use, modify, and distribute this file in
 accordance with the terms of the Adobe license agreement accompanying
 it.
 */

package com.adobe.marketing.mobile.sampleapp.platform;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing items for sale in a product listing.
 */
public class ProductContent {
	private static final String LOG_TAG = "ProductContent";

	private ProductContent() {}

	/**
	 * An array of product items.
	 */
	public static final List<ProductItem> ITEMS = new ArrayList<>();

	/**
	 * A map of product items, by ID.
	 */
	public static final Map<String, ProductItem> ITEM_MAP = new HashMap<>();

	/**
	 * Add a {@code ProductItem} to this list of items. Null items are ignored.
	 * @param item the {@link ProductItem} to add
	 */
	public static void addItem(final ProductItem item) {
		if (item == null) {
			return;
		}

		Log.v(LOG_TAG, "Item added to product list '" + item + "'");

		ITEMS.add(item);
		ITEM_MAP.put(item.sku, item);
	}

	/**
	 * A product items representing a piece of content in the product list.
	 */
	public static class ProductItem {
		public final String sku;
		public final String name;
		public final double price;
		public final String currency;
		public final String details;
		public final int imageColor;

		public ProductItem(final String sku,
						   final String name,
						   final String details,
						   final double price,
						   final String currency,
						   final int imageColor) {
			this.sku = sku;
			this.name = name;
			this.price = price;
			this.currency = currency;
			this.details = details;
			this.imageColor = imageColor;
		}

		@Override
		public String toString() {
			return "(" + sku + ") " + name;
		}
	}
}
