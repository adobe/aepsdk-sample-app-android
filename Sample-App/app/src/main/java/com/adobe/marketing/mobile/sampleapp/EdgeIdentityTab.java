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
import com.adobe.marketing.mobile.edge.identity.AuthenticatedState;
import com.adobe.marketing.mobile.edge.identity.Identity;
import com.adobe.marketing.mobile.edge.identity.IdentityItem;
import com.adobe.marketing.mobile.edge.identity.IdentityMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class EdgeIdentityTab extends Fragment implements NavigationAware {
    private static final String LOG_TAG = "EdgeIdentityTab";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edgeidentity_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Card view to display registered Edge Identity extension version
        final TextView tv_version = getView().findViewById(R.id.tv_edgeidentity_version);
        view.post(new Runnable() {
            @Override
            public void run() {
                if (tv_version != null) {
                    tv_version.setText("Edge Identity v" + Identity.extensionVersion());
                }
            }
        });

        // EdgeIdentity API's
        Button btnUpdateIdentityMap = getView().findViewById(R.id.btn_updateIdentityMap);
        Button btnRemoveIdentityMap = getView().findViewById(R.id.btn_removeIdentityMap);
        Button btnGetIdentityMap = getView().findViewById(R.id.btn_getIdentityMap);
        Button btnGetECID = getView().findViewById(R.id.btn_getECID);


        btnUpdateIdentityMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IdentityMap map = new IdentityMap();
                map.addItem(new IdentityItem("primary@email.com", AuthenticatedState.AUTHENTICATED, false), "Email");
                map.addItem(new IdentityItem("secondary@email.com"), "Email");
                map.addItem(new IdentityItem("uniqueUserID", AuthenticatedState.AUTHENTICATED, true), "UserId");
                Identity.updateIdentities(map);
                getIdentities();
            }
        });

        btnRemoveIdentityMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Identity.removeIdentity(new IdentityItem("secondary@email.com"), "Email");
                getIdentities();
            }
        });

        btnGetIdentityMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                getIdentities();
            }
        });

        btnGetECID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Identity.getExperienceCloudId(new AdobeCallback<String>() {
                    @Override
                    public void call(String ecid) {
                        Log.i(LOG_TAG, String.format("Received ECID from API = %s", ecid));
                        updateTextView(String.format("ECID : %s", ecid));
                    }
                });
            }
        });
    }

    @Override
    public void OnNavigateTo() { }

    @Override
    public void OnNavigateAway() { }

    private void getIdentities() {
        Identity.getIdentities(new AdobeCallback<IdentityMap>() {
            @Override
            public void call(final IdentityMap map) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(map);
                Log.i(LOG_TAG, String.format("Received Identities from API = %s", json));
                updateTextView(json);
            }
        });
    }

    private void updateTextView(final String jsonString) {
        final TextView textViewGetData = getView().findViewById(R.id.tv_edgeIdentity_data);
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