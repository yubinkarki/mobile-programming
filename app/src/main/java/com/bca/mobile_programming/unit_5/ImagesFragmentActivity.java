package com.bca.mobile_programming.unit_5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.bca.mobile_programming.R;

public class ImagesFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle b) {
        EdgeToEdge.enable(this);

        super.onCreate(b);
        setContentView(R.layout.unit_5_images);
    }
}
