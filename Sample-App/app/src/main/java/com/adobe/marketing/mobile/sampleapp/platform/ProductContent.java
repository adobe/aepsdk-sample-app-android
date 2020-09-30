/*
 * Copyright 2019 Adobe
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.adobe.marketing.mobile.sampleapp.platform;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
	 * Initialize a list of {@code ProductItem}s from an {@link JSONArray}.
	 * @param jsonItems
	 */
	public static void initializeProductList(final JSONArray jsonItems) {
		for (int i = 0; i < jsonItems.length(); i++) {

			ProductItem item = null;

			try {
				item = createItem(jsonItems.getJSONObject(i));
				addItem(item);
			} catch (JSONException e) {
				Log.w(LOG_TAG, "Failed to parse a product item from the JSON product list: " + e.getLocalizedMessage());
			}
		}
	}

	/**
	 * Create a new {@code ProductItem} from the given {@link JSONObject}.
	 * @param jsonItem a {@code JSONObject} containing the definition of a product
	 * @return a {@code ProductItem} based on the {@code jsonItem} definition
	 */
	public static ProductItem createItem(final JSONObject jsonItem) {
		if (jsonItem == null) {
			return null;
		}

		String sku = jsonItem.optString("sku", "");
		String name = jsonItem.optString("name", "undefined");
		String details = jsonItem.optString("details", "");
		double price = jsonItem.optDouble("price", 0.0);
		String currency = jsonItem.optString("currency", "USD");
		String imageSmall = jsonItem.optString("imageSmall", "default_small.png");
		String imageLarge = jsonItem.optString("imageLarge", "default_large.png");

		return new ProductItem(
				   sku,
				   name,
				   details,
				   price,
				   currency,
				   imageSmall,
				   imageLarge);
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
		public final String imageLarge;
		public final String imageSmall;

		public ProductItem(final String sku,
						   final String name,
						   final String details,
						   final double price,
						   final String currency,
						   final String imageSmall,
						   final String imageLarge) {
			this.sku = sku;
			this.name = name;
			this.price = price;
			this.currency = currency;
			this.details = details;
			this.imageLarge = imageLarge;
			this.imageSmall = imageSmall;
		}

		@Override
		public String toString() {
			return "(" + sku + ") " + name;
		}
	}
}
