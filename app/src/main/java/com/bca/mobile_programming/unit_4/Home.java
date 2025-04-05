package com.bca.mobile_programming.unit_4;

import android.util.Log;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.MenuItem;
import android.widget.Button;
import android.view.ViewGroup;
import android.content.Intent;
import android.text.Spannable;
import android.app.AlertDialog;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.WindowManager;
import android.view.LayoutInflater;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.ForegroundColorSpan;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import com.bca.mobile_programming.R;
import com.bca.mobile_programming.unit_1.AlertUtil;
import com.bca.mobile_programming.unit_1.GeneralUtil;
import com.bca.mobile_programming.unit_7.MapViewMain;
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
    private Button dashButton;
    private ViewGroup rootLayout;

    @Override
    protected void onStart() {
        super.onStart();

        // Setting color of status bar
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.lighter_blue));

        ActionBar bar = getSupportActionBar();

        // Changing action bar properties
        if (bar != null) {
            int color = ContextCompat.getColor(this, R.color.lighter_blue);
            int textColor = ContextCompat.getColor(this, R.color.dark_gray);
            Spannable text = new SpannableString(getResources().getString(R.string.home));

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
        super.onCreate(b);
        setContentView(R.layout.unit_4_home);

        rootLayout = findViewById(R.id.homeRoot);
        dashButton = findViewById(R.id.homeDashButton);
        Button dialogButton = findViewById(R.id.homeDialogButton);
        Button switchButton = findViewById(R.id.homeSwitchButton);
        Button imagesButton = findViewById(R.id.homeFragmentButton);
        Button activityDialogButton = findViewById(R.id.homeActivityButton);

        activityDialogButton.setOnClickListener(v -> {
            Intent i = new Intent(Home.this, Contact.class);
            startActivity(i);
        });

        dialogButton.setOnClickListener(v -> {
            AlertUtil alert = new AlertUtil(this, rootLayout);
            alert.show(getSupportFragmentManager(), "alert");
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
                } else if (selectedItem == R.id.popupOptionsMap) {
                    Intent i = new Intent(Home.this, MapViewMain.class);
                    startActivity(i);
                    return true;
                }

                return false;
            });

            popup.show();
        });
    }

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
            GeneralUtil.showMySnack(rootLayout, "You pressed Logout!", close);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu_options, menu);
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
