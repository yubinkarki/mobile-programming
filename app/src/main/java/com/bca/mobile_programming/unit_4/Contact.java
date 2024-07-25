package com.bca.mobile_programming.unit_4;

import android.util.Log;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.bca.mobile_programming.R;

public class Contact extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle b) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(b);
        setContentView(R.layout.unit_2_hello);

        this.setFinishOnTouchOutside(false);

        Button submitButton = findViewById(R.id.helloSubmitButton);
        Button cancelButton = findViewById(R.id.helloCancelButton);
        EditText fullNameInput = findViewById(R.id.helloFullNameInput);

        submitButton.setOnClickListener(v -> {
            String fullNameValue = fullNameInput.getText().toString();

            if (fullNameValue.isEmpty()) fullNameValue = "No Value";

            Intent i = new Intent();
            i.putExtra("contactData", fullNameValue);
            setResult(RESULT_OK, i);
            Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show();
            finish();
        });

        cancelButton.setOnClickListener(v -> {
            Intent i = new Intent();
            i.putExtra("contactData", "Contact Text");
            setResult(RESULT_OK, i);
            finish();
        });

        OnBackPressedCallback sendDataOnBack = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent i = new Intent();
                i.putExtra("contactData", "Back Button");
                setResult(RESULT_OK, i);
                finish();
            }
        };

        getOnBackPressedDispatcher().addCallback(this, sendDataOnBack);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("myStateLog", "Contact - onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("myStateLog", "Contact - onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("myStateLog", "Contact - onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("myStateLog", "Contact - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("myStateLog", "Contact - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("myStateLog", "Contact - onDestroy");
    }
}
