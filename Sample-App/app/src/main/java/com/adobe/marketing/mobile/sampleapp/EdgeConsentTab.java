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
public class EdgeConsentTab extends Fragment implements NavigationAware {
    private static final String LOG_TAG = "EdgeIdentityTab";


    public EdgeConsentTab() {
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
        // EdgeIdentity API's
        Button btnUpdateIdentityMap = getView().findViewById(R.id.btn_updateIdentityMap);
        Button btnRemoveIdentityMap = getView().findViewById(R.id.btn_removeIdentityMap);
        Button btnGetIdentityMap = getView().findViewById(R.id.btn_getIdentityMap);

        btnUpdateIdentityMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IdentityMap map = new IdentityMap();
                map.addItem(new IdentityItem("primary@email.com", AuthenticatedState.AUTHENTICATED, false), "Email");
                map.addItem(new IdentityItem("secondary@email.com"), "Email");
                map.addItem(new IdentityItem("uniqueUserID", AuthenticatedState.AUTHENTICATED, true), "UserId");
                Identity.updateIdentities(map);
            }
        });

        btnRemoveIdentityMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Identity.removeIdentity(new IdentityItem("secondary@email.com"), "Email");
            }
        });

        btnGetIdentityMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Identity.getIdentities(new AdobeCallback<IdentityMap>() {
                    @Override
                    public void call(final IdentityMap map) {
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        String json = gson.toJson(map);
                        Log.i(LOG_TAG, String.format("Received Identities from API = %s", json));
                    }
                });
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