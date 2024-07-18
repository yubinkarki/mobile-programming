package com.bca.mobile_programming.unit_6;

import android.view.View;
import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bca.mobile_programming.R;

public class CustomGridItem extends ArrayAdapter<String> {
    int[] image;
    Activity ctx;
    String[] title;
    String[] description;

    public CustomGridItem(Activity context, String[] title, String[] description, int[] image) {
        super(context, R.layout.unit_6_custom_grid_item, title);

        this.ctx = context;
        this.title = title;
        this.image = image;
        this.description = description;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View gridItem, @NonNull ViewGroup parent) {
        ListItemHolder holder;

        if (gridItem == null) {
            LayoutInflater inflater = ctx.getLayoutInflater();
            gridItem = inflater.inflate(R.layout.unit_6_custom_grid_item, parent, false);

            holder = new ListItemHolder();
            holder.title = gridItem.findViewById(R.id.customGridItemTitle);
            holder.image = gridItem.findViewById(R.id.customGridItemImage);
            holder.description = gridItem.findViewById(R.id.customGridItemDescription);

            gridItem.setTag(holder);
        } else holder = (ListItemHolder) gridItem.getTag();

        holder.title.setText(title[position]);
        holder.image.setImageResource(image[position]);
        holder.description.setText(description[position]);

        return gridItem;
    }
}

