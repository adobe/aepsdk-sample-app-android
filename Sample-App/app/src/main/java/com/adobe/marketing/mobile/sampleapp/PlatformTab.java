package com.adobe.marketing.mobile.sampleapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.adobe.marketing.mobile.sampleapp.platform.CommerceUtil;
import com.adobe.marketing.mobile.sampleapp.platform.ProductCart;
import com.adobe.marketing.mobile.sampleapp.platform.ProductContent;

import java.text.NumberFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlatformTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlatformTab extends Fragment implements NavigationAware {
    private static final String LOG_TAG = "PlatformTab";

    public PlatformTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PlatformTab.
     */
    // TODO: Rename and change types and number of parameters
    public static PlatformTab newInstance() {
        PlatformTab fragment = new PlatformTab();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add ProductContent
        ProductContent.addItem(new ProductContent.ProductItem("1", // sku
        "Item One", // name
        "First Item", // details
        1.00, // price
        "USD", // currency
        null, // image small
        null // image large
        ));
        // Add ProductContent
        ProductContent.addItem(new ProductContent.ProductItem("2", // sku
                "Item Two", // name
                "Second Item", // details
                2.00, // price
                "USD", // currency
                null, // image small
                null // image large
        ));
        // Add ProductContent
        ProductContent.addItem(new ProductContent.ProductItem("3", // sku
                "Item Three", // name
                "Third Item", // details
                3.00, // price
                "USD", // currency
                null, // image small
                null // image large
        ));
        // Add ProductContent
        ProductContent.addItem(new ProductContent.ProductItem("4", // sku
                "Item Four", // name
                "Fourth Item", // details
                4.00, // price
                "USD", // currency
                null, // image small
                null // image large
        ));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_platform_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        int checkboxIds[] = new int[] {
                R.id.checkbox_purchase_one,
                R.id.checkbox_purchase_two,
                R.id.checkbox_purchase_three,
                R.id.checkbox_purchase_four,
        };

        for (int id : checkboxIds) {
            view.findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPurchaseCheckboxClicked(v);
                }
            });
        }

        view.findViewById(R.id.button_purchase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPurchase(v);
            }
        });
    }

    @Override
    public void OnNavigateTo() {

    }

    @Override
    public void OnNavigateAway() {

    }

    private void onPurchaseCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        ProductContent.ProductItem item = null;
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_purchase_one:
                item = ProductContent.ITEM_MAP.get("1");
                break;
            case R.id.checkbox_purchase_two:
                item = ProductContent.ITEM_MAP.get("2");
                break;
            case R.id.checkbox_purchase_three:
                item = ProductContent.ITEM_MAP.get("3");
                break;
            case R.id.checkbox_purchase_four:
                item = ProductContent.ITEM_MAP.get("4");
                break;
        }

        if (item != null) {
            if (checked) {
                ProductCart.addItem(item, 1);
                CommerceUtil.sendProductListAddXdmEvent(item, 1);
            } else {
                ProductCart.removeItem(item);
                CommerceUtil.sendProductListRemoveXdmEvent(item, 1);
            }

            // Update display of total price
            updateTotalPriceView();
        }
    }

    private void updateTotalPriceView() {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        String currency = format.format(ProductCart.getTotalPrice());

        TextView priceView = (TextView) getView().findViewById(R.id.lblTotalPrice);
        priceView.setText(String.valueOf(currency));
    }

    private void onPurchase(View view) {
        // Get selected payment method
        RadioGroup radioGroup = (RadioGroup) getView().findViewById(R.id.radio_payment_method);
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) getView().findViewById(radioButtonId);
        String paymentMethod = String.valueOf(radioButton.getText());

        CommerceUtil.sendPurchaseXdmEvent(paymentMethod, ProductCart.getTotalPrice());

        clearCart();
    }

    private void clearCart() {
        ProductCart.clearCart();

        int ids[] = new int[] {
                    R.id.checkbox_purchase_one,
                    R.id.checkbox_purchase_two,
                    R.id.checkbox_purchase_three,
                    R.id.checkbox_purchase_four,
        };

        for (int id : ids) {
            CheckBox checkbox = (CheckBox)getView().findViewById(id);
            if (checkbox.isChecked()) {
                checkbox.toggle();
            }
        }

        // Update display of total price
        updateTotalPriceView();
    }
}