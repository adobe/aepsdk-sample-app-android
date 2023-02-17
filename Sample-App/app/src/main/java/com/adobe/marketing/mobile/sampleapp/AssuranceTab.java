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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.adobe.marketing.mobile.Assurance;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssuranceTab extends Fragment implements NavigationAware {

    boolean userIsViewingThisFragment = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assurance_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final TextView tv_version = getView().findViewById(R.id.tv_assurance_version);
        view.post(new Runnable() {
            @Override
            public void run() {
                if (tv_version != null) {
                    tv_version.setText("Assurance v" + Assurance.extensionVersion());
                }
            }
        });

        //Create references to all our components
        final Button btnConnectToAssuranceSession = getView().findViewById(R.id.btnConnectToAssuranceSession);
        final EditText txtAssuranceSessionURL = getView().findViewById(R.id.txtAssuranceSessionURL);


        // start session
        btnConnectToAssuranceSession.setOnClickListener(v -> Assurance.startSession(txtAssuranceSessionURL.getText().toString()));
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
