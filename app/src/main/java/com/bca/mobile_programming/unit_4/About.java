package com.bca.mobile_programming.unit_4;

import android.util.Log;
import android.os.Bundle;
import android.widget.Toast;
import android.app.Activity;
import android.widget.Button;

import com.bca.mobile_programming.R;

public class About extends Activity {
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.unit_2_hello);

        Log.d("myStateLog", "About - onCreate");

        Button cancelButton = findViewById(R.id.cancelButton);
        Button submitButton = findViewById(R.id.submitButton);

        cancelButton.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show());

        submitButton.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show());
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
