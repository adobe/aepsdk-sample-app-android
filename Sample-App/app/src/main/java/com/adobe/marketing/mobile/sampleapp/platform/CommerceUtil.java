/*
 Copyright 2020 Adobe
 All Rights Reserved.

 NOTICE: Adobe permits you to use, modify, and distribute this file in
 accordance with the terms of the Adobe license agreement accompanying
 it.
 */

package com.adobe.marketing.mobile.sampleapp.platform;

import android.util.Log;

import com.adobe.marketing.mobile.ExperiencePlatform;
import com.adobe.marketing.mobile.ExperiencePlatformCallback;
import com.adobe.marketing.mobile.ExperiencePlatformEvent;
import com.adobe.marketing.mobile.xdm.Commerce;
import com.adobe.marketing.mobile.xdm.MobileSDKCommerceSchema;
import com.adobe.marketing.mobile.xdm.Order;
import com.adobe.marketing.mobile.xdm.PaymentsItem;
import com.adobe.marketing.mobile.xdm.ProductListAdds;
import com.adobe.marketing.mobile.xdm.ProductListItemsItem;
import com.adobe.marketing.mobile.xdm.ProductListRemovals;
import com.adobe.marketing.mobile.xdm.Purchases;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class encapsulates logic for creating {@link Commerce} objects and sending as
 * {@link ExperiencePlatformEvent}s to the Experience Platform Extension.
 * <p/>
 * The methods in this class are called throughout the commerce workflow to send information
 * to the Adobe Data Platform for user actions such as viewing product items, adding items to
 * a shopping cart, and purchasing items in a shopping cart, for example.
 * <p/>
 * The methods are provided as an example usage and may not be indicative of every application's
 * use case. For example, a {@code commerce.purchases} action sets {@link Order} and {@link PaymentsItem}
 * from hardcoded values for simplicity.
 * <p/>
 * Please review the XDM documentation for the
 * <a href="https://github.com/adobe/xdm/blob/master/docs/reference/context/experienceevent-commerce.schema.md">Experience Event Commerce Schema</a>
 *  Mixin for more information.
 */
public class CommerceUtil {
	private static final String LOG_TAG = "CommerceUtil";

	// US dollar currency code
	private static final String CURRENCY_CODE_USD = "USD";

	/**
	 * Addition of a product to the product list. Example a product is added to a shopping cart.
	 * See <a href="https://github.com/adobe/xdm/blob/master/docs/reference/context/experienceevent.schema.md#xdmeventtype">Experience Event</a>
	 */
	private static final String EVENT_TYPE_COMMERCE_PRODUCT_LIST_ADDS       = "commerce.productListAdds";

	/**
	 * Removal(s) of a product entry from a product list. Example a product is removed from a
	 * shopping cart.
	 * See <a href="https://github.com/adobe/xdm/blob/master/docs/reference/context/experienceevent.schema.md#xdmeventtype">Experience Event</a>
	 */
	private static final String EVENT_TYPE_COMMERCE_PRODUCT_LIST_REMOVALS   = "commerce.productListRemovals";

	/**
	 * An order has been accepted. Purchase is the only required action in a commerce conversion.
	 * Purchase must have a product list referenced.
	 * See <a href="https://github.com/adobe/xdm/blob/master/docs/reference/context/experienceevent.schema.md#xdmeventtype">Experience Event</a>
	 */
	private static final String EVENT_TYPE_COMMERCE_PURCHASES               = "commerce.purchases";

	/**
	 * Creates and sends a product list add event to the Adobe Data Platform.
	 * A {@code commerce.productListAdds} event is a {@link Commerce} object with
	 * {@link Commerce#setProductListAdds(ProductListAdds)}
	 * set, along with a {@link ProductListItemsItem} list containing the details of the
	 * added product.
	 *
	 * This method should be called when a product is added to a shopping cart.
	 *
	 * @param item the {@link ProductContent.ProductItem} which was added
	 * @param quantity the number of product items added
	 */
	public static void sendProductListAddXdmEvent(final ProductContent.ProductItem item, final int quantity) {
		Log.d(LOG_TAG, "sendProductListAddXdmEvent with item '" + item + "' and quantity " + quantity);

		ProductListItemsItem productItem = createProductListItemsItem(item, quantity);

		if (productItem == null) {
			Log.w(LOG_TAG, "sendProductListAddXdmEvent - Cannot create '" + EVENT_TYPE_COMMERCE_PRODUCT_LIST_ADDS +
				  "' event as given product item is null.");
			return;
		}

		productItem.setProductAddMethod("add to cart button");

		List<ProductListItemsItem> itemsList = new ArrayList<>();
		itemsList.add(productItem);

		createAndSendEvent(itemsList, EVENT_TYPE_COMMERCE_PRODUCT_LIST_ADDS);
	}

	/**
	 * Creates and sends a product list remove event to the Adobe Data Platform.
	 * A {@code commerce.productListAdds} event is a {@link Commerce} object with
	 * {@link Commerce#setProductListRemovals(ProductListRemovals)}
	 * set, along with a {@link ProductListItemsItem} list containing the details of the
	 * removed product.
	 *
	 * This method should be called when a product is removed from a shopping cart.
	 *
	 * @param item the {@link ProductContent.ProductItem} which was removed
	 * @param quantity the number of product items removed
	 */
	public static void sendProductListRemoveXdmEvent(final ProductContent.ProductItem item, final int quantity) {
		Log.d(LOG_TAG, "sendProductListRemoveXdmEvent with item '" + item + "'");

		ProductListItemsItem productItem = createProductListItemsItem(item, quantity);

		if (productItem == null) {
			Log.w(LOG_TAG, "sendProductListRemoveXdmEvent - Cannot create '" + EVENT_TYPE_COMMERCE_PRODUCT_LIST_REMOVALS +
				  "' event as given product item is null.");
			return;
		}

		List<ProductListItemsItem> itemsList = new ArrayList<>();
		itemsList.add(productItem);

		createAndSendEvent(itemsList, EVENT_TYPE_COMMERCE_PRODUCT_LIST_REMOVALS);
	}

	/**
	 * Creates and sends a cart purchase event to the Adobe Data Platform.
	 * A {@code commerce.purchases} event is a {@link Commerce} object with
	 * {@link Commerce#setPurchases(Purchases)} and {@link Commerce#setOrder(Order)}
	 * set, along with a {@link ProductListItemsItem} list containing the details of all the
	 * products in the shopping cart. The {@link Order} details the total cost and payment of the purchase.
	 *
	 * This method should be called when a final purchase is made of a shopping cart.
	 *
	 * @param paymentType the type of payment, for example 'cash' or 'credit_cart'
	 * @param orderTotal the total price of the purchase order
	 */
	public static void sendPurchaseXdmEvent(final String paymentType, final double orderTotal) {
		Log.d(LOG_TAG, "sendPurchaseXdmEvent with payment type '" + paymentType + "'");

		// create list of all products in shopping cart
		List<ProductListItemsItem> itemsList = new ArrayList<>();
		ProductListItemsItem productItem;

		for (ProductCart.CartItem cartItem : ProductCart.ITEMS) {
			productItem = createProductListItemsItem(cartItem.item, cartItem.quantity);

			if (productItem != null) {
				itemsList.add(productItem);
			}
		}

		if (itemsList.isEmpty()) {
			Log.w(LOG_TAG, "sendPurchaseXdmEvent - Cannot create '" + EVENT_TYPE_COMMERCE_PURCHASES +
				  "' as no items were found in cart.");
			return;
		}

		// Create PaymentItem which details the method of payment
		PaymentsItem paymentsItem = new PaymentsItem();
		paymentsItem.setCurrencyCode(CURRENCY_CODE_USD);
		paymentsItem.setPaymentAmount(orderTotal);
		paymentsItem.setPaymentType(paymentType);

		List<PaymentsItem> paymentsItemList = new ArrayList<>();
		paymentsItemList.add(paymentsItem);

		// create the Order
		Order order = new Order();
		order.setCurrencyCode(CURRENCY_CODE_USD);
		order.setPriceTotal(orderTotal);
		order.setPayments(paymentsItemList);

		// create Commerce and add Purchases action and Order details
		Commerce commerce = new Commerce();
		Purchases purchases = new Purchases();
		purchases.setValue(1);
		commerce.setPurchases(purchases);
		commerce.setOrder(order);

		// Create top-level event and add Commerce and ProductList
		MobileSDKCommerceSchema xdmData = new MobileSDKCommerceSchema();
		xdmData.setEventType(EVENT_TYPE_COMMERCE_PURCHASES);
		xdmData.setCommerce(commerce);
		xdmData.setProductListItems(itemsList);

		ExperiencePlatformEvent event = new ExperiencePlatformEvent.Builder()
		.setXdmSchema(xdmData)
		.build();

		ExperiencePlatform.sendEvent(event, new ExperiencePlatformCallback() {
			@Override
			public void onResponse(Map<String, Object> data) {
				Log.d(LOG_TAG, String.format("Received Data Platform response for event '%s': %s", EVENT_TYPE_COMMERCE_PURCHASES,
											 data));
			}
		});
	}

	/**
	 * Helper method to construct and send the {@link ExperiencePlatformEvent} to the Experience Platform Extension.
	 * @param itemsList list of {@link ProductListItemsItem}s associated with this commerce event
	 * @param eventType the event type for this commerce event
	 */
	private static void createAndSendEvent(final List<ProductListItemsItem> itemsList, final String eventType) {

		Commerce commerce = new Commerce();

		switch (eventType) {

			case EVENT_TYPE_COMMERCE_PRODUCT_LIST_ADDS:
				ProductListAdds productListAdds = new ProductListAdds();
				productListAdds.setValue(1);
				commerce.setProductListAdds(productListAdds);
				break;

			case EVENT_TYPE_COMMERCE_PRODUCT_LIST_REMOVALS:
				ProductListRemovals productListRemovals = new ProductListRemovals();
				productListRemovals.setValue(1);
				commerce.setProductListRemovals(productListRemovals);
				break;

			case EVENT_TYPE_COMMERCE_PURCHASES:
				Purchases purchases = new Purchases();
				purchases.setValue(1);
				commerce.setPurchases(purchases);
				break;

			default:
				Log.w(LOG_TAG, "Unknown event type when sending Commerce event. Ignoring event.");
				return;

		}

		MobileSDKCommerceSchema xdmData = new MobileSDKCommerceSchema();
		xdmData.setEventType(eventType);
		xdmData.setCommerce(commerce);
		xdmData.setProductListItems(itemsList);

		ExperiencePlatformEvent event = new ExperiencePlatformEvent.Builder()
		.setXdmSchema(xdmData)
		.build();

		ExperiencePlatform.sendEvent(event, new ExperiencePlatformCallback() {
			@Override
			public void onResponse(Map<String, Object> data) {
				Log.d(LOG_TAG, String.format("Received Data Platform response for event '%s': %s", eventType, data));
			}
		});
	}

	/**
	 * Helper method to convert an {@link ProductContent.ProductItem} to an
	 * {@link ProductListItemsItem} object.
	 * If {@code quantity} is zero, then only the {@code name} and {@code sku} are added to the {@code ProductItem}.
	 *
	 * @param item a {@link ProductContent.ProductItem}
	 * @param quantity the specified quantity of {@link ProductListItemsItem}. If zero, quantity, price, and currency
	 *                 data are not added to the result.
	 * @return a {@link ProductListItemsItem} populated from the given {@link ProductContent.ProductItem},
	 * or null if {@code item} is null
	 */
	private static ProductListItemsItem createProductListItemsItem(final ProductContent.ProductItem item,
			final int quantity) {
		if (item == null) {
			return null;
		}

		ProductListItemsItem productItem = new ProductListItemsItem();
		productItem.setName(item.name);
		productItem.setSKU(item.sku);

		if (quantity > 0) {
			productItem.setCurrencyCode(item.currency);
			productItem.setQuantity(quantity);
			productItem.setPriceTotal(computeTotal(item.price, quantity));
		}

		return productItem;
	}

	/**
	 * Compute the total cost of a number of items.
	 * @param price the price of the product item
	 * @param quantity the quantity of the items
	 * @return the total cost of the items
	 */
	private static double computeTotal(final double price, final int quantity) {
		BigDecimal bdPrice = new BigDecimal(price);
		BigDecimal bdQuantity = new BigDecimal(quantity);
		bdPrice = bdPrice.multiply(bdQuantity);
		return bdPrice.setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

}
