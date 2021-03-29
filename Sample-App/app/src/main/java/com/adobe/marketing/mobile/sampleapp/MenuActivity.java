package com.adobe.marketing.mobile.sampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = "MenuActivity";

    Button btnCore;
    Button btnEdge;
    Button btnAssurance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Get the URI from the data elements of the applications intent com.adobe.marketing.mobile.sampleapp.MainActivity
        final Intent intent = getIntent();
        final Uri data = intent.getData();

        if (data != null) {
            Log.d(LOG_TAG, "Connected successfully to Assurance with the URI : " + data.toString());
        }

        btnCore = findViewById(R.id.btn_core);
        btnEdge = findViewById(R.id.btn_edge);
        btnAssurance = findViewById(R.id.btn_assurance);

        btnCore.setOnClickListener(this);
        btnEdge.setOnClickListener(this);
        btnAssurance.setOnClickListener(this);
    }

    private void openMainActivity(int tab) {
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        intent.putExtra(AppConstants.INTENT_TAB_KEY,  tab);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_core: openMainActivity(1);
                break;
            case R.id.btn_edge: openMainActivity(2);
                break;
            case R.id.btn_assurance: openMainActivity(0);
                break;
            default:
                break;
        }
    }
}
