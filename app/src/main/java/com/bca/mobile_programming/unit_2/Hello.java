package com.bca.mobile_programming.unit_2;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.content.Intent;
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
        Button cancelButton = findViewById(R.id.helloCancelButton);
        EditText fullNameInput = findViewById(R.id.helloFullNameInput);

        submitButton.setOnClickListener(v -> {
            String inputValue = fullNameInput.getText().toString();

            if (inputValue.isEmpty()) {
                headingText.setText(R.string.na);
            } else headingText.setText(inputValue);
        });

        // Sending result back to the previous activity
        cancelButton.setOnClickListener(v -> {
            String sendBackData = fullNameInput.getText().toString();

            if(sendBackData.isEmpty()) sendBackData = "Default Value";

            Intent i = new Intent();
            i.putExtra("contactData", sendBackData);
            setResult(RESULT_OK, i);
            finish();
        });

    }
}
