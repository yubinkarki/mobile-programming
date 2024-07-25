package com.bca.mobile_programming.unit_6;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.content.res.Resources;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bca.mobile_programming.R;
import com.bca.mobile_programming.unit_1.GeneralUtil;

public class CustomListViewMain extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();

        ActionBar bar = getSupportActionBar();
        if (bar != null) bar.hide();
    }

    @Override
    protected void onCreate(@Nullable Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.unit_6_list_view_main);

        Resources res = getResources();

        ListView listView = findViewById(R.id.listViewMainList);
        ViewGroup rootLayout = findViewById(R.id.listViewMainRoot);

        String[] titleList = res.getStringArray(R.array.custom_title_list);
        String[] descriptionList = res.getStringArray(R.array.custom_description_list);
        int[] imageList = {R.drawable.pokemon_bulbasur, R.drawable.pokemon_charmander, R.drawable.pokemon_squirtle, R.drawable.pokemon_pikachu, R.drawable.pokemon_eeve, R.drawable.pokemon_mewtwo, R.drawable.pokemon_gengar, R.drawable.pokemon_dragonite, R.drawable.pokemon_alakazam, R.drawable.pokemon_arcanine};

        CustomListItem customAdapter = new CustomListItem(this, titleList, descriptionList, imageList);

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
