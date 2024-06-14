package com.bca.mobile_programming.unit_4;

import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.bca.mobile_programming.R;

public class Home extends Activity {
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.unit_3_constraint);

        Log.d("myStateLog", "Home - onCreate");

        Button cancelButton = findViewById(R.id.cancelButton);
        Button submitButton = findViewById(R.id.submitButton);
        Spinner countrySpinner = findViewById(R.id.countrySpinner);

        String[] countryList = {"Korea", "Netherlands", "United Kingdom", "Germany"};

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this, R.layout.unit_3_spinner_item, countryList);
        countrySpinner.setAdapter(countryAdapter);

        cancelButton.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show());

        submitButton.setOnClickListener(v -> {
            Intent i = new Intent(Home.this, About.class);
            startActivity(i);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("myStateLog", "Home - onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "Resumed", Toast.LENGTH_LONG).show();
        Log.d("myStateLog", "Home - onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("myStateLog", "Home - onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("myStateLog", "Home - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("myStateLog", "Home - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("myStateLog", "Home - onDestroy");
    }
}
