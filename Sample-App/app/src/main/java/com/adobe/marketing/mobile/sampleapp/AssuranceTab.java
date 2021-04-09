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
import com.adobe.marketing.mobile.Edge;


/**
 * A simple {@link Fragment} subclass.
 */
public class AssuranceTab extends Fragment implements NavigationAware {

    TextView lblEnterURL = null;
    Button btnConnectToAssuranceSession = null;
    EditText txtAssuranceSessionURL = null;

    boolean userIsViewingThisFragment = true;
    private static final String LOG_TAG = "Assurance Tab";

    public AssuranceTab() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assurance_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final TextView tv_version  = getView().findViewById(R.id.tv_assurance_version);
        view.post(new Runnable() {
            @Override
            public void run() {
                if (tv_version != null) {
                    tv_version.setText("Assurance Extension version: " + Assurance.extensionVersion());
                }
            }
        });

        //Create references to all our components
        lblEnterURL = getView().findViewById(R.id.lblEnterURL);
        btnConnectToAssuranceSession = getView().findViewById(R.id.btnConnectToAssuranceSession);
        txtAssuranceSessionURL = getView().findViewById(R.id.txtAssuranceSessionURL);


        //Setup button events
        btnConnectToAssuranceSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Assurance.startSession(txtAssuranceSessionURL.getText().toString());
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
