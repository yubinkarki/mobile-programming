package com.bca.mobile_programming.unit_3;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.bca.mobile_programming.R;

public class Signup extends Activity {
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.unit_3_signup);

        Spinner countrySpinner = findViewById(R.id.signupCountrySpinner);
        String[] countryList = getResources().getStringArray(R.array.country_list);

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this, R.layout.unit_3_spinner_item, countryList);
        countrySpinner.setAdapter(countryAdapter);
    }
}
