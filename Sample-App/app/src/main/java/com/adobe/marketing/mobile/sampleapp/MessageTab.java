package com.adobe.marketing.mobile.sampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.adobe.marketing.mobile.AdobeCallback;
import com.adobe.marketing.mobile.edge.identity.Identity;

public class MessageTab extends Fragment implements NavigationAware {
    TextView tvECID;

    private static final String LOG_TAG = "Assurance Tab";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messaging_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvECID = view.findViewById(R.id.tv_ecIDText);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Activity activity = getActivity();

        if (activity == null) return;

        Identity.getExperienceCloudId(new AdobeCallback<String>() {
            @Override
            public void call(final String ecid) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvECID.setText(ecid);
                    }
                });
            }
        });
    }

    @Override
    public void OnNavigateTo() { }

    @Override
    public void OnNavigateAway() { }
}
