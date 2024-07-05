package com.bca.mobile_programming.unit_4;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import android.util.Log;
import android.os.Looper;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.os.Handler;
import android.widget.Toast;
import android.app.Activity;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.widget.Button;
import android.content.Intent;
import android.text.Spannable;
import android.widget.Spinner;
import android.app.AlertDialog;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.PopupMenu;
import android.view.ContextMenu;
import android.widget.RadioGroup;
import android.widget.ArrayAdapter;
import android.text.SpannableString;
import android.content.res.Resources;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.ForegroundColorSpan;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.bca.mobile_programming.R;
import com.bca.mobile_programming.unit_1.GeneralUtil;
import com.bca.mobile_programming.unit_1.KeyboardUtil;
import com.bca.mobile_programming.unit_5.FragmentSwitchActivity;
import com.bca.mobile_programming.unit_5.ImagesFragmentActivity;

public class Home extends AppCompatActivity {
    private Resources res;
    private View rootLayout;
    private Button dashButton;
    private TextView headingText;
    private EditText fullNameInput;
    private KeyboardUtil keyboardUtil;
    private ActivityResultLauncher<Intent> contactLauncher;

    @Override
    protected void onStart() {
        super.onStart();

        keyboardUtil = new KeyboardUtil(this);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.lighter_blue));

        if (getSupportActionBar() != null) {
            ActionBar bar = getSupportActionBar();
            int color = ContextCompat.getColor(this, R.color.lighter_blue);
            int textColor = ContextCompat.getColor(this, R.color.dark_gray);
            Spannable text = new SpannableString(res.getString(R.string.happy_dashain));

            text.setSpan(new ForegroundColorSpan(textColor), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            text.setSpan(new AbsoluteSizeSpan(20, true), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            text.setSpan(new LeadingMarginSpan.Standard(20), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            bar.setTitle(text);
            bar.setElevation(10);
            bar.setBackgroundDrawable(new ColorDrawable(color));
        }
    }

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.unit_3_constraint);

        res = getResources();

        contactLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();

                if (data != null) {
                    String message = data.getStringExtra("contactData");
                    headingText.setText(message);
                }
            }
        });

        String noText = res.getString(R.string.no);
        String yesText = res.getString(R.string.yes);
        String heading = res.getString(R.string.kyc_form);
        String alertTitle = res.getString(R.string.alert_title);
        String alertMessage = res.getString(R.string.alert_message);
        String[] planetList = res.getStringArray(R.array.planet_list);

        rootLayout = findViewById(R.id.constraintRoot);
        dashButton = findViewById(R.id.constraintDashButton);
        headingText = findViewById(R.id.constraintHeadingText);
        fullNameInput = findViewById(R.id.constraintFullNameInput);
        Button resetButton = findViewById(R.id.constraintResetButton);
        Button aboutButton = findViewById(R.id.constraintAboutButton);
        Button dialogButton = findViewById(R.id.constraintDialogButton);
        Button switchButton = findViewById(R.id.constraintSwitchButton);
        Button resultButton = findViewById(R.id.constraintResultButton);
        Button imagesButton = findViewById(R.id.constraintFragmentButton);
        RadioGroup genderGroup = findViewById(R.id.constraintGenderGroup);
        Spinner countrySpinner = findViewById(R.id.constraintCountrySpinner);
        Button activityDialogButton = findViewById(R.id.constraintActivityButton);
        CheckBox footballCheckbox = findViewById(R.id.constraintCheckboxFootball);
        CheckBox badmintonCheckbox = findViewById(R.id.constraintCheckboxBadminton);
        CheckBox basketballCheckbox = findViewById(R.id.constraintCheckboxBasketball);
        CheckBox volleyballCheckbox = findViewById(R.id.constraintCheckboxVolleyball);

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this, R.layout.unit_3_spinner_item, planetList);
        countrySpinner.setAdapter(countryAdapter);

        resetButton.setOnClickListener(v -> {
            fullNameInput.setText("");
            fullNameInput.clearFocus();
            headingText.setText(heading);
            keyboardUtil.hideKeyboard(fullNameInput);
        });

        aboutButton.setOnClickListener(v -> {
            Intent i = new Intent(Home.this, About.class);
            boolean keyboard = keyboardUtil.isKeyboardVisible;
            Handler handler = new Handler(Looper.getMainLooper());

            i.putExtra("gender", "Male");
            i.putExtra("country", "Nepal");
            i.putExtra("fullName", "Yubin Karki");
            i.putStringArrayListExtra("sports", new ArrayList<>(Arrays.asList("Football", "Basketball", "Tennis")));

            if (keyboard) {
                keyboardUtil.hideKeyboard(v);
                handler.postDelayed(() -> startActivity(i), 150);
            } else startActivity(i);
        });

        resultButton.setOnClickListener(v -> {
            Intent i = new Intent(Home.this, About.class);
            boolean keyboard = keyboardUtil.isKeyboardVisible;
            ArrayList<String> selectedSports = new ArrayList<>();
            String fullName = fullNameInput.getText().toString();
            Handler handler = new Handler(Looper.getMainLooper());
            int checkedRadioButtonId = genderGroup.getCheckedRadioButtonId();
            String selectedCountry = countrySpinner.getSelectedItem().toString();
            AtomicReference<String> selectedGender = new AtomicReference<>("Unknown");

            if (checkedRadioButtonId == R.id.genderMale)
                selectedGender.set(res.getString(R.string.male));
            else if (checkedRadioButtonId == R.id.genderFemale)
                selectedGender.set(res.getString(R.string.female));

            CheckBox[] checkboxes = {footballCheckbox, badmintonCheckbox, basketballCheckbox, volleyballCheckbox};

            for (CheckBox checkbox : checkboxes)
                if (checkbox.isChecked()) selectedSports.add(checkbox.getText().toString());

            i.putExtra("fullName", fullName);
            i.putExtra("gender", selectedGender.get());
            i.putExtra("country", selectedCountry);
            i.putStringArrayListExtra("sports", selectedSports);

            if (keyboard) {
                keyboardUtil.hideKeyboard(v);
                handler.postDelayed(() -> startActivity(i), 150);
            } else startActivity(i);
        });

        activityDialogButton.setOnClickListener(v -> {
            Intent i = new Intent(Home.this, Contact.class);
            boolean keyboard = keyboardUtil.isKeyboardVisible;
            Handler handler = new Handler(Looper.getMainLooper());

            if (keyboard) {
                keyboardUtil.hideKeyboard(v);
                handler.postDelayed(() -> contactLauncher.launch(i), 150);
                // handler.postDelayed(() -> startActivityForResult(i, contactCode), 150);
            } else contactLauncher.launch(i);
        });

        dialogButton.setOnClickListener(v -> {
            String positive = res.getString(R.string.positive);
            String negative = res.getString(R.string.negative);
            String closeMessage = res.getString(R.string.dismiss);

            alertBuilder.setTitle(alertTitle).setMessage(alertMessage).setCancelable(false);

            alertBuilder.setPositiveButton(yesText, (dialog, which) -> {
                dialog.cancel();
                GeneralUtil.showMySnack(rootLayout, positive, closeMessage);
            });

            alertBuilder.setNegativeButton(noText, (dialog, which) -> {
                dialog.cancel();
                GeneralUtil.showMySnack(rootLayout, negative, closeMessage);
            });

            AlertDialog alert = alertBuilder.create();
            alert.show();
        });

        fullNameInput.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                keyboardUtil.hideKeyboard(v);
                fullNameInput.setFocusable(false);
                fullNameInput.setFocusableInTouchMode(true);
                fullNameInput.setText(fullNameInput.getText().toString());
                return true;
            } else return false;
        });

        imagesButton.setOnClickListener(v -> {
            Intent i = new Intent(Home.this, ImagesFragmentActivity.class);
            startActivity(i);
        });

        switchButton.setOnClickListener(v -> {
            Intent i = new Intent(Home.this, FragmentSwitchActivity.class);
            startActivity(i);
        });

        switchButton.setOnLongClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Long Press", Toast.LENGTH_SHORT).show();
            return true;
        });

        dashButton.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(Home.this, v);
            popup.getMenuInflater().inflate(R.menu.popup_options, popup.getMenu());

            popup.setOnMenuItemClickListener(item -> {
                String close = "Bye";
                int selectedItem = item.getItemId();

                if (selectedItem == R.id.popupOptionsAbout) {
                    GeneralUtil.showMySnack(rootLayout, "Popup about", close);
                    return true;
                } else if (selectedItem == R.id.popupOptionsServices) {
                    GeneralUtil.showMySnack(rootLayout, "Popup services", close);
                    return true;
                }

                return false;
            });

            popup.show();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String close = "Go Away";
        int selectedItem = item.getItemId();

        if (selectedItem == R.id.appOptionsAbout) {
            GeneralUtil.showMySnack(rootLayout, "You pressed About. Awesome!", close);
            return true;
        } else if (selectedItem == R.id.appOptionsServices) {
            GeneralUtil.showMySnack(rootLayout, "You pressed Services. Great!", close);
            return true;
        } else if (selectedItem == R.id.appOptionsTheme) {
            GeneralUtil.showMySnack(rootLayout, "You pressed Theme. Sugoi!", close);
            return true;
        } else if (selectedItem == R.id.appOptionsLogout) {
            GeneralUtil.showMySnack(rootLayout, "You pressed Logout. Naniii!", close);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.post_item_options, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerForContextMenu(dashButton);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("myStateLog", "Home - onPause");
        fullNameInput.clearFocus();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("myStateLog", "Home - onStop");
        fullNameInput.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("myStateLog", "Home - onDestroy");
        if (keyboardUtil != null) keyboardUtil.removeListener();
    }
}
