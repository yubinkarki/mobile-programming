package com.bca.mobile_programming.unit_5;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bca.mobile_programming.R;

public class FragmentSwitchActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.unit_5_fragment_switch);

        FragmentManager fm = getSupportFragmentManager();
        int fragmentContainerId = R.id.fragmentSwitchBottomContainer;
        Button fragmentOneButton = findViewById(R.id.fragmentSwitchOneButton);
        Button fragmentTwoButton = findViewById(R.id.fragmentSwitchTwoButton);
        Button fragmentCrossButton = findViewById(R.id.fragmentSwitchCrossButton);

        fragmentOneButton.setOnClickListener(v -> {
            Fragment headphoneFragment = new HeadphoneFragment();

            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(fragmentContainerId, headphoneFragment);
            ft.addToBackStack(null);
            ft.commit();
        });

        fragmentTwoButton.setOnClickListener(v -> {
            Fragment myLogoFragment = new MyLogoFragment();

            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(fragmentContainerId, myLogoFragment);
            ft.addToBackStack(null);
            ft.commit();
        });

        fragmentCrossButton.setOnClickListener(v -> {
            fm.popBackStack();
        });
    }
}
