/*
 Copyright 2020 Adobe
 All Rights Reserved.

 NOTICE: Adobe permits you to use, modify, and distribute this file in
 accordance with the terms of the Adobe license agreement accompanying
 it.
 */
package com.adobe.marketing.mobile.sampleapp;

import com.adobe.marketing.mobile.AdobeCallback;

import com.adobe.marketing.mobile.ExperiencePlatform;
import com.adobe.marketing.mobile.Griffon;
import com.adobe.marketing.mobile.MobileCore;
import com.adobe.marketing.mobile.Identity;
import com.adobe.marketing.mobile.Lifecycle;
import com.adobe.marketing.mobile.Analytics;
import com.adobe.marketing.mobile.Signal;
import com.adobe.marketing.mobile.UserProfile;
import com.adobe.marketing.mobile.InvalidInitException;
import com.adobe.marketing.mobile.LoggingMode;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.HashMap;


public class MainApp extends Application {

    private static final String LOG_TAG = "MainApp";

    private static Context context;
    public static Context getAppContext(){
        return MainApp.context;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        MainApp.context = getApplicationContext();

        MobileCore.setApplication(this);
        MobileCore.setLogLevel(LoggingMode.VERBOSE);
        MobileCore.setSmallIconResourceID(R.mipmap.ic_launcher_round);
        MobileCore.setLargeIconResourceID(R.mipmap.ic_launcher_round);

        try{
            Analytics.registerExtension();
            UserProfile.registerExtension();
            Identity.registerExtension();
            Lifecycle.registerExtension();
            Signal.registerExtension();
            ExperiencePlatform.registerExtension();
            Griffon.registerExtension();

            MobileCore.configureWithAppID("your App id");
            MobileCore.start(new AdobeCallback () {

                    @Override
                    public void call(Object o) {

                        Log.d(LOG_TAG, "AEP Mobile SDK is initialized");

                        MobileCore.updateConfiguration(new HashMap<String, Object>(){{
                            put("global.privacy", "optedin");
                            put("experienceCloud.org", "FAF554945B90342F0A495E2C@AdobeOrg");
                            put("experiencePlatform.configId", "d3d079e7-130e-4ec1-88d7-c328eb9815c4");
                        }});

                    }
                });
                 } catch (InvalidInitException e) {
                    e.printStackTrace();
            }
    }
}
