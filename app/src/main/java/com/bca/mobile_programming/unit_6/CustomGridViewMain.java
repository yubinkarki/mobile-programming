package com.bca.mobile_programming.unit_6;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.AdapterView;
import android.content.res.Resources;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bca.mobile_programming.R;
import com.bca.mobile_programming.unit_1.GeneralUtil;

public class CustomGridViewMain extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();

        ActionBar bar = getSupportActionBar();
        if (bar != null) bar.hide();
    }

    @Override
    protected void onCreate(@Nullable Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.unit_6_grid_view_main);

        Resources res = getResources();

        GridView gridView = findViewById(R.id.gridViewMainList);
        ViewGroup rootLayout = findViewById(R.id.gridViewMainRoot);

        String[] titleList = res.getStringArray(R.array.custom_title_list);
        String[] descriptionList = res.getStringArray(R.array.custom_description_list);
        int[] imageList = {R.drawable.yk_logo, R.drawable.yk_logo, R.drawable.profile, R.drawable.headphone, R.drawable.yk_logo, R.drawable.yk_logo, R.drawable.headphone, R.drawable.profile, R.drawable.profile, R.drawable.yk_logo};

        CustomGridItem customAdapter = new CustomGridItem(this, titleList, descriptionList, imageList);

        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View v, int position, long id) {
                String close = "Okay";
                Adapter listAdapter = list.getAdapter();
                String value = listAdapter.getItem(position).toString();
                String message = value + " at position " + (position + 1) + " with id " + (id + 1);

                GeneralUtil.showMySnack(rootLayout, message, close);
            }
        });
    }
}
