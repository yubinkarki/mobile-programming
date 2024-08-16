package com.bca.mobile_programming.unit_7;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.content.res.Resources;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.ForegroundColorSpan;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import com.bca.mobile_programming.R;

public class MapViewMain extends AppCompatActivity implements OnMapReadyCallback {
    private Resources res;

    @Override
    protected void onStart() {
        super.onStart();

        ActionBar bar = getSupportActionBar();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.lighter_blue));

        if (bar != null) {
            int color = ContextCompat.getColor(this, R.color.lighter_blue);
            int textColor = ContextCompat.getColor(this, R.color.dark_gray);
            Spannable text = new SpannableString(res.getString(R.string.map_title));

            text.setSpan(new AbsoluteSizeSpan(20, true), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            text.setSpan(new ForegroundColorSpan(textColor), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            text.setSpan(new LeadingMarginSpan.Standard(20), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            bar.setTitle(text);
            bar.setElevation(10);
            bar.setBackgroundDrawable(new ColorDrawable(color));
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.unit_7_map_view);

        res = getResources();

        MapView mapContainer = findViewById(R.id.mapViewMain);
        mapContainer.onCreate(b);
        mapContainer.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        LatLng coordinates = new LatLng(27.6915, 85.3420);
        map.addMarker(new MarkerOptions().position(coordinates).title("Kathmandu Sahar"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 10));
    }
}
