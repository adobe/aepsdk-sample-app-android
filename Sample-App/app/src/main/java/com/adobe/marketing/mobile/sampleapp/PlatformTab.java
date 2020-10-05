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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlatformTab#newInstance} factory method to
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
}