/*
 Copyright 2020 Adobe
 All Rights Reserved.

 NOTICE: Adobe permits you to use, modify, and distribute this file in
 accordance with the terms of the Adobe license agreement accompanying
 it.
 */
package com.adobe.marketing.mobile.sampleapp;

import androidx.appcompat.app.AppCompatActivity;

import com.adobe.marketing.mobile.Messaging;
import com.adobe.marketing.mobile.MobileCore;

import java.util.Map;
import java.util.HashMap;

import android.util.Log;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.tabs.*;

import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";
    public PageAdapter pagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onPause() {
        super.onPause();
        MobileCore.lifecyclePause();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobileCore.setApplication(getApplication());
        MobileCore.lifecycleStart(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the URI from the data elements of the applications intent com.adobe.marketing.mobile.sampleapp.MainActivity
        final Intent intent = getIntent();
        final Uri data = intent.getData();
        int tab = intent.getIntExtra(AppConstants.INTENT_TAB_KEY, AppConstants.TAB_CORE);

        // Messaging track notification click
        final boolean isAppOpenFromPushInteraction = intent.getBooleanExtra(AppConstants.INTENT_FROM_PUSH, false);
        if (isAppOpenFromPushInteraction) {
            // tracking application opened with no custom action
            Messaging.handleNotificationResponse(intent, true, null);
        }

        if (data != null) {
            Log.d(LOG_TAG, "Connected successfully to Assurance with the URI : " + data.toString());
        }

        //Set up the tab system
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        pagerAdapter = new PageAdapter(getSupportFragmentManager(), getApplicationContext(), tabLayout.getTabCount());

        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(tab, true);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            private int previousPosition = 0;

            @Override
            public void onPageSelected(int position) {

                if (pagerAdapter.GetFragmentArray()[previousPosition] instanceof NavigationAware) {
                    NavigationAware tab = (NavigationAware) pagerAdapter.GetFragmentArray()[previousPosition];
                    tab.OnNavigateAway();
                }

                previousPosition = position;

                if (pagerAdapter.GetFragmentArray()[position] instanceof NavigationAware) {
                    NavigationAware tab = (NavigationAware) pagerAdapter.GetFragmentArray()[position];
                    tab.OnNavigateTo();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Map<String, String> additionalContextData = new HashMap<String, String>();
                MobileCore.trackState(tab.getText().toString(), null);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}
