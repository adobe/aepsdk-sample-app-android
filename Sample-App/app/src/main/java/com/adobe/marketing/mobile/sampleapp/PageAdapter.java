/*
 Copyright 2020 Adobe
 All Rights Reserved.

 NOTICE: Adobe permits you to use, modify, and distribute this file in
 accordance with the terms of the Adobe license agreement accompanying
 it.
 */
package com.adobe.marketing.mobile.sampleapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    private int numoftabs;

    private String[] tabTitles;

    private Fragment[] tabFragments = new Fragment[] {
            new CoreTab(),
            new EdgeTab(),
            new EdgeIdentityTab(),
            new ConsentTab(),
            new AssuranceTab(),
            new MessageTab()
    };

    public PageAdapter(FragmentManager fm, final Context context, int numOfTabs) {
        super(fm);
        this.numoftabs = numOfTabs;
        tabTitles = new String[] {
                context.getString(R.string.tab_Core),
                context.getString(R.string.tab_Edge),
                context.getString(R.string.tab_EdgeIdentity),
                context.getString(R.string.tab_Consent),
                context.getString(R.string.tab_Assurance),
                context.getString(R.string.tab_Messaging)
        };
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
