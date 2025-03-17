package com.bca.mobile_programming.unit_4;

import java.util.ArrayList;

import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.ImageButton;
import android.content.res.Resources;

import com.bca.mobile_programming.R;
import com.bca.mobile_programming.unit_1.GeneralUtil;

public class About extends Activity {
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.unit_4_about);

        Log.d("myStateLog", "About - onCreate");

        Resources res = getResources();

        Intent i = getIntent();

        String email = i.getStringExtra("email");
        String gender = i.getStringExtra("gender");
        String country = i.getStringExtra("country");
        String fullName = i.getStringExtra("fullName");
        ArrayList<String> sports = i.getStringArrayListExtra("sports");

        View rootLayout = findViewById(R.id.aboutRoot);
        Button backButton = findViewById(R.id.aboutBackButton);
        TextView sportsText = findViewById(R.id.aboutSportsValue);
        TextView genderText = findViewById(R.id.aboutGenderValue);
        Button dialogButton = findViewById(R.id.aboutDialogButton);
        TextView countryText = findViewById(R.id.aboutCountryValue);
        TextView fullNameText = findViewById(R.id.aboutFullNameValue);
        ImageButton profileIconButton = findViewById(R.id.aboutProfileIconButton);

        backButton.setOnClickListener(v -> finish());

        dialogButton.setOnClickListener(v -> {
            if (email != null && !email.isEmpty()) {
                Toast.makeText(getApplicationContext(), email, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "No email", Toast.LENGTH_SHORT).show();
            }
        });

        profileIconButton.setOnClickListener(v -> {
            String closeMessage = "Dump";
            String message = "This is profile icon";

            GeneralUtil.showMySnack(rootLayout, message, closeMessage);
        });

        genderText.setText(gender);
        countryText.setText(country);

        if (fullName != null && !fullName.isEmpty()) fullNameText.setText(fullName);
        else fullNameText.setText(res.getString(R.string.anonymous));

        if (sports != null && !sports.isEmpty()) {
            String joinedSports = String.join(", ", sports);
            sportsText.setText(joinedSports);
        } else {
            Toast.makeText(getApplicationContext(), "No sports found", Toast.LENGTH_SHORT).show();
            sportsText.setText(res.getString(R.string.na));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("myStateLog", "About - onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("myStateLog", "About - onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("myStateLog", "About - onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("myStateLog", "About - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("myStateLog", "About - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("myStateLog", "About - onDestroy");
    }
}
