package com.adobe.marketing.mobile.sampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
import com.adobe.marketing.mobile.Edge;
import com.adobe.marketing.mobile.EdgeCallback;
import com.adobe.marketing.mobile.EdgeEventHandle;
import com.adobe.marketing.mobile.ExperienceEvent;
import com.adobe.marketing.mobile.edge.identity.Identity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MessageTab extends Fragment implements NavigationAware {
    TextView tvECID;
    EditText etEmail;
    Button btnSyncEmail;

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
        etEmail = view.findViewById(R.id.et_emailId);
        btnSyncEmail = (Button) view.findViewById(R.id.btn_sendEmail);

        btnSyncEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Identity.getExperienceCloudId(new AdobeCallback<String>() {
                    @Override
                    public void call(final String ecid) {
                        Map<String, Object> xdm = new HashMap<>();
                        Map<String, Object> identity = new LinkedHashMap<>();

                        ArrayList<Map<String, String>> ecidList = new ArrayList<>();
                        Map<String, String> ecidID = new HashMap<>();
                        ecidID.put("id", ecid);
                        ecidList.add(ecidID);
                        identity.put("ECID", ecidList);

                        ArrayList<Map<String, String>> emailList = new ArrayList<>();
                        Map<String, String> emailID = new HashMap<>();
                        emailID.put("id", etEmail.getText().toString());
                        emailList.add(emailID);
                        identity.put("Email", emailList);

                        xdm.put("identityMap", identity);

                        ExperienceEvent event = new ExperienceEvent.Builder().setData(null).setXdmSchema(xdm, MainApp.EMAIL_UPDATE_DATASET).build();
                        Edge.sendEvent(event, new EdgeCallback() {
                            @Override
                            public void onComplete(List<EdgeEventHandle> list) {
                                Log.d(LOG_TAG, "onComplete");
                            }
                        });
                    }
                });
            }
        });
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
