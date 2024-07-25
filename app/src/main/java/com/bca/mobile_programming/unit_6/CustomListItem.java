package com.bca.mobile_programming.unit_6;

import android.view.View;
import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bca.mobile_programming.R;

public class CustomListItem extends ArrayAdapter<String> {
    int[] image;
    Activity ctx;
    String[] title;
    String[] description;

    public CustomListItem(Activity context, String[] title, String[] description, int[] image) {
        super(context, R.layout.unit_6_custom_list_item, title);

        this.ctx = context;
        this.title = title;
        this.image = image;
        this.description = description;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View listItem, @NonNull ViewGroup parent) {
        ListItemHolder holder;

        if (listItem == null) {
            LayoutInflater inflater = ctx.getLayoutInflater();

            // Inflate the layout
            listItem = inflater.inflate(R.layout.unit_6_custom_list_item, parent, false);

            // Creating a new ViewHolder
            holder = new ListItemHolder();

            // Find individual views and store in the ViewHolder
            holder.title = listItem.findViewById(R.id.customListItemTitle);
            holder.image = listItem.findViewById(R.id.customListItemImage);
            holder.description = listItem.findViewById(R.id.customListItemDescription);

            // Store the ViewHolder within the view
            listItem.setTag(holder);
        } else holder = (ListItemHolder) listItem.getTag();

        // Set relevant data to the widgets
        holder.title.setText(title[position]);
        holder.image.setImageResource(image[position]);
        holder.description.setText(description[position]);

        return listItem;
    }
}

