package com.bca.mobile_programming.unit_2;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bca.mobile_programming.R;

public class Hello extends Activity {
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.unit_2_hello);

        TextView headingText = findViewById(R.id.helloHeadingText);
        Button submitButton = findViewById(R.id.helloSubmitButton);
        EditText fullNameInput = findViewById(R.id.helloFullNameInput);

        submitButton.setOnClickListener(v -> {
            String inputValue = fullNameInput.getText().toString();

            if (inputValue.isEmpty()) headingText.setText(R.string.na);
            else headingText.setText(inputValue);
        });
    }
}
