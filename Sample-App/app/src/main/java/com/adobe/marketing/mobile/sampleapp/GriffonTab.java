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
import com.adobe.marketing.mobile.Griffon;


/**
 * A simple {@link Fragment} subclass.
 */
public class GriffonTab extends Fragment implements NavigationAware {

    TextView lblEnterURL = null;
    Button btnConnectToGriffonSession = null;
    EditText txtGriffonSessionURL = null;

    boolean userIsViewingThisFragment = true;
    private static final String LOG_TAG = "Griffon Tab";

    public GriffonTab() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_griffon_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Create references to all our components
        lblEnterURL = getView().findViewById(R.id.lblEnterURL);
        btnConnectToGriffonSession = getView().findViewById(R.id.btnConnectToGriffonSession);
        txtGriffonSessionURL = getView().findViewById(R.id.txtGriffonSessionURL);


        //Setup button events
        btnConnectToGriffonSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Griffon.startSession(txtGriffonSessionURL.getText().toString());
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
