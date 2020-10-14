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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.adobe.marketing.mobile.AdobeCallback;
import com.adobe.marketing.mobile.AdobeCallbackWithError;
import com.adobe.marketing.mobile.AdobeError;
import com.adobe.marketing.mobile.Analytics;
import com.adobe.marketing.mobile.Identity;
import com.adobe.marketing.mobile.MobileCore;
import com.adobe.marketing.mobile.MobilePrivacyStatus;
import com.adobe.marketing.mobile.VisitorID;
import com.adobe.marketing.mobile.sampleapp.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnalyticsTab extends Fragment implements NavigationAware {

    TextView lblQueueSize = null;
    TextView lblTrackingIdentifier = null;
    TextView lblVisitor = null;
    TextView lblMinBatchSize = null;
    Button btnEmitStateEvent = null;
    Button btnEmitActionEvent = null;
    Button btnClearQueuedHits = null;
    Button btnSendQueuedHits = null;
    RadioButton rdoOptIn = null;
    RadioButton rdoOptOut = null;
    RadioButton rdoOptUnknown = null;
    Button btnGetPrivacy = null;
    TextView txtCurrentPrivacy = null;
    Button btnCollectPII = null;
    Button btnUpdateConfig = null;
    Button btnSetAdvertisingId = null;
    Button btnSetPushId = null;
    Button btnGetECID = null;
    Button btnGetUrlVar = null;
    Button btnSyncId = null;
    Button btnGetSdkId = null;
    Button btnAppendUrl = null;
    String trackingIdentifier = "";
    String visitorIdentifier = "";
    String batchSize = "";

    boolean userIsViewingThisFragment = true;
    private static final String LOG_TAG = "Core Tab";

    public AnalyticsTab() {
        // Required empty public constructor
    }

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
        return inflater.inflate(R.layout.fragment_analytics_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        //Create references to all our components
        lblQueueSize = getView().findViewById(R.id.lblQueueSize);
        lblMinBatchSize = getView().findViewById(R.id.lblMinBatchSize);
        lblTrackingIdentifier = getView().findViewById(R.id.lblTrackingIdentifier);
        lblVisitor = getView().findViewById(R.id.lblVisitor);
        rdoOptIn = getView().findViewById(R.id.rdoOptIn);
        rdoOptOut = getView().findViewById(R.id.rdoOptOut);
        rdoOptUnknown = getView().findViewById(R.id.rdoOptUnknown);
        btnEmitStateEvent = getView().findViewById(R.id.btn_emitStateEvent);
        btnEmitActionEvent = getView().findViewById(R.id.btn_emitActionEvent);
        btnClearQueuedHits = getView().findViewById(R.id.btn_clearQueuedHits);
        btnSendQueuedHits = getView().findViewById(R.id.btn_sendQueuedHits);
        btnGetPrivacy = getView().findViewById(R.id.btn_getPrivacy);
        txtCurrentPrivacy = getView().findViewById(R.id.text_currentPrivacy);
        btnCollectPII = getView().findViewById(R.id.btn_collectPII);
        btnUpdateConfig = getView().findViewById(R.id.btn_updateConfig);
        btnSetAdvertisingId = getView().findViewById(R.id.btn_setAdvertisingId);
        btnSetPushId = getView().findViewById(R.id.btn_setPushId);
        btnGetECID = getView().findViewById(R.id.btn_getECID);
        btnGetUrlVar = getView().findViewById(R.id.btn_getUrlVar);
        btnSyncId = getView().findViewById(R.id.btn_syncId);
        btnGetSdkId = getView().findViewById(R.id.btn_getSdkId);
        btnAppendUrl = getView().findViewById(R.id.btn_appendUrl);

        //Setup button events
        btnSendQueuedHits.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Analytics.sendQueuedHits();

                showToast("Sent queued hits");
                delayedRefresh(500);
            }
        });

        btnCollectPII.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MobileCore.collectPii(Collections.singletonMap("name","Adobe Experience Platform"));
            }
        });

        btnUpdateConfig.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MobileCore.updateConfiguration(Collections.<String, Object>singletonMap("analytics.batchLimit",3));
            }
        });

        btnSetAdvertisingId.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MobileCore.setAdvertisingIdentifier("advertisingIdentifier");
            }
        });

        btnSetPushId.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MobileCore.setPushIdentifier("9516258b6230afdd93cf0cd07b8dd845");
            }
        });

        btnGetECID.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Identity.getExperienceCloudId(new AdobeCallbackWithError<String>() {
                    @Override
                    public void fail(AdobeError adobeError) {

                    }

                    @Override
                    public void call(String s) {
                        Log.i(LOG_TAG,"ECID : " + s);
                    }
                });
            }
        });

        btnGetUrlVar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Identity.getUrlVariables(new AdobeCallbackWithError<String>() {
                    @Override
                    public void fail(AdobeError adobeError) {

                    }

                    @Override
                    public void call(String s) {
                        Log.i(LOG_TAG,"URL Variables : " + s);
                    }
                });
            }
        });

        btnGetSdkId.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MobileCore.getSdkIdentities(new AdobeCallbackWithError<String>() {
                    @Override
                    public void fail(AdobeError adobeError) {

                    }

                    @Override
                    public void call(String s) {
                        Log.i(LOG_TAG,"SDK Identities : " + s);
                    }
                });
            }
        });

        btnSyncId.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Identity.syncIdentifier("idType1","1234567", VisitorID.AuthenticationState.AUTHENTICATED);
            }
        });

        btnAppendUrl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Identity.appendVisitorInfoForURL("https://example.com", new AdobeCallbackWithError<String>() {
                    @Override
                    public void fail(AdobeError adobeError) {

                    }

                    @Override
                    public void call(String s) {
                        Log.i(LOG_TAG,"URL : " + s);
                    }
                });
            }
        });



        btnGetPrivacy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MobileCore.getPrivacyStatus(new AdobeCallback<MobilePrivacyStatus>() {
                    @Override
                    public void call(final MobilePrivacyStatus mobilePrivacyStatus) {
                        getActivity().runOnUiThread(new Runnable(){
                            @Override
                            public void run(){
                                txtCurrentPrivacy.setText("Current Privacy: "+ mobilePrivacyStatus.getValue());
                            }
                        });
                    }
                });
            }
        });

        btnClearQueuedHits.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Analytics.clearQueue();

                showToast("Cleared queue");
                delayedRefresh(500);
            }
        });

        btnEmitActionEvent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String eventName = "sampleAction";

                Map<String, String> additionalContextData = new HashMap<String, String>();
                additionalContextData.put("exampleCustomKey", "exampleValue");
                MobileCore.trackAction(eventName, additionalContextData);

                showToast("Analytics action \""+eventName+"\" triggered");

                delayedRefresh(500);
            }
        });

        btnEmitStateEvent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String eventName = "sampleState";

                Map<String, String> additionalContextData = new HashMap<>();
                additionalContextData.put("exampleCustomKey", "exampleValue");
                MobileCore.trackState(eventName, additionalContextData);

                showToast("Analytics state \""+eventName+"\" triggered");

                delayedRefresh(500);
            }
        });

        rdoOptIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MobileCore.setPrivacyStatus(MobilePrivacyStatus.OPT_IN);
                delayedRefresh();
            }
        });
        rdoOptOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MobileCore.setPrivacyStatus(MobilePrivacyStatus.OPT_OUT);
                delayedRefresh();
            }
        });
        rdoOptUnknown.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MobileCore.setPrivacyStatus(MobilePrivacyStatus.UNKNOWN);
                delayedRefresh();
            }
        });



        updateBatchLimit(10);


        //Setup refresh timer
        Timer timer = new Timer("Background polling timer for analytics status");
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                refresh();
            }
        }, 0, 10000);
    }

    public void updateBatchLimit(int limitNumber){
        //Set batch limit
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("analytics.batchLimit", limitNumber);
        MobileCore.updateConfiguration(data);
        batchSize = Integer.toString(limitNumber);

        refresh();
    }

    public void delayedRefresh(){
        delayedRefresh(1000);
    }
    public void delayedRefresh(int delay){ //Used to refresh after a delay of 1 second - useful for showing immediate results after the user taps a button.
        //Setup refresh timer
        Timer timer = new Timer("Background polling timer for analytics status");
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                refresh();
            }
        }, delay);
    }


    private boolean isFirstRefresh = true;
    public void refresh(){
        if(!userIsViewingThisFragment){ //No need to refresh if the user isn't viewing the fragment. This will also prevent unneeded Adobe SDK events from being triggered.
            return;
        }
        if(isFirstRefresh){ //Only refresh the identifiers automatically once. After this, refresh them only when changed (see event for btnSaveFreeTextEntry).
            Analytics.getTrackingIdentifier(new AdobeCallback<String>() {
                @Override
                public void call(String s) {
                    trackingIdentifier = s;
                    if(s == null){
                        trackingIdentifier = "<none>";
                    }
                    refreshIdentifiers();
                }
            });
            Analytics.getVisitorIdentifier(new AdobeCallback<String>() {
                @Override
                public void call(String s) {
                    visitorIdentifier = s;
                    if(s == null){
                        visitorIdentifier = "<none>";
                    }
                    refreshIdentifiers();
                }
            });
        }
        isFirstRefresh = false;

        Analytics.getQueueSize(new AdobeCallback<Long>() {
            @Override
            public void call(final Long queueSize) {
                getActivity().runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        lblQueueSize.setText("Event queue length: "+queueSize);
                    }
                });
            }
        });

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lblMinBatchSize.setText("Min Batch Size: " + batchSize);
            }
        });


        //Refresh opting radio buttons
        MobileCore.getPrivacyStatus(new AdobeCallback<MobilePrivacyStatus>() {
            @Override
            public void call(MobilePrivacyStatus mobilePrivacyStatus) {
                if(mobilePrivacyStatus != null){
                    if(mobilePrivacyStatus.equals(MobilePrivacyStatus.OPT_IN)){
                        rdoOptIn.toggle();
                    }
                    else if(mobilePrivacyStatus.equals(MobilePrivacyStatus.OPT_OUT)){
                        rdoOptOut.toggle();
                    }
                    else{
                        rdoOptUnknown.toggle();
                    }
                }
                else{
                    rdoOptUnknown.toggle();
                }
            }
        });
    }

    public void refreshIdentifiers(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lblVisitor.setText("Visitor identifier: "+visitorIdentifier);
                lblTrackingIdentifier.setText("Tracking identifier: "+trackingIdentifier);
            }
        });
    }

    @Override
    public void OnNavigateTo() {
        userIsViewingThisFragment = true;
        delayedRefresh(500);
    }

    @Override
    public void OnNavigateAway() {
        userIsViewingThisFragment = false;
    }
}
