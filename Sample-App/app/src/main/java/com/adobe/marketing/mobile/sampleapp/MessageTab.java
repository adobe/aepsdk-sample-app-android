package com.adobe.marketing.mobile.sampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adobe.marketing.mobile.AdobeCallback;
import com.adobe.marketing.mobile.Messaging;
import com.adobe.marketing.mobile.edge.identity.Identity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MessageTab extends Fragment {
    TextView tvECID;

    private static final String LOG_TAG = "Message Tab";

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

        final TextView tv_version = getView().findViewById(R.id.tv_messaging_version);
        view.post(new Runnable() {
            @Override
            public void run() {
                if (tv_version != null) {
                    tv_version.setText("Messaging v" + Messaging.extensionVersion());
                }
            }
        });


        tvECID = view.findViewById(R.id.tv_lbl_ecidLabel);
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
                        String text = String.format("Messaging SDK setup is complete with ECID - %s. \n\nFor more details please take a look at the documentation in the github repository.", ecid);
                        tvECID.setText(text);
                    }
                });
            }
        });
    }
}
