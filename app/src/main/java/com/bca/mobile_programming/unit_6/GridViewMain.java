package com.bca.mobile_programming.unit_6;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.res.Resources;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bca.mobile_programming.R;
import com.bca.mobile_programming.unit_1.GeneralUtil;

public class GridViewMain extends AppCompatActivity {
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

        String[] pokemonList = res.getStringArray(R.array.pokemon_list);

        ArrayAdapter<String> pokemon = new ArrayAdapter<>(this, R.layout.unit_3_spinner_item, R.id.spinnerItemText, pokemonList);

        gridView.setAdapter(pokemon);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> grid, View v, int position, long id) {
                String close = "Okay";
                Adapter gridAdapter = grid.getAdapter();
                String value = gridAdapter.getItem(position).toString();
                String message = value + " at position " + position + " with id " + id;

                GeneralUtil.showMySnack(rootLayout, message, close);
            }
        });
    }
}
