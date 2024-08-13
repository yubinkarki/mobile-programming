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
import android.app.Activity;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.widget.Button;
import android.view.ViewGroup;
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
import android.view.LayoutInflater;
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
import androidx.core.splashscreen.SplashScreen;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.bca.mobile_programming.R;
import com.bca.mobile_programming.unit_1.AlertUtil;
import com.bca.mobile_programming.unit_1.GeneralUtil;
import com.bca.mobile_programming.unit_1.KeyboardUtil;
import com.bca.mobile_programming.unit_6.GridViewMain;
import com.bca.mobile_programming.unit_6.ListViewMain;
import com.bca.mobile_programming.unit_7.UserProfileMain;
import com.bca.mobile_programming.unit_6.CustomGridViewMain;
import com.bca.mobile_programming.unit_6.CustomListViewMain;
import com.bca.mobile_programming.unit_6.RecyclerViewListMain;
import com.bca.mobile_programming.unit_7.UserProfileServerMain;
import com.bca.mobile_programming.unit_5.FragmentSwitchActivity;
import com.bca.mobile_programming.unit_5.ImagesFragmentActivity;

public class Home extends AppCompatActivity {
    private boolean keepSplash = true;

    private Resources res;
    private Handler handler;
    private Button dashButton;
    private ViewGroup rootLayout;
    private TextView headingText;
    private EditText fullNameInput;
    private KeyboardUtil keyboardUtil;
    private ActivityResultLauncher<Intent> contactLauncher;

    @Override
    protected void onStart() {
        super.onStart();

        ActionBar bar = getSupportActionBar();
        keyboardUtil = new KeyboardUtil(this);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.lighter_blue));

        if (bar != null) {
            int color = ContextCompat.getColor(this, R.color.lighter_blue);
            int textColor = ContextCompat.getColor(this, R.color.dark_gray);
            Spannable text = new SpannableString(res.getString(R.string.happy_dashain));

            text.setSpan(new AbsoluteSizeSpan(20, true), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            text.setSpan(new ForegroundColorSpan(textColor), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            text.setSpan(new LeadingMarginSpan.Standard(20), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            bar.setTitle(text);
            bar.setElevation(10);
            bar.setBackgroundDrawable(new ColorDrawable(color));
        }
    }

    @Override
    protected void onCreate(Bundle b) {
        SplashScreen splash = SplashScreen.installSplashScreen(this);
        splash.setKeepOnScreenCondition(() -> keepSplash);

        handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> keepSplash = false, 800);

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

        String heading = res.getString(R.string.kyc_form);
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

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this, R.layout.unit_3_spinner_item, R.id.spinnerItemText, planetList);
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

            if (keyboard) {
                keyboardUtil.hideKeyboard(v);
                handler.postDelayed(() -> contactLauncher.launch(i), 150);
                // handler.postDelayed(() -> startActivityForResult(i, contactCode), 150);
            } else contactLauncher.launch(i);
        });

        dialogButton.setOnClickListener(v -> {
            AlertUtil alert = new AlertUtil(this, rootLayout);
            alert.show(getSupportFragmentManager(), "alert");
        });

        fullNameInput.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                fullNameInput.clearFocus();
                keyboardUtil.hideKeyboard(v);
                fullNameInput.setFocusable(false);
                fullNameInput.setFocusableInTouchMode(true);
                headingText.setText(fullNameInput.getText().toString());
                fullNameInput.setText("");
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
            LayoutInflater inflater = getLayoutInflater();
            View calculatorView = inflater.inflate(R.layout.unit_5_add_calculator, null);

            TextView resultText = calculatorView.findViewById(R.id.addCalculatorResultValue);
            EditText firstNumber = calculatorView.findViewById(R.id.addCalculatorFirstNumberInput);
            Button calculateButton = calculatorView.findViewById(R.id.addCalculatorCalculateButton);
            EditText secondNumber = calculatorView.findViewById(R.id.addCalculatorSecondNumberInput);

            calculateButton.setOnClickListener(c -> {
                int first, second, result;

                String firstValue = firstNumber.getText().toString();
                String secondValue = secondNumber.getText().toString();

                if (firstValue.isEmpty()) first = 0;
                else first = Integer.parseInt(firstValue);

                if (secondValue.isEmpty()) second = 0;
                else second = Integer.parseInt(secondValue);

                result = first + second;
                resultText.setText(String.valueOf(result));
            });

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setView(calculatorView);
            builder.create();
            builder.show();

            return true;
        });

        dashButton.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(Home.this, v);
            popup.getMenuInflater().inflate(R.menu.popup_options, popup.getMenu());

            popup.setOnMenuItemClickListener(item -> {
                String close = "Bye";
                int selectedItem = item.getItemId();

                if (selectedItem == R.id.popupOptionsProfile) {
                    Intent i = new Intent(Home.this, UserProfileMain.class);
                    startActivity(i);
                    return true;
                } else if (selectedItem == R.id.popupOptionsServices) {
                    GeneralUtil.showMySnack(rootLayout, "Popup services", close);
                    return true;
                } else if (selectedItem == R.id.popupOptionsProfileServer) {
                    Intent i = new Intent(Home.this, UserProfileServerMain.class);
                    startActivity(i);
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

        if (selectedItem == R.id.appOptionsListView) {
            Intent i = new Intent(Home.this, ListViewMain.class);
            startActivity(i);
            return true;
        } else if (selectedItem == R.id.appOptionsCustomListView) {
            Intent i = new Intent(Home.this, CustomListViewMain.class);
            startActivity(i);
            return true;
        } else if (selectedItem == R.id.appOptionsGridView) {
            Intent i = new Intent(Home.this, GridViewMain.class);
            startActivity(i);
            return true;
        } else if (selectedItem == R.id.appOptionsCustomGridView) {
            Intent i = new Intent(Home.this, CustomGridViewMain.class);
            startActivity(i);
            return true;
        } else if (selectedItem == R.id.appOptionsRecyclerViewList) {
            Intent i = new Intent(Home.this, RecyclerViewListMain.class);
            startActivity(i);
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
        handler.removeCallbacksAndMessages(null);
    }
}
