/*
 Copyright 2020 Adobe
 All Rights Reserved.

 NOTICE: Adobe permits you to use, modify, and distribute this file in
 accordance with the terms of the Adobe license agreement accompanying
 it.
 */
package com.adobe.marketing.mobile.sampleapp;

import com.adobe.marketing.mobile.AdobeCallback;

import com.adobe.marketing.mobile.Assurance;
import com.adobe.marketing.mobile.Edge;
import com.adobe.marketing.mobile.Messaging;
import com.adobe.marketing.mobile.MobileCore;
import com.adobe.marketing.mobile.Lifecycle;
import com.adobe.marketing.mobile.Analytics;
import com.adobe.marketing.mobile.Signal;
import com.adobe.marketing.mobile.UserProfile;
import com.adobe.marketing.mobile.InvalidInitException;
import com.adobe.marketing.mobile.LoggingMode;
import com.adobe.marketing.mobile.edge.consent.Consent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

public class MainApp extends Application {

    private static final String LOG_TAG = "MainApp";
    private static final String LAUNCH_ENVIRONMENT_FILE_ID = "";
    private static Context context;

    public static Context getAppContext() {
        return MainApp.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MainApp.context = getApplicationContext();

        MobileCore.setApplication(this);
        MobileCore.setLogLevel(LoggingMode.VERBOSE);
        MobileCore.setSmallIconResourceID(R.mipmap.ic_launcher_round);
        MobileCore.setLargeIconResourceID(R.mipmap.ic_launcher_round);

        try {
            Analytics.registerExtension();
            UserProfile.registerExtension();
            Consent.registerExtension();
            com.adobe.marketing.mobile.Identity.registerExtension();
            com.adobe.marketing.mobile.edge.identity.Identity.registerExtension();
            Lifecycle.registerExtension();
            Signal.registerExtension();
            Edge.registerExtension();
            Assurance.registerExtension();
            Messaging.registerExtension();

            MobileCore.configureWithAppID(LAUNCH_ENVIRONMENT_FILE_ID);
            MobileCore.start(new AdobeCallback() {

                @Override
                public void call(Object o) {
                    Log.d(LOG_TAG, "AEP Mobile SDK is initialized");

                }
            });
        } catch (InvalidInitException e) {
            e.printStackTrace();
        }

        try {
            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w(LOG_TAG, "Fetching FCM registration token failed", task.getException());
                                return;
                            }

                            // Get new FCM registration token
                            String token = task.getResult();
                            MobileCore.setPushIdentifier(token);
                        }
                    });
        } catch (IllegalArgumentException e) {
            Log.e(LOG_TAG, "IllegalArgumentException - Check if google-services.json is added and is correctly configured. \nError message: " + e.getLocalizedMessage());
        }
    }
}
