package com.adobe.marketing.mobile.sampleapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.adobe.marketing.mobile.AdobeCallbackWithError;
import com.adobe.marketing.mobile.AdobeError;
import com.adobe.marketing.mobile.Edge;
import com.adobe.marketing.mobile.ExperienceEvent;
import com.adobe.marketing.mobile.optimize.DecisionScope;
import com.adobe.marketing.mobile.optimize.Offer;
import com.adobe.marketing.mobile.optimize.Optimize;
import com.adobe.marketing.mobile.optimize.Proposition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OptimizeTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OptimizeTab extends Fragment {

    boolean userIsViewingThisFragment = true;
    final ArrayList<Offer> htmlOfferList = new ArrayList<Offer>();


    public OptimizeTab() {
        // Required empty public constructor
    }


    public static OptimizeTab newInstance() {
        OptimizeTab fragment = new OptimizeTab();
        return fragment;
    }

    public void ShowExperience() {

        String htmlString ;

        if(!htmlOfferList.isEmpty()) {

            htmlString = htmlOfferList.get(0).getContent();

            WebView mywebview = (WebView) getView().findViewById(R.id.viewMbox);
            mywebview.clearCache(false);
            mywebview.loadData(htmlString, "text/html", "UTF-8");

            // Emit the displayed event when the HTML offer is clicked
            htmlOfferList.get(0).displayed();
        }

    }

    public void FetchTargetPropositions() {

        // The following code is a sample showing how a custom parameter 'myAnimal' is used to fetch the right experience to show to the users
        // Please modify this code for your specific Target activity and parameters

        // Step 1: Add mbox parameters
        final Map<String, Object> data = new HashMap<>();
        final Map<String, String> targetParameters = new HashMap<>();
        EditText textFieldAnimal = getView().findViewById(R.id.txtAnimalChoice);
        targetParameters.put("myAnimal", textFieldAnimal.getText().toString());
        data.put("__adobe", new HashMap<String, Object>() {
            { put("target", targetParameters); }
        });

        // Step 2: Define a decisionScope which matches the hardcoded location.
        DecisionScope decisionScope = new DecisionScope("_TargetPoweredLoc");
        final ArrayList<DecisionScope> decisionScopeList = new ArrayList<DecisionScope>();
        decisionScopeList.add(decisionScope);

        // Step 3: Call the API method updatePropositions to fetch the experiences from Target
        Optimize.updatePropositions(decisionScopeList, null, data);

        // Step 4: Implement the callback method which will be called when the propositions are returned
        Proposition proposition;
        Optimize.onPropositionsUpdate(new AdobeCallbackWithError<Map<DecisionScope, Proposition>>() {
            @Override
            public void fail(final AdobeError adobeError) {
                // handle error
            }

            @Override
            public void call(final Map<DecisionScope, Proposition> propositionsMap) {

                if (propositionsMap != null && !propositionsMap.isEmpty()) {
                    // handle propositions
                    for (Proposition prop : propositionsMap.values()) {
                        htmlOfferList.addAll(prop.getOffers());
                    }
                }
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_optimize_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final TextView tv_version = getView().findViewById(R.id.tv_optimize_version);
        view.post(new Runnable() {
            @Override
            public void run() {
                if (tv_version != null) {
                    tv_version.setText("Optimize v" + Optimize.extensionVersion());
                }
            }
        });

        Button buttonFetchPropositions = view.findViewById(R.id.btnFetchExperience);
        buttonFetchPropositions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchTargetPropositions();
            }
        });

        Button buttonShowPropositions = view.findViewById(R.id.btnShowExperience);
        buttonShowPropositions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowExperience();
            }
        });
    }
}
