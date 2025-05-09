package com.bca.mobile_programming.unit_3;

import android.util.Log;
import android.os.Bundle;
import android.view.Window;
import android.app.Activity;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

import androidx.core.view.WindowCompat;

import com.bca.mobile_programming.R;
import com.bca.mobile_programming.unit_4.Home;

public class Login extends Activity {
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("myStateLog", "Login - onStart");

        Window window = getWindow();
        WindowCompat.setDecorFitsSystemWindows(window, false); // Enable edge to edge
    }

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.unit_3_login);
        Log.d("myStateLog", "Login - onCreate");

        Button submitButton = findViewById(R.id.loginSubmitButton);
        TextView signupText = findViewById(R.id.loginNoAccountText);

        signupText.setOnClickListener(v -> {
            Intent i = new Intent(Login.this, Signup.class);
            startActivity(i);
        });

        submitButton.setOnClickListener(v -> {
            Intent i = new Intent(Login.this, Home.class);
            startActivity(i);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("myStateLog", "Login - onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("myStateLog", "Login - onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("myStateLog", "Login - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("myStateLog", "Login - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("myStateLog", "Login - onDestroy");
    }
}
