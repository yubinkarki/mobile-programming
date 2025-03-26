package com.bca.mobile_programming.unit_3;

import java.util.ArrayList;

import android.util.Log;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.bca.mobile_programming.R;
import com.bca.mobile_programming.unit_2.Hello;
import com.bca.mobile_programming.unit_4.About;


public class Signup extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("myStateLog", "Signup - onPause");

        if (getSupportActionBar() != null) getSupportActionBar().hide();
    }

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.unit_3_signup);
        Log.d("myStateLog", "Signup - onCreate");

        Button faqButton = findViewById(R.id.signupFaqButton);
        EditText fullNameInput = findViewById(R.id.signupNameInput);
        Button signupButton = findViewById(R.id.signupSubmitButton);
        TextView signupHeadingText = findViewById(R.id.signupHeading);
        TextView loginText = findViewById(R.id.signupHaveAccountText);
        Spinner countrySpinner = findViewById(R.id.signupCountrySpinner);
        RadioGroup genderGroup = findViewById(R.id.signupGenderRadioGroup);
        CheckBox footballCheckbox = findViewById(R.id.signupCheckboxFootball);
        CheckBox badmintonCheckbox = findViewById(R.id.signupCheckboxBadminton);
        CheckBox basketballCheckbox = findViewById(R.id.signupCheckboxBasketball);
        CheckBox volleyballCheckbox = findViewById(R.id.signupCheckboxVolleyball);

        String[] countryList = getResources().getStringArray(R.array.country_list);

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this, R.layout.unit_3_spinner_item, countryList);
        countrySpinner.setAdapter(countryAdapter);

        // To get data back from the destination activity - should extend AppCompatActivity
        ActivityResultLauncher<Intent> contactLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();

                    if (data != null) {
                        String message = data.getStringExtra("contactData");
                        signupHeadingText.setText(message);
                    }
                }
            });

        loginText.setOnClickListener(v -> finish());

        // Sending static/dynamic data to About activity via Intent
        signupButton.setOnClickListener(v -> {
            Intent i = new Intent(Signup.this, About.class);

            // i.putExtra("gender", "Male");
            // i.putExtra("country", "Nepal");
            // i.putExtra("fullName", "Yubin Karki");
            // i.putStringArrayListExtra("sports", new ArrayList<>(Arrays.asList("Football", "Basketball", "Tennis")));

            String selectedGender = "";
            ArrayList<String> selectedSports = new ArrayList<>();
            String fullName = fullNameInput.getText().toString();
            int checkedRadioButtonId = genderGroup.getCheckedRadioButtonId();
            String selectedCountry = countrySpinner.getSelectedItem().toString();

            if (checkedRadioButtonId == R.id.signupMaleRadioButton) selectedGender = "Male";
            else if (checkedRadioButtonId == R.id.signupFemaleRadioButton) selectedGender = "Female";

            CheckBox[] checkboxes = {footballCheckbox, badmintonCheckbox, basketballCheckbox, volleyballCheckbox};

            for (CheckBox checkbox : checkboxes) {
                if (checkbox.isChecked()) selectedSports.add(checkbox.getText().toString());
            }

            i.putExtra("fullName", fullName);
            i.putExtra("gender", selectedGender);
            i.putExtra("country", selectedCountry);
            i.putStringArrayListExtra("sports", selectedSports);

            startActivity(i);
        });

        faqButton.setOnClickListener(v -> {
            Intent i = new Intent(Signup.this, Hello.class);
            contactLauncher.launch(i);
        });
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == contactCode && resultCode == RESULT_OK) {
            String message = data.getStringExtra("contactData");
            headingText.setText(message);
        }
    } */

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("myStateLog", "Signup - onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("myStateLog", "Signup - onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("myStateLog", "Signup - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("myStateLog", "Signup - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("myStateLog", "Signup - onDestroy");
    }
}
