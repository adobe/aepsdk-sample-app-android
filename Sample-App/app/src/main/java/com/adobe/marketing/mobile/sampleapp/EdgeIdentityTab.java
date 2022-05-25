/*
  Copyright 2021 Adobe
  All Rights Reserved.

  NOTICE: Adobe permits you to use, modify, and distribute this file in
  accordance with the terms of the Adobe license agreement accompanying
  it.
 */

package com.adobe.marketing.mobile.sampleapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.adobe.marketing.mobile.AdobeCallback;
import com.adobe.marketing.mobile.MobileCore;
import com.adobe.marketing.mobile.edge.identity.AuthenticatedState;
import com.adobe.marketing.mobile.edge.identity.Identity;
import com.adobe.marketing.mobile.edge.identity.IdentityItem;
import com.adobe.marketing.mobile.edge.identity.IdentityMap;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class EdgeIdentityTab extends Fragment implements NavigationAware {
    private static final String LOG_TAG = "EdgeIdentityTab";
    private static final String ZERO_ADVERTISING_ID = "00000000-0000-0000-0000-000000000000";

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

        // Edge Identity Advertising Identifier
        Button btnUpdateAdId = getView().findViewById(R.id.btn_edge_identity_get_gaid);
        Button btnSetAdId = getView().findViewById(R.id.btn_edge_identity_set_ad_id);
        Button btnSetAdIdNull = getView().findViewById(R.id.btn_edge_identity_set_ad_id_null);
        Button btnSetAdIdAllZeros = getView().findViewById(R.id.btn_edge_identity_set_ad_id_all_zeros);
        Button btnSetAdIdEmptyString = getView().findViewById(R.id.btn_edge_identity_set_ad_id_empty_string);

        btnUpdateAdId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                getAdvertisingIdClientInfo(new AdobeCallback<AdvertisingIdClient.Info>() {
                    @Override
                    public void call(AdvertisingIdClient.Info info) {
                        Handler handler = new Handler(Looper.getMainLooper());

                        String adIdText = "Unable to get valid AdvertisingIdClient.Info";
                        String trackingAuthorizationText = "See console for error logs";

                        if (info != null) {
                            if (info.isLimitAdTrackingEnabled()) {
                                adIdText = "";
                                trackingAuthorizationText = "Ad tracking disabled";
                            } else {
                                adIdText = info.getId();
                                trackingAuthorizationText = "Ad tracking enabled";
                            }
                            // Update ad ID through MobileCore only when a valid Info instance is available
                            // to fetch the latest state
                            MobileCore.setAdvertisingIdentifier(adIdText);
                        }

                        String finalAdId = adIdText;
                        String finalTrackingAuthorizationText = trackingAuthorizationText;
                        handler.post(() -> {
                            TextView gaidTextView = getView().findViewById(R.id.label_edge_identity_gaid_placeholder);
                            gaidTextView.setText(finalAdId);
                            TextView adTrackingTextView = getView().findViewById(R.id.label_edge_identity_ad_tracking_enabled);
                            adTrackingTextView.setText(finalTrackingAuthorizationText);
                        });
                    }
                });
            }
        });

        btnSetAdId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                EditText adIdText = getView().findViewById(R.id.text_ad_id);
                String adId = adIdText.getText().toString();
                MobileCore.setAdvertisingIdentifier(adId);
            }
        });

        btnSetAdIdNull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                MobileCore.setAdvertisingIdentifier(null);
            }
        });

        btnSetAdIdAllZeros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                MobileCore.setAdvertisingIdentifier(ZERO_ADVERTISING_ID);
            }
        });

        btnSetAdIdEmptyString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                MobileCore.setAdvertisingIdentifier("");
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

    /**
     * Async method that retrieves the ad ID from the {@link AdvertisingIdClient} (using Google's gms.ads SDK).
     * Sanitizes ad ID value to the empty string ({@code ""}) if ad tracking is disabled, or an exception is encountered.
     *
     * @param callback receives the ad ID {@code String} if a valid value can be retrieved, {@code ""} otherwise.
     */
    private void getAdvertisingIdentifier(final AdobeCallback<String> callback) {
        if (callback == null) {
            Log.e(LOG_TAG, "Unexpected null callback, provide a callback to retrieve current GAID.");
            return;
        }
        getAdvertisingIdClientInfo(new AdobeCallback<AdvertisingIdClient.Info>() {
            @Override
            public void call(AdvertisingIdClient.Info info) {
                if (info == null) {
                    callback.call("");
                    return;
                }
                if (info.isLimitAdTrackingEnabled()) {
                    callback.call("");
                } else {
                    callback.call(info.getId());
                }
            }
        });
    }

    /**
     * Async method that retrieves the ad ID from the {@link AdvertisingIdClient} (using Google's gms.ads SDK).
     * Sanitizes ad ID value to the empty string ({@code ""}) if ad tracking is disabled, or an exception is encountered.
     *
     * @param callback receives the ad ID {@code String} if a valid value can be retrieved, {@code ""} otherwise.
     */
    private void getIsLimitAdTrackingEnabled(final AdobeCallback<Boolean> callback) {
        if (callback == null) {
            Log.e(LOG_TAG, "Unexpected null callback, provide a callback to retrieve isLimitAdTrackingEnabled.");
            return;
        }
        getAdvertisingIdClientInfo(new AdobeCallback<AdvertisingIdClient.Info>() {
            @Override
            public void call(AdvertisingIdClient.Info info) {
                if (info == null) {
                    return;
                }
                callback.call(info.isLimitAdTrackingEnabled());
            }
        });
    }

    /**
     * Async method that retrieves the {@link AdvertisingIdClient.Info} (using Google's gms.ads SDK).
     * Callers <strong>MUST</strong> verify that the result of the callback is not null before using any of its properties.
     *
     * @param callback receives the {@link AdvertisingIdClient.Info} if a valid value can be retrieved, {@code null} otherwise.
     */
    private void getAdvertisingIdClientInfo(final AdobeCallback<AdvertisingIdClient.Info> callback) {
        if (callback == null) {
            Log.e(LOG_TAG, "Unexpected null callback, provide a callback to retrieve AdvertisingIdClientInfo.");
            return;
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            try {
                AdvertisingIdClient.Info idInfo = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
                callback.call(idInfo);
                return;
            } catch (GooglePlayServicesNotAvailableException e) {
                Log.d(LOG_TAG, "GooglePlayServicesNotAvailableException while retrieving the advertising identifier ${e.localizedMessage}");
            } catch (GooglePlayServicesRepairableException e) {
                Log.d(LOG_TAG, "GooglePlayServicesRepairableException while retrieving the advertising identifier ${e.localizedMessage}");
            } catch (IOException e) {
                Log.d(LOG_TAG, "IOException while retrieving the advertising identifier ${e.localizedMessage}");
            }
            callback.call(null);
        });
    }
}



