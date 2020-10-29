/*
  Copyright 2020 Adobe
  All Rights Reserved.

  NOTICE: Adobe permits you to use, modify, and distribute this file in
  accordance with the terms of the Adobe license agreement accompanying
  it.
 */

package com.adobe.marketing.mobile.sampleapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.adobe.marketing.mobile.AdobeCallback;
import com.adobe.marketing.mobile.Edge;
import com.adobe.marketing.mobile.EdgeCallback;
import com.adobe.marketing.mobile.ExperienceEvent;
import com.adobe.marketing.mobile.Identity;
import com.adobe.marketing.mobile.MobileCore;
import com.adobe.marketing.mobile.xdm.Acopprod3;
import com.adobe.marketing.mobile.xdm.Commerce;
import com.adobe.marketing.mobile.xdm.MobileSDKCommerceSchema;
import com.adobe.marketing.mobile.xdm.Order;
import com.adobe.marketing.mobile.xdm.PaymentsItem;
import com.adobe.marketing.mobile.xdm.ProductListAdds;
import com.adobe.marketing.mobile.xdm.ProductListItemsItem;
import com.adobe.marketing.mobile.xdm.ProductReviews;
import com.adobe.marketing.mobile.xdm.Purchases;
import com.adobe.marketing.mobile.xdm.RatingEnum;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class EdgeTab extends Fragment implements NavigationAware {

    private final ProductItem[] products = new ProductItem[] {
        new ProductItem("SHOES123", "Red canvas shoes", 34.76, "USD"),
        new ProductItem("SHOES456", "Brown leather shoes", 52.81, "USD"),
        new ProductItem("HAT567", "Wool Hat", 25.15, "USD"),
        new ProductItem("HAT089", "Straw Hat", 11.85, "USD")
    };

    public EdgeTab() {
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
        return inflater.inflate(R.layout.fragment_edge_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button buttonAddToCart = view.findViewById(R.id.button_add_to_cart);
        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAddToCartXdmEvent();

                Resources res = getResources();
                View view = getView().findViewById(R.id.layoutMain);
                Snackbar.make(view, res.getString(R.string.add_to_cart_message), Snackbar.LENGTH_SHORT)
                        .show();
            }
        });

        Button buttonPurchase = view.findViewById(R.id.button_purchase);
        buttonPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send a sample Commerce Purchase Experience Event to the AEP Edge extension
                sendPurchaseXdmEvent();

                Resources res = getResources();
                View view = getView().findViewById(R.id.layoutMain);
                Snackbar.make(view, res.getString(R.string.purchase_complete_message), Snackbar.LENGTH_SHORT)
                        .show();
            }
        });

        Button buttonReview = view.findViewById(R.id.button_review);
        buttonReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText reviewerEmailEditText = getView().findViewById(R.id.reviewer_email);
                Spinner productsSpinner = getView().findViewById(R.id.products_spinner);
                RatingBar ratingBar = getView().findViewById(R.id.review_ratingbar);
                EditText productReviewEditText = getView().findViewById(R.id.text_review_text);

                String reviewerId = reviewerEmailEditText.getText().toString();
                ProductItem product = (ProductItem)productsSpinner.getSelectedItem();
                int rating = (int)ratingBar.getRating();
                String productReview = productReviewEditText.getText().toString();

                sendProductReviewXdmEvent(product, reviewerId, rating, productReview);

                Resources res = getResources();
                View view = getView().findViewById(R.id.layoutMain);
                Snackbar.make(view, res.getString(R.string.review_complete_message), Snackbar.LENGTH_SHORT)
                        .show();

            }
        });

        // Setup product list spinner
        Spinner spinner = view.findViewById(R.id.products_spinner);
        spinner.setAdapter(new SpinnerAdapter(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, products));
    }

    @Override
    public void OnNavigateTo() {

    }

    @Override
    public void OnNavigateAway() {

    }

    private void sendAddToCartXdmEvent() {
        Spinner productsSpinner = getView().findViewById(R.id.products_spinner);
        ProductItem product = (ProductItem) productsSpinner.getSelectedItem();

        /// Create list with the added items
        final ProductListItemsItem product1 = new ProductListItemsItem();
        product1.setName(product.name);
        product1.setPriceTotal(product.price);
        product1.setSKU(product.sku);
        product1.setQuantity(1);
        product1.setCurrencyCode(product.currencyCode);

        List<ProductListItemsItem> productItemsList = new ArrayList<ProductListItemsItem>() {{
            add(product1);
        }};

        ProductListAdds productListAdds = new ProductListAdds();
        productListAdds.setValue(1);

        /// Create Commerce object and add ProductListAdds details
        Commerce commerce = new Commerce();
        commerce.setProductListAdds(productListAdds);

        // Compose the XDM Schema object and set the event name
        MobileSDKCommerceSchema xdmData = new MobileSDKCommerceSchema();
        xdmData.setEventType("commerce.productListAdds");
        xdmData.setCommerce(commerce);
        xdmData.setProductListItems(productItemsList);

        // Create an Experience Event with the built schema and send it using the AEP Edge extension
        ExperienceEvent event = new ExperienceEvent.Builder()
                .setXdmSchema(xdmData)
                .build();
        Edge.sendEvent(event, new EdgeCallback() {
            @Override
            public void onResponse(Map<String, Object> data) {
                Log.d("Send XDM Event", String.format("Received response for event 'commerce.productListAdds': %s", data));
            }
        });
    }

    private void sendPurchaseXdmEvent() {
        Spinner productsSpinner = getView().findViewById(R.id.products_spinner);
        ProductItem product = (ProductItem) productsSpinner.getSelectedItem();

        /// Create list with the purchased items
        final ProductListItemsItem product1 = new ProductListItemsItem();
        product1.setName(product.name);
        product1.setPriceTotal(product.price);
        product1.setSKU(product.sku);
        product1.setQuantity(1);
        product1.setCurrencyCode(product.currencyCode);

        List<ProductListItemsItem> purchasedItems = new ArrayList<ProductListItemsItem>() {{
            add(product1);
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

        // Create an Experience Event with the built schema and send it using the AEP Edge extension
        ExperienceEvent event = new ExperienceEvent.Builder()
                .setXdmSchema(xdmData)
                .build();
        Edge.sendEvent(event, new EdgeCallback() {
            @Override
            public void onResponse(Map<String, Object> data) {
                Log.d("Send XDM Event", String.format("Received response for event 'commerce.purchases': %s", data));
            }
        });
    }

    private void sendProductReviewXdmEvent(final ProductItem product, final String reviewerId, final int rating, final String text) {
        // TODO - Assignment 3
    }

    private static class ProductItem {
        final String sku;
        final String name;
        final double price;
        final String currencyCode;

        public ProductItem(final String sku, final String name, final double price, final String currencyCode) {
            this.sku = sku;
            this.name = name;
            this.price = price;
            this.currencyCode = currencyCode;
        }
    }

    private static class SpinnerAdapter extends ArrayAdapter<ProductItem> {
        private final ProductItem[] products;

        public SpinnerAdapter(@NonNull Context context, int resource, @NonNull ProductItem[] objects) {
            super(context, resource, objects);
            products = objects;
        }

        @Override
        public int getCount() {
            return products.length;
        }

        @Override
        public ProductItem getItem(int position) {
            return products[position];
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView label = (TextView) super.getView(position, convertView, parent);
            label.setText(products[position].name);
            return label;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView label = (TextView) super.getDropDownView(position, convertView, parent);
            label.setText(products[position].name);
            return label;
        }
    }
}