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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Class representing a list of products in a shopping cart.
 */
public class ProductCart {
	private static final String LOG_TAG = "ProductCart";

	/**
	 * An array of shopping cart items.
	 */
	public static final List<CartItem> ITEMS = new ArrayList<>();

	/**
	 * A map of shopping cart items, by item SKU.
	 */
	public static final Map<String, CartItem> ITEM_MAP = new HashMap<>();

	/**
	 * Add an item to the shopping cart. If {@code item} is not in the shopping cart, it is added.
	 * If {@code item} is already in the shopping cart,
	 * then the given {@code quantity} is added to the existing item's quantity.
	 * @param item the {@link ProductContent.ProductItem} to add to this shopping cart
	 * @param quantity the number of {@code item}s to add
	 */
	public static void addItem(final ProductContent.ProductItem item, final int quantity) {

		// if updating existing cart item quantity, replace in collections
		if (ITEM_MAP.containsKey(item.sku)) {
			CartItem oldItem = ITEM_MAP.get(item.sku);
			int totalQty = quantity + (oldItem == null ? 0 : oldItem.quantity);

			final CartItem newItem = new CartItem(item, totalQty);

			ITEM_MAP.replace(item.sku, newItem);
			ITEMS.replaceAll(new UnaryOperator<CartItem>() {
				@Override
				public CartItem apply(CartItem cartItem) {
					if (cartItem.item.sku.equals(item.sku)) {
						return newItem;
					}

					return cartItem;
				}
			});
		} else {
			final CartItem newCartItem = new CartItem(item, quantity);
			ITEM_MAP.put(item.sku, newCartItem);
			ITEMS.add(newCartItem);
		}

		Log.d(LOG_TAG, String.format("Added item '%s' to cart with quantity %d. Cart now contains %s", item, quantity, ITEMS));
	}

	/**
	 * Removes an item from the shopping cart.
	 * @param item the {@link ProductContent.ProductItem} to remove
	 */
	public static void removeItem(final ProductContent.ProductItem item) {
		ITEM_MAP.remove(item.sku);
		ITEMS.removeIf(new Predicate<CartItem>() {
			@Override
			public boolean test(CartItem cartItem) {
				return cartItem.item.sku.equals(item.sku);
			}
		});

		Log.d(LOG_TAG, String.format("Removed item '%s' from cart. Cart now contains %s", item, ITEMS));
	}

	/**
	 * Removes all items from the shopping cart.
	 */
	public static void clearCart() {
		ITEMS.clear();
		ITEM_MAP.clear();

		Log.d(LOG_TAG, "Shopping cart cleared.");
	}

	/**
	 * Get the total price of all the items in the shopping cart.
	 * @return the sum total of all the items in the cart
	 */
	public static double getTotalPrice() {
		BigDecimal total = new BigDecimal(0);

		for (CartItem item : ITEMS) {
			BigDecimal bdPrice = new BigDecimal(item.item.price);
			BigDecimal bdQuantity = new BigDecimal(item.quantity);
			total = total.add(bdPrice.multiply(bdQuantity));
		}

		return total.setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	/**
	 * Class represents a single shopping cart item.
	 */
	public static class CartItem {
		public final ProductContent.ProductItem item;
		public final int quantity;

		public CartItem(final ProductContent.ProductItem item,
						final int quantity) {
			this.item = item;
			this.quantity = quantity;
		}

		@Override
		public String toString() {
			return item + " (" + quantity + ")";
		}
	}

}
