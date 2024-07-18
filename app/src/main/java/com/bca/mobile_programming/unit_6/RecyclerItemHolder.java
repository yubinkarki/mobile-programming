package com.bca.mobile_programming.unit_6;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItemHolder extends RecyclerView.ViewHolder {
    ViewGroup rootView;
    ImageView imageView;
    TextView titleView, descriptionView;

    public RecyclerItemHolder(View v, int r, int t, int i, int d) {
        super(v);

        rootView = v.findViewById(r);
        titleView = v.findViewById(t);
        imageView = v.findViewById(i);
        descriptionView = v.findViewById(d);
    }
}