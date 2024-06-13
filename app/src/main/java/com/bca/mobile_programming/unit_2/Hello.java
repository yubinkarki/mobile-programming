package com.bca.mobile_programming.unit_2;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bca.mobile_programming.R;

public class Hello extends Activity {
    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.unit_2_hello);

        TextView headingText = findViewById(R.id.headingText);
        Button submitButton = findViewById(R.id.submitButton);
        EditText fullNameInput = findViewById(R.id.fullNameInput);

        submitButton.setOnClickListener(v -> {
            String inputValue = fullNameInput.getText().toString();

            if (inputValue.isEmpty()) {
                headingText.setText("Empty!");
            } else {
                headingText.setText(inputValue);
            }
        });
    }
}
