/*
  Copyright 2021 Adobe
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
public class ConsentTab extends Fragment implements NavigationAware {
    private static final String LOG_TAG = "ConsentTab";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consent_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Card view to display registered Consent extension version
        final TextView tv_version = getView().findViewById(R.id.tv_Consent_version);
        view.post(new Runnable() {
            @Override
            public void run() {
                if (tv_version != null) {
                    tv_version.setText("Consent v" + Consent.extensionVersion());
                }
            }
        });

        // EdgeConsent API's
        Button btnCollectConsentY = getView().findViewById(R.id.btn_collectConsentY);
        Button btnCollectConsentN = getView().findViewById(R.id.btn_collectConsentN);
        Button btnGetConsents = getView().findViewById(R.id.btn_getConsents);

        btnCollectConsentY.setOnClickListener(v -> {
            collectConsentUpdate("y");
            getConsent();
        });

        btnCollectConsentN.setOnClickListener(v -> {
            collectConsentUpdate("n");
            getConsent();
        });

        btnGetConsents.setOnClickListener(v -> getConsent());
    }

    @Override
    public void OnNavigateTo() { }

    @Override
    public void OnNavigateAway() { }

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
        Consent.getConsents(consents -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(consents);
            Log.i(LOG_TAG, String.format("getConsent returned: %s", json));
            updateTextView(json);
        });
    }

    private void updateTextView(final String jsonString) {
        final TextView textViewGetData = getView().findViewById(R.id.tv_Consent_data);
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