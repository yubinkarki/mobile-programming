package com.bca.mobile_programming.unit_7;

import java.util.ArrayList;

import android.view.View;
import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bca.mobile_programming.R;

public class UserListItemAdapter extends ArrayAdapter<String> {
    Activity ctx;
    ArrayList<Integer> id;
    ArrayList<String> name;
    ArrayList<String> address;

    public UserListItemAdapter(Activity context, ArrayList<Integer> id, ArrayList<String> name, ArrayList<String> address) {
        super(context, R.layout.unit_7_user_list_item, name);

        this.id = id;
        this.name = name;
        this.ctx = context;
        this.address = address;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View listItem, @NonNull ViewGroup parent) {
        UserListItemHolder holder;

        if (listItem == null) {
            LayoutInflater inflater = ctx.getLayoutInflater();

            // Inflate the layout
            listItem = inflater.inflate(R.layout.unit_7_user_list_item, parent, false);

            // Creating a new ViewHolder
            holder = new UserListItemHolder();

            holder.idView = listItem.findViewById(R.id.userListItemId);
            holder.nameView = listItem.findViewById(R.id.userListItemName);
            holder.addressView = listItem.findViewById(R.id.userListItemAddress);

            listItem.setTag(holder);
        } else holder = (UserListItemHolder) listItem.getTag();

        holder.nameView.setText(name.get(position));
        holder.addressView.setText(address.get(position));
        holder.idView.setText(String.valueOf(id.get(position)));

        return listItem;
    }
}
