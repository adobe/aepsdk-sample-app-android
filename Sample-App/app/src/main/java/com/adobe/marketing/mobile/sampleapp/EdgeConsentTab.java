/*
  Copyright 2020 Adobe
  All Rights Reserved.

  NOTICE: Adobe permits you to use, modify, and distribute this file in
  accordance with the terms of the Adobe license agreement accompanying
  it.
 */

package com.adobe.marketing.mobile.sampleapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.adobe.marketing.mobile.AdobeCallback;
import com.adobe.marketing.mobile.edge.consent.Consent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class EdgeConsentTab extends Fragment implements NavigationAware {
    private static final String LOG_TAG = "EdgeConsentTab";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edgeconsent_tab, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Card view to display registered Consent extension version
        final TextView tv_version  = getView().findViewById(R.id.tv_edgeConsent_version);
        view.post(new Runnable() {
            @Override
            public void run() {
                if (tv_version != null) {
                    tv_version.setText("Edge Consent Extension version: " + Consent.extensionVersion());
                }
            }
        });

        // EdgeConsent API's
        Button btnCollectConsentY = getView().findViewById(R.id.btn_collectConsentY);
        Button btnCollectConsentN = getView().findViewById(R.id.btn_collectConsentN);
        Button btnGetConsents = getView().findViewById(R.id.btn_getConsents);

        btnCollectConsentY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectConsentUpdate("y");
                getConsent();
            }
        });

        btnCollectConsentN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectConsentUpdate("n");
                getConsent();
            }
        });

        btnGetConsents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                getConsent();
            }
        });
    }

    @Override
    public void OnNavigateTo() {

    }

    @Override
    public void OnNavigateAway() {

    }

    private void collectConsentUpdate(final String value) {
        if (value == null || value.isEmpty()) {
            return;
        }

        Consent.update(new HashMap<String, Object>() {
            {
                put("consents", new HashMap<String, Object>() {
                    {
                        put("collect", new HashMap<String, Object>() {
                            {
                                put("val", value);
                            }
                        });
                    }
                });
            }
        });
    }

    private void getConsent() {
        Consent.getConsents(new AdobeCallback<Map<String, Object>>() {
            @Override
            public void call(Map<String, Object> map) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(map);
                Log.i(LOG_TAG, String.format("Received Consent from API = %s", json));
                updateTextView(json);
            }
        });
    }

    private void updateTextView(final String jsonString) {
        final TextView textViewGetData = getView().findViewById(R.id.tv_edgeConsent_data);
        getView().post(new Runnable() {
            @Override
            public void run() {
                if (textViewGetData != null) {
                    textViewGetData.setText(jsonString);
                }
            }
        });
    }




}