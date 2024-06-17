package com.bca.mobile_programming.unit_4;

import android.util.Log;
import android.os.Bundle;
import android.widget.Toast;
import android.app.Activity;
import android.widget.Button;
import android.content.Intent;

import com.bca.mobile_programming.R;

public class Contact extends Activity {
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.unit_2_hello);

        this.setFinishOnTouchOutside(false);
        Log.d("myStateLog", "Contact - onCreate");

        Button submitButton = findViewById(R.id.helloSubmitButton);
        Button cancelButton = findViewById(R.id.helloCancelButton);

        submitButton.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show());

        cancelButton.setOnClickListener(v -> {
            Intent i = new Intent();
            i.putExtra("contactData", "Contact Text");
            setResult(RESULT_OK, i);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.putExtra("contactData", "Back Button");
        setResult(RESULT_OK, i);
        finish();
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
