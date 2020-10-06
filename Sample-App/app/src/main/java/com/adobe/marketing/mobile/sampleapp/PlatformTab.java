/*
  Copyright 2020 Adobe
  All Rights Reserved.

  NOTICE: Adobe permits you to use, modify, and distribute this file in
  accordance with the terms of the Adobe license agreement accompanying
  it.
 */

package com.adobe.marketing.mobile.sampleapp;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.adobe.marketing.mobile.ExperiencePlatform;
import com.adobe.marketing.mobile.ExperiencePlatformCallback;
import com.adobe.marketing.mobile.ExperiencePlatformEvent;
import com.adobe.marketing.mobile.xdm.Commerce;
import com.adobe.marketing.mobile.xdm.MobileSDKCommerceSchema;
import com.adobe.marketing.mobile.xdm.Order;
import com.adobe.marketing.mobile.xdm.PaymentsItem;
import com.adobe.marketing.mobile.xdm.ProductListItemsItem;
import com.adobe.marketing.mobile.xdm.Purchases;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PlatformTab extends Fragment implements NavigationAware {

    public PlatformTab() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_platform_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button buttonAddToCart = view.findViewById(R.id.button_add_to_cart);
        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                View view = getView().findViewById(R.id.layoutMain);
                Snackbar.make(view, res.getString(R.string.add_to_cart_not_implemented_message), Snackbar.LENGTH_SHORT)
                        .show();
            }
        });

        Button buttonPurchase = view.findViewById(R.id.button_purchase);
        buttonPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send a sample Commerce Purchase Experience Event to the AEP Experience Platform extension
                sendPurchaseXdmEvent();

                Resources res = getResources();
                View view = getView().findViewById(R.id.layoutMain);
                Snackbar.make(view, res.getString(R.string.purchase_complete_message), Snackbar.LENGTH_SHORT)
                        .show();
            }
        });
    }

    @Override
    public void OnNavigateTo() {

    }

    @Override
    public void OnNavigateAway() {

    }

    private void sendPurchaseXdmEvent() {
        /// Create list with the purchased items
        final ProductListItemsItem product1 = new ProductListItemsItem();
        product1.setName("Shoes");
        product1.setPriceTotal(34.76);
        product1.setSKU("SHOES123");
        product1.setQuantity(1);
        product1.setCurrencyCode("USD");

        final ProductListItemsItem product2 = new ProductListItemsItem();
        product2.setName("Hat");
        product2.setPriceTotal(15.3);
        product2.setSKU("HAT567");
        product2.setQuantity(2);
        product2.setCurrencyCode("USD");

        List<ProductListItemsItem> purchasedItems = new ArrayList<ProductListItemsItem>() {{
            add(product1);
            add(product2);
        }};

        double orderTotal = 0D;
        for (ProductListItemsItem item : purchasedItems) {
            orderTotal += product1.getPriceTotal();
        }

        /// Create PaymentItem which details the method of payment
        final PaymentsItem paymentsItem = new PaymentsItem();
        paymentsItem.setPaymentAmount(orderTotal);
        paymentsItem.setPaymentType("Credit card");

        /// Create the Order
        Order order = new Order();
        order.setPriceTotal(orderTotal);
        order.setPayments(new ArrayList<PaymentsItem>() {{
            add(paymentsItem);
        }});
        order.setCurrencyCode("USD");

        /// Create Purchases action
        Purchases purchases = new Purchases();
        purchases.setValue(1);

        /// Create Commerce object and add Purchases action and Order details
        Commerce commerce = new Commerce();
        commerce.setOrder(order);
        commerce.setPurchases(purchases);

        // Compose the XDM Schema object and set the event name
        MobileSDKCommerceSchema xdmData = new MobileSDKCommerceSchema();
        xdmData.setEventType("commerce.purchases");
        xdmData.setCommerce(commerce);
        xdmData.setProductListItems(purchasedItems);

        // Create an Experience Event with the built schema and send it using the Platform extension
        ExperiencePlatformEvent event = new ExperiencePlatformEvent.Builder()
                .setXdmSchema(xdmData)
                .build();
        ExperiencePlatform.sendEvent(event, new ExperiencePlatformCallback() {
            @Override
            public void onResponse(Map<String, Object> data) {
                Log.d("Send Purchase XDM Event", String.format("Received Data Platform response for event 'commerce.purchases': %s", data));
            }
        });
    }
}