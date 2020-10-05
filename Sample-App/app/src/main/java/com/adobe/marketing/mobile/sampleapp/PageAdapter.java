/*
 Copyright 2020 Adobe
 All Rights Reserved.

 NOTICE: Adobe permits you to use, modify, and distribute this file in
 accordance with the terms of the Adobe license agreement accompanying
 it.
 */
package com.adobe.marketing.mobile.sampleapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    private int numoftabs;

    // TODO: Places these tab titles in string resources. Access them like this: Resources.getSystem().getString(R.string.<string_name>)
    private String[] tabTitles = new String[]{"Griffon", "Analytics", "Platform"};

    private Fragment[] tabFragments = new Fragment[]{new GriffonTab(), new AnalyticsTab(), new PlatformTab()};

    private GriffonTab griffonTab = null;
    private AnalyticsTab analyticsTab = null;

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numoftabs = numOfTabs;
    }



    @Override
    public Fragment getItem(int position) {
        return tabFragments[position];
    }

    public Fragment[] GetFragmentArray(){
        return tabFragments;
    }

    // overriding getPageTitle()
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return numoftabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
