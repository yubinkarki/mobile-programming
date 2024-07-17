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
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ListItemHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = ctx.getLayoutInflater();
            convertView = inflater.inflate(R.layout.unit_6_custom_list_item, parent, false);

            holder = new ListItemHolder();
            holder.title = convertView.findViewById(R.id.customListItemTitle);
            holder.image = convertView.findViewById(R.id.customListItemImage);
            holder.description = convertView.findViewById(R.id.customListItemDescription);

            convertView.setTag(holder);
        } else holder = (ListItemHolder) convertView.getTag();

        holder.title.setText(title[position]);
        holder.image.setImageResource(image[position]);
        holder.description.setText(description[position]);

        return convertView;
    }
}

