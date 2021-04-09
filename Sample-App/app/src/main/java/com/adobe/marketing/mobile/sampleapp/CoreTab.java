/*
 Copyright 2020 Adobe
 All Rights Reserved.

 NOTICE: Adobe permits you to use, modify, and distribute this file in
 accordance with the terms of the Adobe license agreement accompanying
 it.
 */
package com.adobe.marketing.mobile.sampleapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.adobe.marketing.mobile.AdobeCallback;
import com.adobe.marketing.mobile.AdobeCallbackWithError;
import com.adobe.marketing.mobile.AdobeError;
import com.adobe.marketing.mobile.Identity;
import com.adobe.marketing.mobile.MobileCore;
import com.adobe.marketing.mobile.MobilePrivacyStatus;
import com.adobe.marketing.mobile.VisitorID;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoreTab extends Fragment implements NavigationAware {

    boolean userIsViewingThisFragment = true;
    private static final String LOG_TAG = "Core Tab";

    Toast toast = null;

    public void showToast(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (toast != null) {
                    toast.cancel();
                }
                toast = Toast.makeText(MainApp.getAppContext(), message, Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_core_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Create references to all our components
        Button rdoOptIn = getView().findViewById(R.id.rdoOptIn);
        Button rdoOptOut = getView().findViewById(R.id.rdoOptOut);
        Button rdoOptUnknown = getView().findViewById(R.id.rdoOptUnknown);
        Button btnEmitStateEvent = getView().findViewById(R.id.btn_emitStateEvent);
        Button btnEmitActionEvent = getView().findViewById(R.id.btn_emitActionEvent);
        Button btnGetPrivacy = getView().findViewById(R.id.btn_getPrivacy);
        Button btnCollectPII = getView().findViewById(R.id.btn_collectPII);
        Button btnUpdateConfig = getView().findViewById(R.id.btn_updateConfig);
        Button btnSetAdvertisingId = getView().findViewById(R.id.btn_setAdvertisingId);
        Button btnSetPushId = getView().findViewById(R.id.btn_setPushId);
        Button btnGetECID = getView().findViewById(R.id.btn_getECID);
        Button btnGetUrlVar = getView().findViewById(R.id.btn_getUrlVar);
        Button btnSyncId = getView().findViewById(R.id.btn_syncId);
        Button btnGetSdkId = getView().findViewById(R.id.btn_getSdkId);
        Button btnAppendUrl = getView().findViewById(R.id.btn_appendUrl);
        final TextView txtCurrentPrivacy = getView().findViewById(R.id.text_currentPrivacy);


        btnCollectPII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobileCore.collectPii(Collections.singletonMap("name", "Adobe Experience Platform"));
            }
        });

        btnUpdateConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobileCore.updateConfiguration(Collections.<String, Object>singletonMap("analytics.batchLimit", 3));
            }
        });

        btnSetAdvertisingId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobileCore.setAdvertisingIdentifier("advertisingIdentifier");
            }
        });

        btnSetPushId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobileCore.setPushIdentifier("9516258b6230afdd93cf0cd07b8dd845");
            }
        });

        btnGetECID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Identity.getExperienceCloudId(new AdobeCallbackWithError<String>() {
                    @Override
                    public void fail(AdobeError adobeError) {

                    }

                    @Override
                    public void call(String s) {
                        Log.i(LOG_TAG, "ECID : " + s);
                    }
                });
            }
        });

        btnGetUrlVar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Identity.getUrlVariables(new AdobeCallbackWithError<String>() {
                    @Override
                    public void fail(AdobeError adobeError) {
                    }

                    @Override
                    public void call(String s) {
                        Log.i(LOG_TAG, "URL Variables : " + s);
                    }
                });
            }
        });

        btnGetSdkId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobileCore.getSdkIdentities(new AdobeCallbackWithError<String>() {
                    @Override
                    public void fail(AdobeError adobeError) {
                    }

                    @Override
                    public void call(String s) {
                        Log.i(LOG_TAG, "SDK Identities : " + s);
                    }
                });
            }
        });

        btnSyncId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Identity.syncIdentifier("idType1", "1234567", VisitorID.AuthenticationState.AUTHENTICATED);
            }
        });

        btnAppendUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Identity.appendVisitorInfoForURL("https://example.com", new AdobeCallbackWithError<String>() {
                    @Override
                    public void fail(AdobeError adobeError) {
                    }

                    @Override
                    public void call(String s) {
                        Log.i(LOG_TAG, "URL : " + s);
                    }
                });
            }
        });


        btnGetPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobileCore.getPrivacyStatus(new AdobeCallback<MobilePrivacyStatus>() {
                    @Override
                    public void call(final MobilePrivacyStatus mobilePrivacyStatus) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txtCurrentPrivacy.setText("Current Privacy: " + mobilePrivacyStatus.getValue());
                            }
                        });
                    }
                });
            }
        });

        btnEmitActionEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = "sampleAction";

                Map<String, String> additionalContextData = new HashMap<String, String>();
                additionalContextData.put("exampleCustomKey", "exampleValue");
                MobileCore.trackAction(eventName, additionalContextData);

                showToast("Analytics action \"" + eventName + "\" triggered");

            }
        });

        btnEmitStateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = "sampleState";

                Map<String, String> additionalContextData = new HashMap<>();
                additionalContextData.put("exampleCustomKey", "exampleValue");
                MobileCore.trackState(eventName, additionalContextData);

                showToast("Analytics state \"" + eventName + "\" triggered");

            }
        });

        rdoOptIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobileCore.setPrivacyStatus(MobilePrivacyStatus.OPT_IN);
            }
        });
        rdoOptOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobileCore.setPrivacyStatus(MobilePrivacyStatus.OPT_OUT);
            }
        });
        rdoOptUnknown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobileCore.setPrivacyStatus(MobilePrivacyStatus.UNKNOWN);
            }
        });
    }

    @Override
    public void OnNavigateTo() {
        userIsViewingThisFragment = true;
    }

    @Override
    public void OnNavigateAway() {
        userIsViewingThisFragment = false;
    }
}
