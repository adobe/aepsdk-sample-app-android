/*
 Copyright 2020 Adobe
 All Rights Reserved.

 NOTICE: Adobe permits you to use, modify, and distribute this file in
 accordance with the terms of the Adobe license agreement accompanying
 it.
 */

package com.adobe.marketing.mobile.sampleapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.adobe.marketing.mobile.sampleapp.platform.CommerceUtil;
import com.adobe.marketing.mobile.sampleapp.platform.ProductCart;
import com.adobe.marketing.mobile.sampleapp.platform.ProductContent;

import java.text.NumberFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlatformTab extends Fragment implements NavigationAware {
    private static final String LOG_TAG = "PlatformTab";

    public PlatformTab() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ProductContent.ITEMS.isEmpty()) {
            addItemsToProductList();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_platform_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Set click handler for Purchase button
        view.findViewById(R.id.button_purchase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPurchase(v);
            }
        });

        // Define click handler for product list items to add/remove items from cart
        View.OnClickListener itemViewClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductContent.ProductItem item = (ProductContent.ProductItem) view.getTag();
                TextView textView = (TextView) view.findViewById(R.id.label);

                if (ProductCart.ITEM_MAP.containsKey(item.sku)) {
                    textView.setText(R.string.label_add);
                    ProductCart.removeItem(item);
                    CommerceUtil.sendProductListRemoveXdmEvent(item, 1);
                } else {
                    textView.setText(R.string.label_in_cart);
                    ProductCart.addItem(item, 1);
                    CommerceUtil.sendProductListAddXdmEvent(item, 1);
                }

                // Update display of total price
                updateTotalPriceView();
            }
        };

        // Set view adapter for product list
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.item_list);
        recyclerView.setAdapter(new ItemViewAdapter(ProductContent.ITEMS, itemViewClickListener));

        updateTotalPriceView();
    }

    @Override
    public void OnNavigateTo() {

    }

    @Override
    public void OnNavigateAway() {

    }

    /**
     * Update the total price {@code TextView} with the current price listed in the {@link ProductCart}.
     */
    private void updateTotalPriceView() {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        String currency = format.format(ProductCart.getTotalPrice());

        TextView priceView = (TextView) getView().findViewById(R.id.lblTotalPrice);
        priceView.setText(String.valueOf(currency));
    }

    /**
     * Handle a purchase event.
     * @param view the "purchase" view, which in this case is a button and it no really useful.
     */
    private void onPurchase(View view) {
        // Get selected payment method
        RadioGroup radioGroup = (RadioGroup) getView().findViewById(R.id.radio_payment_method);
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) getView().findViewById(radioButtonId);
        String paymentMethod = String.valueOf(radioButton.getText());

        // Send "commerce.purchase" event to Adobe Experience Platform Edge
        CommerceUtil.sendPurchaseXdmEvent(paymentMethod, ProductCart.getTotalPrice());

        clearCart();
    }

    /**
     * Populate the product content list with items for "sale".
     */
    private void addItemsToProductList() {
        // Add ProductContent
        ProductContent.addItem(new ProductContent.ProductItem("625–740", // sku
                "Red", // name
                "The color of apples, strawberries, and firetrucks!", // details
                9.95, // price
                "USD", // currency
                Color.RED // image
        ));
        // Add ProductContent
        ProductContent.addItem(new ProductContent.ProductItem("450-495", // sku
                "Blue", // name
                "The color of the sky, oceans, and blueberries!", // details
                12.98, // price
                "USD", // currency
                Color.BLUE // image
        ));
        // Add ProductContent
        ProductContent.addItem(new ProductContent.ProductItem("495–570", // sku
                "Green", // name
                "The color of grass, leaves, and avocados!", // details
                10.95, // price
                "USD", // currency
                Color.GREEN // image
        ));
        // Add ProductContent
        ProductContent.addItem(new ProductContent.ProductItem("570–590", // sku
                "Yellow", // name
                "The color of the Sun, busy bees, and daisies!", // details
                5.99, // price
                "USD", // currency
                Color.YELLOW // image
        ));
    }

    /**
     * Updates the product list view with the correct "in cart" or "add" status label.
     */
    private void updateProductCartStatus() {
        View recyclerView = (RecyclerView) getView().findViewById(R.id.item_list);
        for (ProductContent.ProductItem product : ProductContent.ITEMS) {
            View itemView = recyclerView.findViewWithTag(product);
            TextView labelView = itemView.findViewById(R.id.label);
            if (ProductCart.ITEM_MAP.containsKey(product.sku)) {
                labelView.setText(R.string.label_in_cart);
            } else {
                labelView.setText(R.string.label_add);
            }
        }
    }

    /**
     * Clears the "Cart" by removing all items in the {@link ProductCart}, removes "in cart" status
     * in each product item's view, and clears total price view.
     */
    private void clearCart() {
        ProductCart.clearCart();
        updateProductCartStatus();
        updateTotalPriceView();
    }
}