package com.bca.mobile_programming.unit_3;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.bca.mobile_programming.R;

public class Hello extends Activity {
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.unit_3_constraint);

        Spinner countrySpinner = findViewById(R.id.constraintCountrySpinner);
        String[] countryList = {"Korea", "Netherlands", "United Kingdom", "Germany"};

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this, R.layout.unit_3_spinner_item, countryList);
        countrySpinner.setAdapter(countryAdapter);
    }
}
